package net.neflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.neflores.model.Proveedor;

public interface ProveedoresRepository extends JpaRepository<Proveedor, Integer> {
	 @Query("SELECT e FROM Proveedor e JOIN e.usuario u WHERE u.id =  ?1")
	 List<Proveedor> buscarPorUsuario(int usuario);


}
