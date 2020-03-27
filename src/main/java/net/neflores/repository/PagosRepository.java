package net.neflores.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.neflores.model.Pago;


public interface PagosRepository extends JpaRepository<Pago, Integer>{
	//Consulta personalizada en jpql
	//Solicito listado de pagos segun usuario logueado
	 @Query("SELECT p FROM Pago p JOIN p.usuario u WHERE u.idUsuario =  ?1")
	 List<Pago> buscarPorUsuario(int idUsuario);
	 
}
