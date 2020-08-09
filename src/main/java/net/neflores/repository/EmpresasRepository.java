//Un repositoreio es una interfaces que extiende una interfaces de otro repositorio
//tiene los metodos de un crud. 

package net.neflores.repository;




import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import net.neflores.model.Empresa;



public interface EmpresasRepository extends JpaRepository<Empresa, Integer> {
	
	//Consulta personalizada en jpql
	//Solicito listado de empresas segun usuario logueado
	 @Query("SELECT e FROM Empresa e JOIN e.usuario u WHERE u.idUsuario =  ?1 AND e.status = 1")
	 List<Empresa> buscarPorUsuario(int idUsuario);

}
