package net.neflores.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.neflores.model.Mail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Controller
public class ContactoController {
	
	@Autowired
    private JavaMailSender emailService;
	
	@PostMapping("/send")
	public String conacto(RedirectAttributes attributes, Mail mail) throws MessagingException, IOException {
		
		
		try {	
			
			SimpleMailMessage msg = new SimpleMailMessage();
			 
	        msg.setTo("nestorchoele@gmail.com");
	        msg.setSubject("BPN Gestion - Contacto");
	        msg.setText("Nombre: "+mail.getNombre()+"\nApellido: "+mail.getApellido()+"\nMail: "+mail.getEmail()+"\nMensaje: "+mail.getDescripcion());

	        
	        emailService.send(msg);
			attributes.addFlashAttribute("msg", "El mensaje fue enviado!.");
	 
		}catch(Exception ex) {
			attributes.addFlashAttribute("msg", "No es posible enviar el mensaje!.");
		}
	 
		return "redirect:/contacto";
	}
		
}
