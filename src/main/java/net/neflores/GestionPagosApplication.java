package net.neflores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GestionPagosApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(GestionPagosApplication.class, args);
	}
	
   // @Override
    //protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
     //   return builder.sources(BpnGestionApplication.class);
    //}

}

