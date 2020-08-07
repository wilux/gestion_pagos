package net.neflores.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import net.neflores.model.Usuario;
import net.neflores.service.IUsuarioService;


@Controller
public class HomeController {
	
  
	@Autowired
   	private IUsuarioService serviceUsuarios;
		


	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}
	
	@GetMapping("/index")
	//Authentication es una Interfaces de Spring Security que me permite recuperar datos del usuario
	public String mostrarIndex(Authentication auth, HttpSession session) {
		
		//Recuperamos el nombre del usuario
		String username = auth.getName();
		
		//Recuperamos el rol del usuario que ingresa
		//for (GrantedAuthority rol: auth.getAuthorities()) {
		//	System.out.println(rol);
		//}
		
		//Comprobamos si hay un usuario cargado primero
		if (session.getAttribute("usuario")==null) {
		
		//Definimos una variable del objeto usuario y le asignamos el usuario
		Usuario usuario = serviceUsuarios.buscarPorUsername(username);
		//La variable usuario contiene en este momento todos los datos del usuario que inicio sesion
		
		//Por seguridad, seteamos el password a nulo para que no se almancene en la session.
		usuario.setPassword(null);
		
		
		//Añadimos a la session el usuario
		session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	
	
}
