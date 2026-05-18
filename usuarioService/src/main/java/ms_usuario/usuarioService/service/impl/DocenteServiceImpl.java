package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.dto.DocenteDTO;
import ms_usuario.usuarioService.model.Docente;
import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.repository.DocenteRepository;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.repository.UsuarioRolRepository;
import ms_usuario.usuarioService.service.DocenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public DocenteServiceImpl(DocenteRepository docenteRepository,
                              UsuarioRepository usuarioRepository,
                              UsuarioRolRepository usuarioRolRepository) {
        this.docenteRepository = docenteRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public List<Docente> listarDocentes() {
        return docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> buscarDocentePorId(Long id) {
        return docenteRepository.findById(id);
    }

    @Override
    public Optional<Docente> buscarDocentePorIdUsuario(Long idUsuario) {
        return docenteRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<Docente> obtenerDocentesPorEspecialidad(String especialidad) {
        return docenteRepository.findByEspecialidadDocente(especialidad);
    }

    @Override
    public List<Docente> obtenerDocentesPorEstado(String estado) {
        return docenteRepository.findByEstadoDocente(estado);
    }

    @Override
    public Docente crearDocente(DocenteDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));

        // Verificar que no existe docente para este usuario
        if (docenteRepository.existsByUsuario_IdUsuario(dto.getIdUsuario())) {
            throw new RuntimeException("Ya existe docente para usuario con id: " + dto.getIdUsuario());
        }

        Docente docente = new Docente();
        docente.setUsuario(usuario);
        docente.setTituloProfesionalDocente(dto.getTituloProfesionalDocente());
        docente.setEspecialidadDocente(dto.getEspecialidadDocente());
        docente.setEstadoDocente(dto.getEstadoDocente());

        Docente docenteGuardado = docenteRepository.save(docente);

        // Asignar rol DOCENTE solo si no lo tiene aún (evita ORA-00001)
        if (!usuarioRolRepository.existsByUsuario_IdUsuarioAndId_TipoRol(usuario.getIdUsuario(), "DOCENTE")) {
            usuarioRolRepository.save(new UsuarioRol(usuario.getIdUsuario(), "DOCENTE"));
        }

        return docenteGuardado;
    }

    @Override
    public Docente actualizarDocente(Long id, DocenteDTO dto) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con id: " + id));

        docente.setTituloProfesionalDocente(dto.getTituloProfesionalDocente());
        docente.setEspecialidadDocente(dto.getEspecialidadDocente());
        docente.setEstadoDocente(dto.getEstadoDocente());

        return docenteRepository.save(docente);
    }

    @Override
    public void eliminarDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con id: " + id));

        Long idUsuario = docente.getUsuario().getIdUsuario();
        docenteRepository.delete(docente);

        // Eliminar solo el rol DOCENTE (no los demás roles que pueda tener el usuario)
        usuarioRolRepository.findByUsuario_IdUsuarioAndId_TipoRol(idUsuario, "DOCENTE")
                .ifPresent(usuarioRolRepository::delete);
    }

    @Override
    public boolean esDocente(Long idUsuario) {
        return docenteRepository.existsByUsuario_IdUsuario(idUsuario);
    }
}