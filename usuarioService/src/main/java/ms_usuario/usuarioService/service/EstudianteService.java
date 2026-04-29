package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.dto.EstudianteDTO;

import java.util.List;

public interface EstudianteService {
    List<Estudiante> listarEstudiantes();
    Estudiante buscarEstudiantePorId(String id);
    Estudiante crearEstudiante(EstudianteDTO dto);
    Estudiante actualizarEstudiante(String id, EstudianteDTO dto);
    void eliminarEstudiante(String id);
}
