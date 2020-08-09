package net.neflores.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.neflores.model.Empleado;
import net.neflores.repository.EmpleadosRepository;
import net.neflores.service.IEmpleadosService;


@Service
@Primary
public class EmpleadosServiceJpa implements IEmpleadosService {

	@Autowired
	private EmpleadosRepository empleadosRepo;
	
	@Override
	public void guardar(Empleado empleado) {
		empleadosRepo.save(empleado);
	}

	@Override
	public List<Empleado> buscarTodas() {
		return empleadosRepo.findAll();
	}

	@Override
	public Empleado buscarPorId(Integer idEmpleado) {
	Optional<Empleado> optional =	empleadosRepo.findById(idEmpleado);
	if (optional.isPresent()) {
		return optional.get();
	}
		return null;
	}

	@Override
	public void eliminar(Integer idEmpleado) {
		empleadosRepo.deleteById(idEmpleado);
	}

	@Override
	public Page<Empleado> buscarTodas(Pageable page) {
		return empleadosRepo.findAll(page);
	}

	@Override
	public List<Empleado> buscarPorUsuario(Integer idUsuario) {
		return empleadosRepo.buscarPorUsuario(idUsuario);
	}

	@Override
	public void guardarLista(List<Empleado> empleados) {
		empleadosRepo.saveAll(empleados);
		
	}

	
}
