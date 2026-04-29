package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.dto.UsuarioDTO;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario crearUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setRutUsuario(dto.getRutUsuario());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setPrimerApellidoUsuario(dto.getPrimerApellidoUsuario());
        usuario.setSegundoApellidoUsuario(dto.getSegundoApellidoUsuario());
        usuario.setCorreoUsuario(dto.getCorreoUsuario());
        usuario.setFechaNacUsuario(dto.getFechaNacUsuario());

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(String id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombreUsuario(dto.getNombreUsuario());
            usuarioExistente.setPrimerApellidoUsuario(dto.getPrimerApellidoUsuario());
            usuarioExistente.setSegundoApellidoUsuario(dto.getSegundoApellidoUsuario());
            usuarioExistente.setCorreoUsuario(dto.getCorreoUsuario());
            usuarioExistente.setFechaNacUsuario(dto.getFechaNacUsuario());

            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    @Override
    public void eliminarUsuario(String id) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioRepository.delete(usuarioExistente);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }
}
