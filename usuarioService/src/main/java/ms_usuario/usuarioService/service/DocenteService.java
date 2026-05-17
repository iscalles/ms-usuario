package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Docente;
import ms_usuario.usuarioService.dto.DocenteDTO;

import java.util.List;
import java.util.Optional;

public interface DocenteService {
    List<Docente> listarDocentes();
    Optional <Docente> buscarDocentePorId(Long id);
    Optional<Docente> buscarDocentePorIdUsuario(Long idUsuario);
    List<Docente> obtenerDocentesPorEspecialidad(String especialidad);
    List<Docente> obtenerDocentesPorEstado(String estado);
    Docente crearDocente(DocenteDTO dto);
    Docente actualizarDocente(Long id, DocenteDTO dto);
    void eliminarDocente(Long id);
    boolean esDocente(Long idUsuario);
}
