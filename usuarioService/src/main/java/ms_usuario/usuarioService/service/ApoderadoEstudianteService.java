package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.dto.ApoderadoEstudianteDTO;

import java.util.List;

public interface ApoderadoEstudianteService {
    List<ApoderadoEstudiante> listarRelaciones();
    ApoderadoEstudiante buscarRelacion(String apoderadoRut, String estudianteRut);
    List<ApoderadoEstudiante> buscarEstudiantesPorApoderado(String apoderadoRut);
    List<ApoderadoEstudiante> buscarApoderadosPorEstudiante(String estudianteRut);
    ApoderadoEstudiante crearRelacion(ApoderadoEstudianteDTO dto);
    ApoderadoEstudiante actualizarRelacion(String apoderadoRut, String estudianteRut, ApoderadoEstudianteDTO dto);
    void eliminarRelacion(String apoderadoRut, String estudianteRut);
}
