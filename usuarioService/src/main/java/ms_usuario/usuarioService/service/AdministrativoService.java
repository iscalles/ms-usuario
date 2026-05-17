package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.dto.AdministrativoDTO;
import ms_usuario.usuarioService.model.Administrativo;

import java.util.List;
import java.util.Optional;

public interface AdministrativoService {

    List<Administrativo> listarAdministrativos();
    Optional<Administrativo> buscarAdministrativoPorId(Long id);
    Optional<Administrativo> buscarAdministrativoPorIdUsuario(Long idUsuario);
    List<Administrativo> obtenerAdministrativosPorDepartamento(String departamento);
    List<Administrativo> obtenerAdministrativosPorCargo(String cargo);
    Administrativo crearAdministrativo(AdministrativoDTO dto);
    Administrativo actualizarAdministrativo(Long id, AdministrativoDTO dto);
    void eliminarAdministrativo(Long id);
    boolean esAdministrativo(Long idUsuario);
}