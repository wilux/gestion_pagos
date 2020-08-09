package net.neflores.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.neflores.model.Empleado;

public interface IEmpleadosService {

	void guardar(Empleado empleado);
	void guardarLista(List<Empleado> empleados);
	List<Empleado> buscarTodas();
	Empleado buscarPorId(Integer idEmpleado);	
	void eliminar(Integer idEmpleado);
	Page<Empleado> buscarTodas(Pageable page);
	List<Empleado> buscarPorUsuario(Integer idUsuario);
	//List<Empleado> buscarPorEmpresa(Integer idEmpresa);
	
}
