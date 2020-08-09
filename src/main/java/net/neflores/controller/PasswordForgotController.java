package net.neflores.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.neflores.model.Mail;
import net.neflores.model.Usuario;
import net.neflores.repository.PasswordResetTokenRepository;
import net.neflores.service.IEmailService;
import net.neflores.service.IUsuarioService;
import net.neflores.util.PasswordResetToken;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

	@Autowired
	private IUsuarioService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	@Autowired
	private IEmailService emailService;

	
	@GetMapping
	public String displayForgotPasswordPage() {
		return "formRecuperar";
	}

	@PostMapping
	public String processForgotPasswordForm(@ModelAttribute("email") String email, BindingResult result, HttpServletRequest request, RedirectAttributes attributes) {
	

		if (result.hasErrors()) {
			return "redirect:/formRecuperar";
		}
		System.out.println(email);
		
		
		Usuario user = new Usuario();
		user = userService.buscarPorMail(email);
		if (user == null) {
			
			//result.rejectValue("email", null, "No encontramos una cuenta para el e-mail ingresado!");
			attributes.addFlashAttribute("msg", "No encontramos una cuenta para el e-mail ingresado!");
			return "redirect:/login";
		}


		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		System.out.println(token.getExpiryDate());
		tokenRepository.save(token);

		Mail mail = new Mail();
		mail.setFrom("no-responder@gestioneep.com");
		mail.setTo(user.getEmail());
		mail.setSubject("Solicitud de cambio de clave");

		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user);
		model.put("signature", "https://www.gestioneep.com.ar");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);

		attributes.addFlashAttribute("msg", "Se envio el link a su e-mail para reestablecer la clave!");
		
		return "redirect:/login";

	}

}
