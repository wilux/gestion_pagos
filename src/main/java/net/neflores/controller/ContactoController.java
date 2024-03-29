package net.neflores.controller;

import java.io.IOException;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.neflores.model.Mail;
import net.neflores.model.Usuario;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Controller
public class ContactoController {
	
	@Autowired
    private JavaMailSender emailService;
	
	@GetMapping("/contacto")
	public String contactar(Usuario usuario) {
		return "contacto";
	}
	
	@PostMapping("/send")
	public String conacto(RedirectAttributes attributes, Mail mail) throws MessagingException, IOException {
		
		
		try {	
			
			SimpleMailMessage msg = new SimpleMailMessage();
			 
	        msg.setTo("nestorchoele@gmail.com");
	        msg.setSubject("EEP Gestión - Contacto");
	        msg.setText("Nombre: "+mail.getNombre()+"\nApellido: "+mail.getApellido()+"\nCuit: "+mail.getCuit()+"\nMail: "+mail.getEmail()+"\nMensaje: "+mail.getMensaje());

	        
	        emailService.send(msg);
			attributes.addFlashAttribute("msg", "El mensaje fue enviado!.");
	 
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible enviar el mensaje!. Vuelva a intentarlo.");
		}
	 
		return "redirect:/contacto";
	}
		
}
