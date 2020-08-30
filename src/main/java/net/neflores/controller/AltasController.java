package net.neflores.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/altas")
public class AltasController {
	
	
	@GetMapping(value = "/index")
	public String mostrarAltas() {
		return "altas/listAltas";		
	}
	


}


