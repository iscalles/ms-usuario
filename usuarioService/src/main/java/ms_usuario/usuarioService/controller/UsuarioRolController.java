package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.service.UsuarioRolService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuario-rol")
public class UsuarioRolController {

    private final UsuarioRolService usuarioRolService;

    public UsuarioRolController(UsuarioRolService usuarioRolService) {
        this.usuarioRolService = usuarioRolService;
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<UsuarioRol>> obtenerRolesDelUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(usuarioRolService.obtenerRolesDelUsuario(idUsuario));
    }

    @GetMapping("/usuario/{idUsuario}/roles-string")
    public ResponseEntity<Set<String>> obtenerRolesComoString(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(usuarioRolService.obtenerRolesComoString(idUsuario));
    }

    @PostMapping("/usuario/{idUsuario}/rol/{tipoRol}")
    public ResponseEntity<UsuarioRol> asignarRol(
            @PathVariable Long idUsuario,
            @PathVariable String tipoRol) {
        UsuarioRol rolAsignado = usuarioRolService.asignarRol(idUsuario, tipoRol);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolAsignado);
    }

    @GetMapping("/usuario/{idUsuario}/tiene-rol/{tipoRol}")
    public ResponseEntity<Boolean> tieneRol(
            @PathVariable Long idUsuario,
            @PathVariable String tipoRol) {
        return ResponseEntity.ok(usuarioRolService.tieneRol(idUsuario, tipoRol));
    }

    @GetMapping("/rol/{tipoRol}/usuarios")
    public ResponseEntity<List<UsuarioRol>> obtenerUsuariosPorRol(@PathVariable String tipoRol) {
        return ResponseEntity.ok(usuarioRolService.obtenerUsuariosPorRol(tipoRol));
    }

    @DeleteMapping("/usuario/{idUsuario}/rol/{tipoRol}")
    public ResponseEntity<Void> eliminarRol(
            @PathVariable Long idUsuario,
            @PathVariable String tipoRol) {
        usuarioRolService.eliminarRol(idUsuario, tipoRol);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/usuario/{idUsuario}/todos-roles")
    public ResponseEntity<Void> eliminarTodosLosRoles(@PathVariable Long idUsuario) {
        usuarioRolService.eliminarTodosLosRoles(idUsuario);
        return ResponseEntity.noContent().build();
    }
}