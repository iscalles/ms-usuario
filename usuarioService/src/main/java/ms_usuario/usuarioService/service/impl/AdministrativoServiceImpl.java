package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.dto.AdministrativoDTO;
import ms_usuario.usuarioService.model.Administrativo;
import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.repository.AdministrativoRepository;
import ms_usuario.usuarioService.repository.UsuarioRepository;
import ms_usuario.usuarioService.repository.UsuarioRolRepository;
import ms_usuario.usuarioService.service.AdministrativoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdministrativoServiceImpl implements AdministrativoService {

    private final AdministrativoRepository administrativoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public AdministrativoServiceImpl(AdministrativoRepository administrativoRepository,
                                     UsuarioRepository usuarioRepository,
                                     UsuarioRolRepository usuarioRolRepository) {
        this.administrativoRepository = administrativoRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioRolRepository = usuarioRolRepository;
    }

    @Override
    public List<Administrativo> listarAdministrativos() {
        return administrativoRepository.findAll();
    }

    @Override
    public Optional<Administrativo> buscarAdministrativoPorId(Long id) {
        return administrativoRepository.findById(id);
    }

    @Override
    public Optional<Administrativo> buscarAdministrativoPorIdUsuario(Long idUsuario) {
        return administrativoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<Administrativo> obtenerAdministrativosPorDepartamento(String departamento) {
        return administrativoRepository.findByDepartamentoAdministrativo(departamento);
    }

    @Override
    public List<Administrativo> obtenerAdministrativosPorCargo(String cargo) {
        return administrativoRepository.findByCargoAdministrativo(cargo);
    }

    @Override
    public Administrativo crearAdministrativo(AdministrativoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getIdUsuario()));

        // Verificar que no existe administrativo para este usuario
        if (administrativoRepository.existsByUsuario_IdUsuario(dto.getIdUsuario())) {
            throw new RuntimeException("Ya existe administrativo para usuario con id: " + dto.getIdUsuario());
        }

        Administrativo administrativo = new Administrativo();
        administrativo.setUsuario(usuario);
        administrativo.setCargoAdministrativo(dto.getCargoAdministrativo());
        administrativo.setDepartamentoAdministrativo(dto.getDepartamentoAdministrativo());

        Administrativo administrativoGuardado = administrativoRepository.save(administrativo);

        // Asignar rol ADMINISTRATIVO al usuario
        if (!usuarioRolRepository.existsByUsuario_IdUsuarioAndTipoRol(dto.getIdUsuario(), "ADMINISTRATIVO")) {
            usuarioRolRepository.save(new UsuarioRol(usuario.getIdUsuario(), "ADMINISTRATIVO"));
        }

        return administrativoGuardado;
    }

    @Override
    public Administrativo actualizarAdministrativo(Long id, AdministrativoDTO dto) {
        Administrativo administrativo = administrativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrativo no encontrado con id: " + id));

        administrativo.setCargoAdministrativo(dto.getCargoAdministrativo());
        administrativo.setDepartamentoAdministrativo(dto.getDepartamentoAdministrativo());

        return administrativoRepository.save(administrativo);
    }

    @Override
    public void eliminarAdministrativo(Long id) {
        Administrativo administrativo = administrativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrativo no encontrado con id: " + id));

        Long idUsuario = administrativo.getUsuario().getIdUsuario();
        administrativoRepository.delete(administrativo);

        // Nota: No se elimina el rol ADMINISTRATIVO automáticamente porque usuario podría tener otros roles
    }

    @Override
    public boolean esAdministrativo(Long idUsuario) {
        return administrativoRepository.existsByUsuario_IdUsuario(idUsuario);
    }
}