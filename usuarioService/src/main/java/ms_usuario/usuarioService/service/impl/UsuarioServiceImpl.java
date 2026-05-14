package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.dto.UsuarioDTO;
import ms_usuario.usuarioService.dto.UsuarioDTOResponse;
import ms_usuario.usuarioService.dto.UsuarioDTOInternal;
import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.repository.UsuarioRolRepository;
import ms_usuario.usuarioService.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioRolRepository usuarioRolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public List<UsuarioDTOResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioDTOResponse> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    @Override
    public Optional<UsuarioDTOInternal> buscarUsuarioPorRut(String rut) {
        return usuarioRepository.findByRutUsuario(rut)
                .map(this::convertToInternalDTO);
    }

    @Override
    public Optional<UsuarioDTOInternal> buscarUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreoUsuario(correo)
                .map(this::convertToInternalDTO);
    }

    @Override
    public UsuarioDTOResponse crearUsuario(UsuarioDTO dto) {
        // Validar que no exista usuario con ese RUT
        if (usuarioRepository.existsByRutUsuario(dto.getRutUsuario())) {
            throw new RuntimeException("Ya existe usuario con RUT: " + dto.getRutUsuario());
        }

        // Validar que no exista usuario con ese correo
        if (usuarioRepository.existsByCorreoUsuario(dto.getCorreoUsuario())) {
            throw new RuntimeException("Ya existe usuario con correo: " + dto.getCorreoUsuario());
        }

        Usuario usuario = new Usuario();
        usuario.setRutUsuario(dto.getRutUsuario());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setPrimerApellidoUsuario(dto.getPrimerApellidoUsuario());
        usuario.setSegundoApellidoUsuario(dto.getSegundoApellidoUsuario());
        usuario.setCorreoUsuario(dto.getCorreoUsuario());
        usuario.setFechaNacUsuario(dto.getFechaNacUsuario());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return convertToResponseDTO(usuarioGuardado);
    }

    @Override
    public UsuarioDTOResponse actualizarUsuario(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        // Validar que no exista otro usuario con ese correo
        if (!usuario.getCorreoUsuario().equals(dto.getCorreoUsuario())
                && usuarioRepository.existsByCorreoUsuario(dto.getCorreoUsuario())) {
            throw new RuntimeException("Ya existe usuario con correo: " + dto.getCorreoUsuario());
        }

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setPrimerApellidoUsuario(dto.getPrimerApellidoUsuario());
        usuario.setSegundoApellidoUsuario(dto.getSegundoApellidoUsuario());
        usuario.setCorreoUsuario(dto.getCorreoUsuario());
        usuario.setFechaNacUsuario(dto.getFechaNacUsuario());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return convertToResponseDTO(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }

    @Override
    public boolean existeUsuarioPorRut(String rut) {
        return usuarioRepository.existsByRutUsuario(rut);
    }

    @Override
    public boolean existeUsuarioPorCorreo(String correo) {
        return usuarioRepository.existsByCorreoUsuario(correo);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioConRoles(Long id) {
        return usuarioRepository.findById(id);
    }

    private Set<String> obtenerRolesComoSet(Long idUsuario) {
        return usuarioRolRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(UsuarioRol::getTipoRol)
                .collect(Collectors.toSet());
    }

    // Métodos auxiliares de conversión
    private UsuarioDTOResponse convertToResponseDTO(Usuario usuario) {
        UsuarioDTOResponse dto = new UsuarioDTOResponse();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setPrimerApellidoUsuario(usuario.getPrimerApellidoUsuario());
        dto.setSegundoApellidoUsuario(usuario.getSegundoApellidoUsuario());
        dto.setCorreoUsuario(usuario.getCorreoUsuario());
        dto.setFechaNacUsuario(usuario.getFechaNacUsuario());
        dto.setRoles(obtenerRolesComoSet(usuario.getIdUsuario()));

        return dto;
    }

    private UsuarioDTOInternal convertToInternalDTO(Usuario usuario) {
        UsuarioDTOInternal dto = new UsuarioDTOInternal();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setRutUsuario(usuario.getRutUsuario());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setPrimerApellidoUsuario(usuario.getPrimerApellidoUsuario());
        dto.setSegundoApellidoUsuario(usuario.getSegundoApellidoUsuario());
        dto.setCorreoUsuario(usuario.getCorreoUsuario());
        dto.setFechaNacUsuario(usuario.getFechaNacUsuario());
        dto.setRoles(obtenerRolesComoSet(usuario.getIdUsuario()));

        return dto;
    }
    /*Busca usuario por ID_USUARIO y devuelve DTO interno con RUT y roles */
    @Override
    public Optional<UsuarioDTOInternal> buscarUsuarioPorIdInterno(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(this::convertToInternalDTO);
    }
}