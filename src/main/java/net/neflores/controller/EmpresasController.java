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
import net.neflores.model.Empresa;
import net.neflores.model.Usuario;
import net.neflores.service.IEmpresasService;
import net.neflores.service.IUsuarioService;

@Controller
@RequestMapping(value="/empresas")
public class EmpresasController {
	
	@Autowired
   	private IEmpresasService serviceEmpresas;
	
	@Autowired
   	private IUsuarioService serviceUsuarios;
	
	
	@GetMapping(value = "/index")
	public String mostrarEmpresas(Model model, Pageable page, Authentication auth) {
		String username = auth.getName();		
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		List<Empresa> listaSimple = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		//Convierto la listaSimple en Pageable
		Page<Empresa> lista = new PageImpl<>(listaSimple);	
		model.addAttribute("empresas", lista);
		return "empresas/listEmpresas";		
	}
	
    @PostMapping(value = "/save")
	public String guardar(Empresa empresa, BindingResult result, RedirectAttributes attributes,Authentication auth) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "empresas/formEmpresas";
		}	
		
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);		
		empresa.setUsuario(usuario); 
		empresa.setStatus(1);
		serviceEmpresas.guardar(empresa);
		attributes.addFlashAttribute("msg", "Los datos de las Empresas fueron guardados!");		
		return "redirect:/empresas/index";
	}
	
	@GetMapping(value = "/create")
	public String crear(Empresa empresa) {
		return "empresas/formEmpresas";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idEmpresa,RedirectAttributes attributes) {
	 
		try {	
		
			// Eliminamos la empresa.
			Empresa empresa =  serviceEmpresas.buscarPorId(idEmpresa);
			serviceEmpresas.eliminar(empresa);		
			attributes.addFlashAttribute("msg", "La empresa fué eliminada!.");
	 
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible eliminar la empresa seleccionada!.");
		}
	 
		return "redirect:/empresas/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idEmpresa, Model model) {
		Empresa empresa =  serviceEmpresas.buscarPorId(idEmpresa);
		model.addAttribute("empresa", empresa);
		return "empresas/formEmpresas";
	}
	

}

