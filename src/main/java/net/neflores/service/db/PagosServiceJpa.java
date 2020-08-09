package net.neflores.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.neflores.model.Pago;
import net.neflores.repository.PagosRepository;
import net.neflores.service.IPagosService;

@Service
@Primary
public class PagosServiceJpa implements IPagosService {

	@Autowired
	private PagosRepository PagosRepo;
	
	@Override
	public void guardar(Pago pago) {
		PagosRepo.save(pago);
	}

	@Override
	public List<Pago> buscarTodas() {
		return PagosRepo.findAll();
	}

	@Override
	public Pago buscarPorId(Integer idPago) {
	Optional<Pago> optional =	PagosRepo.findById(idPago);
	if (optional.isPresent()) {
		return optional.get();
	}
		return null;
	}

	@Override
	public void eliminar(Integer idPago) {
		PagosRepo.deleteById(idPago);
	}

	@Override
	public Page<Pago> buscarTodas(Pageable page) {
		return PagosRepo.findAll(page);
	}

	@Override
	public List<Pago> buscarPorUsuarioyPrestacion(Integer idUsuario, String prestacion) {
		return PagosRepo.buscarPorUsuario(idUsuario, prestacion);
	}

	@Override
	public List<Pago> FiltrarPorPreastacion(String prestacion) {

		return PagosRepo.FiltrarPorPrestacion(prestacion);
	}

}