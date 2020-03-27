package net.neflores.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.neflores.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByUsername(String username);

}
