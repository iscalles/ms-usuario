package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.UsuarioRol;

import java.util.List;
import java.util.Set;

public interface UsuarioRolService {

    List<UsuarioRol> obtenerRolesDelUsuario(Long idUsuario);
    Set<String> obtenerRolesComoString(Long idUsuario);
    UsuarioRol asignarRol(Long idUsuario, String tipoRol);
    boolean tieneRol(Long idUsuario, String tipoRol);
    List<UsuarioRol> obtenerUsuariosPorRol(String tipoRol);
    void eliminarRol(Long idUsuario, String tipoRol);
    void eliminarTodosLosRoles(Long idUsuario);
}