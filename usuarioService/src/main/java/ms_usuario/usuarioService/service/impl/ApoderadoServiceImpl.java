package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.dto.ApoderadoDTO;
import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.repository.ApoderadoRepository;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.repository.UsuarioRolRepository;
import ms_usuario.usuarioService.service.ApoderadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApoderadoServiceImpl implements ApoderadoService {

    private final ApoderadoRepository apoderadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public ApoderadoServiceImpl(ApoderadoRepository apoderadoRepository,
                                UsuarioRepository usuarioRepository,
                                UsuarioRolRepository usuarioRolRepository) {
        this.apoderadoRepository = apoderadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public List<Apoderado> listarApoderados() {
        return apoderadoRepository.findAll();
    }

    @Override
    public Optional<Apoderado> buscarApoderadoPorId(Long id) {
        return apoderadoRepository.findById(id);
    }

    @Override
    public Optional<Apoderado> buscarApoderadoPorIdUsuario(Long idUsuario) {
        return apoderadoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public Apoderado crearApoderado(ApoderadoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));

        // Verificar que no existe apoderado para este usuario
        if (apoderadoRepository.existsByUsuario_IdUsuario(dto.getIdUsuario())) {
            throw new RuntimeException("Ya existe apoderado para usuario con id: " + dto.getIdUsuario());
        }

        Apoderado apoderado = new Apoderado();
        apoderado.setUsuario(usuario);
        apoderado.setDireccionApoderado(dto.getDireccionApoderado());
        apoderado.setTelefonoApoderado(dto.getTelefonoApoderado());

        Apoderado apoderadoGuardado = apoderadoRepository.save(apoderado);

        // Asignar rol APODERADO al usuario (puede tener múltiples roles)
        if (!usuarioRolRepository.existsByUsuario_IdUsuarioAndTipoRol(dto.getIdUsuario(), "APODERADO")) {
            usuarioRolRepository.save(new UsuarioRol(usuario.getIdUsuario(), "APODERADO"));
        }

        return apoderadoGuardado;
    }

    @Override
    public Apoderado actualizarApoderado(Long id, ApoderadoDTO dto) {
        Apoderado apoderado = apoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con id: " + id));

        apoderado.setDireccionApoderado(dto.getDireccionApoderado());
        apoderado.setTelefonoApoderado(dto.getTelefonoApoderado());

        return apoderadoRepository.save(apoderado);
    }

    @Override
    public void eliminarApoderado(Long id) {
        Apoderado apoderado = apoderadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con id: " + id));

        Long idUsuario = apoderado.getUsuario().getIdUsuario();
        apoderadoRepository.delete(apoderado);

        // Nota: No se elimina el rol APODERADO automáticamente porque usuario podría tener otros roles
    }

    @Override
    public boolean esApoderado(Long idUsuario) {
        return apoderadoRepository.existsByUsuario_IdUsuario(idUsuario);
    }
}