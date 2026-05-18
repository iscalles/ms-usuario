package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.dto.UsuarioDTO;
import ms_usuario.usuarioService.dto.UsuarioDTOResponse;
import ms_usuario.usuarioService.dto.UsuarioDTOInternal;
import ms_usuario.usuarioService.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTOResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/interno")
    public ResponseEntity<List<UsuarioDTOInternal>> listarUsuariosInterno() {
        return ResponseEntity.ok(usuarioService.listarUsuariosInterno());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorId(@PathVariable Long id) {
        Optional <UsuarioDTOResponse> usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    /* endpoint interno para MS_AUTH, obtiene usuario completo por ID_USUARIO (incluye RUT y roles) GET /usuario/interno/{idUsuario}*/
    @GetMapping("/interno/{idUsuario}")
    public ResponseEntity<UsuarioDTOInternal> obtenerUsuarioInterno(@PathVariable Long idUsuario) {
        Optional<UsuarioDTOInternal> usuario = usuarioService.buscarUsuarioPorIdInterno(idUsuario);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/interno/rut/{rut}")
    public ResponseEntity<UsuarioDTOInternal> buscarUsuarioPorRut(@PathVariable String rut) {
        Optional<UsuarioDTOInternal> usuario = usuarioService.buscarUsuarioPorRut(rut);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/interno/correo/{correo}")
    public ResponseEntity<UsuarioDTOInternal> buscarUsuarioPorCorreo(@PathVariable String correo) {
        Optional<UsuarioDTOInternal> usuario = usuarioService.buscarUsuarioPorCorreo(correo);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTOResponse> crearUsuario(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTOResponse usuarioCreado = usuarioService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO dto) {
        UsuarioDTOResponse usuarioActualizado = usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existencia/rut/{rut}")
    public ResponseEntity<Boolean> existeUsuarioPorRut(@PathVariable String rut) {
        return ResponseEntity.ok(usuarioService.existeUsuarioPorRut(rut));
    }

    @GetMapping("/existencia/correo/{correo}")
    public ResponseEntity<Boolean> existeUsuarioPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(usuarioService.existeUsuarioPorCorreo(correo));
    }
}