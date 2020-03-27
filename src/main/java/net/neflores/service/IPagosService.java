package net.neflores.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.neflores.model.Pago;



public interface IPagosService {
	void guardar(Pago pago);
	List<Pago> buscarTodas();
	Pago buscarPorId(Integer idPago);	
	void eliminar(Integer idPago);
	Page<Pago> buscarTodas(Pageable page);
	List<Pago> buscarPorUsuario(Integer idUsuario);
}
