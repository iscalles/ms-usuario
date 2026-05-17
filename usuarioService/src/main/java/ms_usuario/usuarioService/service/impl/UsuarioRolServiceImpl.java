package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.repository.UsuarioRolRepository;
import ms_usuario.usuarioService.service.UsuarioRolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioRolServiceImpl implements UsuarioRolService {

    private final UsuarioRolRepository usuarioRolRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioRolServiceImpl(UsuarioRolRepository usuarioRolRepository,
                                 UsuarioRepository usuarioRepository) {
        this.usuarioRolRepository = usuarioRolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioRol> obtenerRolesDelUsuario(Long idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));
        return usuarioRolRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public Set<String> obtenerRolesComoString(Long idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));
        return usuarioRolRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(UsuarioRol::getTipoRol)
                .collect(Collectors.toSet());
    }

    @Override
    public UsuarioRol asignarRol(Long idUsuario, String tipoRol) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));

        // Usa Id_TipoRol porque tipoRol es parte del @EmbeddedId
        Optional<UsuarioRol> rolExistente =
                usuarioRolRepository.findByUsuario_IdUsuarioAndId_TipoRol(idUsuario, tipoRol);

        if (rolExistente.isPresent()) {
            throw new RuntimeException("Usuario ya tiene el rol: " + tipoRol);
        }

        UsuarioRol usuarioRol = new UsuarioRol(usuario.getIdUsuario(), tipoRol);
        return usuarioRolRepository.save(usuarioRol);
    }

    @Override
    public boolean tieneRol(Long idUsuario, String tipoRol) {
        return usuarioRolRepository.existsByUsuario_IdUsuarioAndId_TipoRol(idUsuario, tipoRol);
    }

    @Override
    public List<UsuarioRol> obtenerUsuariosPorRol(String tipoRol) {
        return usuarioRolRepository.findById_TipoRol(tipoRol);
    }

    @Override
    public void eliminarRol(Long idUsuario, String tipoRol) {
        UsuarioRol rol = usuarioRolRepository
                .findByUsuario_IdUsuarioAndId_TipoRol(idUsuario, tipoRol)
                .orElseThrow(() -> new RuntimeException("Usuario no tiene el rol: " + tipoRol));
        usuarioRolRepository.delete(rol);
    }

    @Override
    public void eliminarTodosLosRoles(Long idUsuario) {
        usuarioRolRepository.deleteByUsuario_IdUsuario(idUsuario);
    }
}
