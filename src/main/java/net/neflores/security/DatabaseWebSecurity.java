package net.neflores.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource) //Para usar la BD con usuarios por defectos del framework
		//Con lo que sigue personalizamos la toma de usuario y perfiles segun nuestra BD personalzada
		.usersByUsernameQuery("select username, password, estatus from Usuario where username=?")
		.authoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " + 
				"inner join Usuario u on u.idUsuario = up.idUsuario " +
				"inner join Perfiles p on p.idPerfiles = up.idPerfil " +
				"where u.username = ?");
				
		
	}
	
	//Especificamos las reestricciones de los accesos
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//Los recursos estaticos no requieren autenticacion
		.antMatchers(
				"/bootstrap/**",
				"/images/**",
				"/tinymce/**",
				"/logos/**").permitAll()
		//Las vistas publicas no requieren autenticacion
		.antMatchers("/",
				"/signup",
				"/search",
				"/contacto",
				"/send",
				"/bcrypt/**",
				"/restablecer",
				"/vacantes/view/**").permitAll()
		
		// Acceso por Roles del Usuario
		
		.antMatchers("/pagos/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
		.antMatchers("/pagos_proveedor/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
		.antMatchers("/debitos/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
		.antMatchers("/empresas/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
		.antMatchers("/empleados/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
		.antMatchers("/tmp/**").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
		.antMatchers("/usuarios/**").hasAnyAuthority("SUPERVISOR")
		
		
		//Todas las demas URLs de la aplicacion requieren autenticacion
		.anyRequest().authenticated();
		
		
		
		http.formLogin()
		  .failureUrl("/login-error")

		
		//El formulario de Login no requiere autenticacion y le indicamos nuestro login personalizado
		.and().formLogin().loginPage("/login").permitAll();
			
				
	}
	
	//Implementacion para encriptar password en Bcrypt (no se puede desencriptar)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
