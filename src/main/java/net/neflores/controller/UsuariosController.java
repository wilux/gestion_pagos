package net.neflores.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.neflores.model.Usuario;
import net.neflores.service.IUsuarioService;
import net.neflores.service.db.UsuariosServiceJpa;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
   	private IUsuarioService serviceUsuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/index")
	public String mostrarIndex(Model model, Usuario usuario) {
		
		List<Usuario> lista = serviceUsuarios.buscarTodos();
    	model.addAttribute("usuarios", lista);
		return "usuarios/listUsuarios";
	}
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		

	 
		try {	
		
	    	serviceUsuarios.eliminar(idUsuario);			
			attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
	 
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible eliminar el usuario seleccionado!.");
		}
	 
		return "redirect:/usuarios/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idUsuario, Model model) {
		Usuario usuario =  serviceUsuarios.buscarPorId(idUsuario);
		model.addAttribute("usuario", usuario);
		return "usuarios/formUsuarios";
	}
	
    @PostMapping(value = "/save")
	public String guardar(Model model, Usuario usuario, BindingResult result, RedirectAttributes attributes,Authentication auth,@RequestParam String idUsuario) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "usuarios/formUsuarios";
		}	
   
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		//Encriptamo password y lo asignamos 
		usuario.setPassword(pwdEncriptado);
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor


		serviceUsuarios.guardar(usuario);

	
		
		attributes.addFlashAttribute("msg", "Los datos del usuario fueron modificados!");		
		return "redirect:/usuarios/index";
	}
	
	
}
