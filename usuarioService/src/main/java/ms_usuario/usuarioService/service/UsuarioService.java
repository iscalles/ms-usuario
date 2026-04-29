package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorId(String id);
    Usuario crearUsuario(UsuarioDTO dto);
    Usuario actualizarUsuario(String id, UsuarioDTO dto);
    void eliminarUsuario(String id);
}
