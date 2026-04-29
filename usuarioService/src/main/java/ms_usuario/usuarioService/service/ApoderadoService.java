package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.dto.ApoderadoDTO;

import java.util.List;

public interface ApoderadoService {
    List<Apoderado> listarApoderados();
    Apoderado buscarApoderadoPorId(String id);
    Apoderado crearApoderado(ApoderadoDTO dto);
    Apoderado actualizarApoderado(String id, ApoderadoDTO dto);
    void eliminarApoderado(String id);
}
