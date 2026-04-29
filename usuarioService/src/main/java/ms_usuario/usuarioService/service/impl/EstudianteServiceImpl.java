package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.dto.EstudianteDTO;
import ms_usuario.usuarioService.repository.EstudianteRepository;
import ms_usuario.usuarioService.service.EstudianteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante buscarEstudiantePorId(String id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    @Override
    public Estudiante crearEstudiante(EstudianteDTO dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setUsuarioRutUsuario(dto.getUsuarioRutUsuario());
        estudiante.setFechaIngresoEstudiante(dto.getFechaIngresoEstudiante());
        estudiante.setEstadoEstudiante(dto.getEstadoEstudiante());

        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante actualizarEstudiante(String id, EstudianteDTO dto) {
        Estudiante estudianteExistente = estudianteRepository.findById(id).orElse(null);
        if (estudianteExistente != null) {
            estudianteExistente.setFechaIngresoEstudiante(dto.getFechaIngresoEstudiante());
            estudianteExistente.setEstadoEstudiante(dto.getEstadoEstudiante());

            return estudianteRepository.save(estudianteExistente);
        } else {
            throw new RuntimeException("Estudiante no encontrado con id: " + id);
        }
    }

    @Override
    public void eliminarEstudiante(String id) {
        Estudiante estudianteExistente = estudianteRepository.findById(id).orElse(null);
        if (estudianteExistente != null) {
            estudianteRepository.delete(estudianteExistente);
        } else {
            throw new RuntimeException("Estudiante no encontrado con id: " + id);
        }
    }
}
