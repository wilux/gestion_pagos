package net.neflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.neflores.model.Pago;


public interface PagosRepository extends JpaRepository<Pago, Integer>{
	//Consulta personalizada en jpql
	//Solicito listado de pagos segun usuario logueado
	 @Query("SELECT p FROM Pago p JOIN p.usuario u WHERE u.idUsuario =  ?1 AND p.prestacion =?2")
	 List<Pago> buscarPorUsuario(int idUsuario, String prestacion);
	 
	 @Query("SELECT p FROM Pago p where p.prestacion = ?1")
	 List<Pago> FiltrarPorPrestacion(String prestacion);
	 
}
