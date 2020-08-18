package net.neflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.neflores.model.Empleado;

public interface EmpleadosRepository extends JpaRepository<Empleado, Integer> {
	
	 @Query("SELECT e FROM Empleado e JOIN e.usuario u WHERE u.id = ?1 AND e.status = 1")
	 List<Empleado> buscarPorUsuario(int usuario);
	 
	// @Query("SELECT e FROM Empleado e JOIN e.empresa u WHERE u.id = ?1")
	//	List<Empleado> buscarPorEmpresa(int empresa);
	 

}
