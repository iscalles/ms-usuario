package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.dto.EstudianteDTO;
import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.repository.EstudianteRepository;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.repository.UsuarioRolRepository;
import ms_usuario.usuarioService.service.EstudianteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository,
                                 UsuarioRepository usuarioRepository,
                                 UsuarioRolRepository usuarioRolRepository) {
        this.estudianteRepository = estudianteRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorId(Long id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public Optional<Estudiante> buscarEstudiantePorIdUsuario(Long idUsuario) {
        return estudianteRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<Estudiante> obtenerEstudiantesPorEstado(String estado) {
        return estudianteRepository.findByEstadoEstudiante(estado);
    }

    @Override
    public Estudiante crearEstudiante(EstudianteDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));

        // Verificar que no existe estudiante para este usuario
        // Un usuario puede ser SOLO estudiante
        if (estudianteRepository.existsByUsuario_IdUsuario(dto.getIdUsuario())) {
            throw new RuntimeException("Ya existe estudiante para usuario con id: " + dto.getIdUsuario());
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setUsuario(usuario);
        estudiante.setFechaIngresoEstudiante(dto.getFechaIngresoEstudiante());
        estudiante.setEstadoEstudiante(dto.getEstadoEstudiante());

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        // Asignar rol ESTUDIANTE al usuario
        usuarioRolRepository.save(new UsuarioRol(usuario.getIdUsuario(), "ESTUDIANTE"));

        return estudianteGuardado;
    }

    @Override
    public Estudiante actualizarEstudiante(Long id, EstudianteDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));

        estudiante.setFechaIngresoEstudiante(dto.getFechaIngresoEstudiante());
        estudiante.setEstadoEstudiante(dto.getEstadoEstudiante());

        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));

        Long idUsuario = estudiante.getUsuario().getIdUsuario();
        estudianteRepository.delete(estudiante);

        // Eliminar rol ESTUDIANTE del usuario
        usuarioRolRepository.deleteByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public boolean esEstudiante(Long idUsuario) {
        return estudianteRepository.existsByUsuario_IdUsuario(idUsuario);
    }
}