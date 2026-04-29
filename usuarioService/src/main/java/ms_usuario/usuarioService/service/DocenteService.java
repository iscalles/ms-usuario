package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Docente;
import ms_usuario.usuarioService.dto.DocenteDTO;

import java.util.List;

public interface DocenteService {
    List<Docente> listarDocentes();
    Docente buscarDocentePorId(String id);
    Docente crearDocente(DocenteDTO dto);
    Docente actualizarDocente(String id, DocenteDTO dto);
    void eliminarDocente(String id);
}
