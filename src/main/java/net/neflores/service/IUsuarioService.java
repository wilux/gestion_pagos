package net.neflores.service;

import java.util.List;
import net.neflores.model.Usuario;

public interface IUsuarioService {
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	Usuario buscarPorUsername(String username);
	Usuario buscarPorId(Integer idUsuario);	
}
