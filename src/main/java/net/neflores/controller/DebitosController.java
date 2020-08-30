package net.neflores.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/debitos")
public class DebitosController {
	

	
	@GetMapping(value = "/index")
	public String mostrarDebitos() {
		
		return "debitos/listDebitos";		
	}
		

}


