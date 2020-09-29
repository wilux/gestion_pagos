package net.neflores.controller;


import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import net.neflores.model.Perfil;
import net.neflores.model.Usuario;
import net.neflores.service.IUsuarioService;


@Controller
public class LoginController {
	
  
	@Autowired
   	private IUsuarioService serviceUsuarios;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	


			
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	
	@GetMapping("login-error")
    public RedirectView setErrorMessage(RedirectAttributes attributes) {
        attributes.addFlashAttribute("error", "Usuario o Password invalido!");
        return new RedirectView("login");
    }


	
	
   
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes attributes) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		attributes.addFlashAttribute("error", "Su sesión ha caducado. Vuelva a iniciar sesión");
		return "redirect:/login";
	}
	

	
	//Si recibe una cadena vacia, lo cambia a null 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) throws DuplicateKeyException{
		
		
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		//Encriptamo password y lo asignamos 
		usuario.setPassword(pwdEncriptado);
		
		usuario.setStatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */

		if (serviceUsuarios.buscarPorUsername(usuario.getUsername()) == null) {
			serviceUsuarios.guardar(usuario);
		} else {
			attributes.addFlashAttribute("error", "El usuario ya fué utilizado, debe elegir uno distinto.");
			return "redirect:/signup";
		}
				 
				
		attributes.addFlashAttribute("msg", "Usuario registrado exitosamente!");
		
		return "redirect:/login";
	}
	

	
}
