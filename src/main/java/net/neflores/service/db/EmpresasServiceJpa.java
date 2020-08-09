package net.neflores.service.db;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.neflores.model.Empresa;
import net.neflores.repository.EmpresasRepository;
import net.neflores.service.IEmpresasService;


@Service
@Primary
public class EmpresasServiceJpa implements IEmpresasService {

	@Autowired
	private EmpresasRepository empresasRepo;
	
	
	@Override
	public void guardar(Empresa empresa) {
		empresasRepo.save(empresa);
	}

	@Override
	public List<Empresa> buscarTodas() {
		return empresasRepo.findAll();
		//return empresasRepo.buscarPorUsuario();
	}

	@Override
	public Empresa buscarPorId(Integer idEmpresa) {
		
	Optional<Empresa> optional =	empresasRepo.findById(idEmpresa);
	if (optional.isPresent()) {
		return optional.get();
	}
		return null;
	}

	@Override
	public void eliminar(Empresa empresa) {
		empresa.setStatus(0);
		empresasRepo.save(empresa);
		//empresasRepo.deleteById(idEmpresa);
	}

	@Override
	public Page<Empresa> buscarTodas(Pageable page) {
		return empresasRepo.findAll(page);
	}

	@Override
	public List<Empresa> buscarPorUsuario(Integer idUsuario) {
		return empresasRepo.buscarPorUsuario(idUsuario);
		
	}



}
