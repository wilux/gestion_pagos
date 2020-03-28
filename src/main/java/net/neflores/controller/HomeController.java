package net.neflores.controller;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.neflores.model.Perfil;
import net.neflores.model.Usuario;
import net.neflores.service.IUsuarioService;

@Controller
public class HomeController {
	

	@Autowired
   	private IUsuarioService serviceUsuarios;
		
	@Autowired
	private PasswordEncoder passwordEncoder;


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
		for (GrantedAuthority rol: auth.getAuthorities()) {
			System.out.println(rol);
		}
		
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
	
	@GetMapping("/contacto")
	public String contactar(Usuario usuario) {
		return "contacto";
	}
			
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}
	

	
	//Si recibe una cadena vacia, lo cambia a null 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		
		
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		//Encriptamo password y lo asignamos 
		usuario.setPassword(pwdEncriptado);
		
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");
		
		return "redirect:/";
	}
	
	//Utilidad para encriptar un texto en Bcrypt y mostrar en el navegador
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encriptar(@PathVariable("texto")String texto) {
		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
	}
	

	
}