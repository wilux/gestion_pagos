package net.neflores.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.neflores.model.Proveedor;
import net.neflores.model.Empresa;
import net.neflores.model.Usuario;
import net.neflores.service.IProveedoresService;
import net.neflores.service.IEmpresasService;
import net.neflores.service.IUsuarioService;

@Controller
@RequestMapping(value="/proveedores")
public class ProveedoresController {
	
	@Autowired
   	private IProveedoresService serviceProveedores;
	
	@Autowired
   	private IUsuarioService serviceUsuarios;
	
	@Autowired
	private IEmpresasService serviceEmpresas;
	
	@GetMapping(value = "/index")
	public String mostrarProveedores(Model model, Proveedor proveedor, Pageable page,Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);		
		proveedor.setUsuario(usuario); 
		try {
			
			List<Proveedor> listaSimple = serviceProveedores.buscarPorUsuario(usuario.getIdUsuario());
			Page<Proveedor> lista = new PageImpl<>(listaSimple);	
	    	model.addAttribute("proveedores", lista);
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return "proveedores/listProveedores";		
	}
	
    @PostMapping(value = "/save")
	public String guardar(Model model, Proveedor proveedor, Empresa empresa, BindingResult result, RedirectAttributes attributes,Authentication auth) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "proveedores/formProveedor";
		}	
		String username = auth.getName();		
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		proveedor.setUsuario(usuario); 
        proveedor.setStatus(1);
		serviceProveedores.guardar(proveedor);
		attributes.addFlashAttribute("msg", "Los datos de las Proveedores fueron guardados!");		
		return "redirect:/proveedores/index";
	}
	
	@GetMapping(value = "/create")
	public String crear(Model model,Proveedor proveedor,Empresa empresa, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {
		// Indico el usuario con el que estoy trabajando
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
	    
		model.addAttribute("empresa", listaEmpresas);
		return "proveedores/formProveedor";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idProveedor, RedirectAttributes attributes) {
	 
		try {	
		
			// Eliminamos el proveedor.
			Proveedor proveedor =  serviceProveedores.buscarPorId(idProveedor);
			serviceProveedores.eliminar(proveedor);			
			attributes.addFlashAttribute("msg", "El proveedor fué eliminada!.");
	 
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible eliminar el proveedor seleccionado!.");
		}
	 
		return "redirect:/proveedores/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idProveedor, Model model,Empresa empresa, Authentication auth) {
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		Proveedor proveedor =  serviceProveedores.buscarPorId(idProveedor);
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("proveedor", proveedor);
		model.addAttribute("empresa", listaEmpresas);
		return "proveedores/formProveedor";
	}
	

	

}

