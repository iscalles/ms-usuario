package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.dto.EstudianteDTO;

import java.util.List;
import java.util.Optional;

public interface EstudianteService {
    List<Estudiante> listarEstudiantes();
    Optional <Estudiante> buscarEstudiantePorId(Long id);
    Optional<Estudiante> buscarEstudiantePorIdUsuario(Long idUsuario);
    List<Estudiante> obtenerEstudiantesPorEstado(String estado);
    Estudiante crearEstudiante(EstudianteDTO dto);
    Estudiante actualizarEstudiante(Long id, EstudianteDTO dto);
    void eliminarEstudiante(Long id);
    boolean esEstudiante(Long idUsuario);
}
