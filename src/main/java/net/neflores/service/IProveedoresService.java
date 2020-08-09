package net.neflores.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.neflores.model.Proveedor;

public interface IProveedoresService {

	void guardar(Proveedor proveedor);
	void guardarLista(List<Proveedor> proveedors);
	List<Proveedor> buscarTodas();
	Proveedor buscarPorId(Integer idProveedor);	
	void eliminar(Proveedor proveedor);
	Page<Proveedor> buscarTodas(Pageable page);
	List<Proveedor> buscarPorUsuario(Integer idUsuario);
}
