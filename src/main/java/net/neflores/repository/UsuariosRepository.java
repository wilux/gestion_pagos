package net.neflores.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.neflores.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	
	 Usuario findByEmail(String email);
	 
	 Usuario findByUsername(String username);
	 
	 @Modifying
	 @Query("update Usuario u set u.password = :password where u.id = :id")
	    void updatePassword(@Param("password") String password, @Param("id") Integer id);

	 
}
