package net.neflores.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.neflores.model.Proveedor;
import net.neflores.repository.ProveedoresRepository;
import net.neflores.service.IProveedoresService;


@Service
@Primary
public class ProveedoresServiceJpa implements IProveedoresService {

	@Autowired
	private ProveedoresRepository proveedoresRepo;
	
	@Override
	public void guardar(Proveedor empleado) {
		proveedoresRepo.save(empleado);
	}

	@Override
	public List<Proveedor> buscarTodas() {
		return proveedoresRepo.findAll();
	}

	@Override
	public Proveedor buscarPorId(Integer idProveedor) {
	Optional<Proveedor> optional =	proveedoresRepo.findById(idProveedor);
	if (optional.isPresent()) {
		return optional.get();
	}
		return null;
	}

	@Override
	public void eliminar(Integer idProveedor) {
		proveedoresRepo.deleteById(idProveedor);
	}

	@Override
	public Page<Proveedor> buscarTodas(Pageable page) {
		return proveedoresRepo.findAll(page);
	}

	@Override
	public List<Proveedor> buscarPorUsuario(Integer idUsuario) {
		return proveedoresRepo.buscarPorUsuario(idUsuario);
	}

	@Override
	public void guardarLista(List<Proveedor> proveedores) {
		proveedoresRepo.saveAll(proveedores);
		
	}
}
