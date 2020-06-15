package net.neflores.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.neflores.model.Usuario;
import net.neflores.repository.UsuariosRepository;
import net.neflores.service.IUsuarioService;

@Service
public class UsuariosServiceJpa implements IUsuarioService {
	
	@Autowired
	private UsuariosRepository UsuariosRepo;

	@Override
	public void guardar(Usuario usuario) {
		
		UsuariosRepo.save(usuario);

	}

	@Override
	public void eliminar(Integer idUsuario) {
		UsuariosRepo.deleteById(idUsuario);

	}

	@Override
	public List<Usuario> buscarTodos() {
		return UsuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return UsuariosRepo.findByUsername(username);
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		Optional<Usuario> optional =	UsuariosRepo.findById(idUsuario);
		if (optional.isPresent()) {
			return optional.get();
		}
			return null;
	}
	

}
