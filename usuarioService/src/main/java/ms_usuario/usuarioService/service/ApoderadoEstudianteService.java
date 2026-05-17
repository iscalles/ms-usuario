package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.dto.ApoderadoEstudianteDTO;
import ms_usuario.usuarioService.model.ApoderadoEstudiante;

import java.util.List;
import java.util.Optional;

public interface ApoderadoEstudianteService {

    List<ApoderadoEstudiante> obtenerEstudiantesDelApoderado(Long idApoderado);
    List<ApoderadoEstudiante> obtenerApoderadosDelEstudiante(Long idEstudiante);
    Optional<ApoderadoEstudiante> buscarRelacion(Long idApoderado, Long idEstudiante);
    ApoderadoEstudiante crearRelacion(ApoderadoEstudianteDTO dto);
    ApoderadoEstudiante actualizarRelacion(Long idApoderado, Long idEstudiante, String parentesco);
    void eliminarRelacion(Long idApoderado, Long idEstudiante);
    boolean existeRelacion(Long idApoderado, Long idEstudiante);
}