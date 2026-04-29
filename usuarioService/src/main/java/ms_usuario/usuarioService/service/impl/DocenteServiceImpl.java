package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.model.Docente;
import ms_usuario.usuarioService.dto.DocenteDTO;
import ms_usuario.usuarioService.repository.DocenteRepository;
import ms_usuario.usuarioService.service.DocenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocenteServiceImpl implements DocenteService {
    private final DocenteRepository docenteRepository;

    public DocenteServiceImpl(DocenteRepository docenteRepository) {
        this.docenteRepository = docenteRepository;
    }

    @Override
    public List<Docente> listarDocentes() {
        return docenteRepository.findAll();
    }

    @Override
    public Docente buscarDocentePorId(String id) {
        return docenteRepository.findById(id).orElse(null);
    }

    @Override
    public Docente crearDocente(DocenteDTO dto) {
        Docente docente = new Docente();
        docente.setUsuarioRutUsuario(dto.getUsuarioRutUsuario());
        docente.setTituloProfesionalDocente(dto.getTituloProfesionalDocente());
        docente.setEspecialidadDocente(dto.getEspecialidadDocente());

        return docenteRepository.save(docente);
    }

    @Override
    public Docente actualizarDocente(String id, DocenteDTO dto) {
        Docente docenteExistente = docenteRepository.findById(id).orElse(null);
        if (docenteExistente != null) {
            docenteExistente.setTituloProfesionalDocente(dto.getTituloProfesionalDocente());
            docenteExistente.setEspecialidadDocente(dto.getEspecialidadDocente());

            return docenteRepository.save(docenteExistente);
        } else {
            throw new RuntimeException("Docente no encontrado con id: " + id);
        }
    }

    @Override
    public void eliminarDocente(String id) {
        Docente docenteExistente = docenteRepository.findById(id).orElse(null);
        if (docenteExistente != null) {
            docenteRepository.delete(docenteExistente);
        } else {
            throw new RuntimeException("Docente no encontrado con id: " + id);
        }
    }
}
