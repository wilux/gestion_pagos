package net.neflores.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import net.neflores.model.Empresa;

public interface IEmpresasService {
	void guardar(Empresa empresa);
	List<Empresa> buscarTodas();
	Empresa buscarPorId(Integer idEmpresa);	
	void eliminar(Integer idEmpresa);
	Page<Empresa> buscarTodas(Pageable page);
	List<Empresa> buscarPorUsuario(Integer idUsuario);
}