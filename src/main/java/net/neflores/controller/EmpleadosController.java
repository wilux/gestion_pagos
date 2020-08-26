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
import net.neflores.model.Empleado;
import net.neflores.model.Empresa;
import net.neflores.model.Usuario;
import net.neflores.service.IEmpleadosService;
import net.neflores.service.IEmpresasService;
import net.neflores.service.IUsuarioService;

@Controller
@RequestMapping(value="/empleados")
public class EmpleadosController {
	
	@Autowired
   	private IEmpleadosService serviceEmpleados;
	
	@Autowired
   	private IUsuarioService serviceUsuarios;
	
	@Autowired
	private IEmpresasService serviceEmpresas;
	
	@GetMapping(value = "/index")
	public String mostrarEmpleados(Model model, Empleado empleado, Pageable page,Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);		
		empleado.setUsuario(usuario); 
		
		List<Empleado> listaSimple = serviceEmpleados.buscarPorUsuario(usuario.getIdUsuario());
		Page<Empleado> lista = new PageImpl<>(listaSimple);	
    	model.addAttribute("empleados", lista);
		return "empleados/listEmpleados";		
	}
	
    @PostMapping(value = "/save")
	public String guardar(Model model, Empleado empleado, Empresa empresa, BindingResult result, RedirectAttributes attributes,Authentication auth) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "empleados/formEmpleados";
		}	
		String username = auth.getName();		
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		empleado.setUsuario(usuario); 
        empleado.setStatus(1);
		serviceEmpleados.guardar(empleado);
		attributes.addFlashAttribute("msg", "Los datos de las Empleados fueron guardados!");		
		return "redirect:/empleados/index";
	}
	
	@GetMapping(value = "/create")
	public String crear(Model model,Empleado empleado,Empresa empresa, BindingResult result, RedirectAttributes attributes,
			Authentication auth) {
		// Indico el usuario con el que estoy trabajando
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
	    
		model.addAttribute("empresa", listaEmpresas);
		return "empleados/formEmpleados";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idEmpleado, RedirectAttributes attributes) {
	 
		try {	
		
			// Eliminamos el empleado.
			Empleado empleado =  serviceEmpleados.buscarPorId(idEmpleado);
			serviceEmpleados.eliminar(empleado);		
			attributes.addFlashAttribute("msg", "El empleado fué eliminada!.");
	 
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible eliminar el empleado seleccionado!.");
		}
	 
		return "redirect:/empleados/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idEmpleado, Model model,Empresa empresa, Authentication auth) {
		
		String username = auth.getName();
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		Empleado empleado =  serviceEmpleados.buscarPorId(idEmpleado);
		List<Empresa> listaEmpresas = serviceEmpresas.buscarPorUsuario(usuario.getIdUsuario());
		model.addAttribute("empleado", empleado);
		model.addAttribute("empresa", listaEmpresas);
		
		return "empleados/formEmpleados";
	}
	

	

}

