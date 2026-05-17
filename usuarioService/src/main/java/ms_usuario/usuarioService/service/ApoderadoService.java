package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.dto.ApoderadoDTO;

import java.util.List;
import java.util.Optional;

public interface ApoderadoService {
    List<Apoderado> listarApoderados();
    Optional <Apoderado> buscarApoderadoPorId(Long id);
    Optional<Apoderado> buscarApoderadoPorIdUsuario(Long idUsuario);
    Apoderado crearApoderado(ApoderadoDTO dto);
    Apoderado actualizarApoderado(Long id, ApoderadoDTO dto);
    void eliminarApoderado(Long id);
    boolean esApoderado(Long idUsuario);
}
