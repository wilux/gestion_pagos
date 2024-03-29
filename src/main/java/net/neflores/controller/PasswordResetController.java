package net.neflores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.neflores.model.PasswordResetDto;
import net.neflores.model.Usuario;
import net.neflores.service.IPasswordResetTokenService;
import net.neflores.service.IUsuarioService;
import net.neflores.util.PasswordResetToken;


@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

	@Autowired
	private IUsuarioService userService;
	@Autowired
	private IPasswordResetTokenService tokenRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@ModelAttribute("passwordResetForm")
	public PasswordResetDto passwordReset() {
		return new PasswordResetDto();
	}

	@GetMapping
	public String displayResetPasswordPage(@RequestParam(required = false) String token, Model model,
			RedirectAttributes attributes) {

		PasswordResetToken resetToken = new PasswordResetToken();
		resetToken = tokenRepository.findByToken(token);

		if (resetToken == null) {

			attributes.addFlashAttribute("error",
					"No se encontro el token para recuperar el password, haga nueva solicitud!");
			return "redirect:/forgot-password";
		}

		else if (resetToken.isExpired()) {

			attributes.addFlashAttribute("error", "El Token expiró, haga una nueva solicitud!");
			return "redirect:/forgot-password";
		}

		else {
			model.addAttribute("token", resetToken.getToken());
			attributes.addFlashAttribute("msg", "Datos correctos, ahora puede cambiar la contraseña.");
		}


		return "reset-password";
	}

	@PostMapping
	@Transactional
	public String handlePasswordReset(@ModelAttribute("passwordResetForm") PasswordResetDto form, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
			redirectAttributes.addFlashAttribute("passwordResetForm", form);
			return "redirect:/reset-password?token=" + form.getToken();
		}

		PasswordResetToken token = tokenRepository.findByToken(form.getToken());
		Usuario user = token.getUser();
		String updatedPassword = passwordEncoder.encode(form.getPassword());
		userService.updatePassword(updatedPassword, user.getIdUsuario());
		tokenRepository.delete(token);
		redirectAttributes.addFlashAttribute("msg", "La contraseña fue reestablecida!");		
		return "redirect:/login";
	}

}