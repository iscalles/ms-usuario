package ms_usuario.usuarioService.service;

import ms_usuario.usuarioService.model.Usuario;
import ms_usuario.usuarioService.dto.UsuarioDTO;
import ms_usuario.usuarioService.dto.UsuarioDTOResponse;
import ms_usuario.usuarioService.dto.UsuarioDTOInternal;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    /*Listar todos los usuarios (respuesta pública sin RUT)*/
    List<UsuarioDTOResponse> listarUsuarios();
    /*Listar todos los usuarios (respuesta pública sin RUT)*/
    Optional <UsuarioDTOResponse> buscarUsuarioPorId(Long id);
    /*Buscar usuario por RUT (respuesta INTERNA - solo MS_USUARIO)*/
    Optional<UsuarioDTOInternal> buscarUsuarioPorRut(String rut);
    /*Buscar usuario por correo (respuesta INTERNA)*/
    Optional<UsuarioDTOInternal> buscarUsuarioPorCorreo(String correo);
    UsuarioDTOResponse crearUsuario(UsuarioDTO dto);
    UsuarioDTOResponse actualizarUsuario(Long id, UsuarioDTO dto);
    void eliminarUsuario(Long id);
    boolean existeUsuarioPorRut(String rut);
    boolean existeUsuarioPorCorreo(String correo);
    Optional<Usuario> obtenerUsuarioConRoles(Long id);
    Optional<UsuarioDTOInternal> buscarUsuarioPorIdInterno(Long idUsuario);
}
