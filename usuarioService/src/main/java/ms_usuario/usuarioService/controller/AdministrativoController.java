package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.dto.AdministrativoDTO;
import ms_usuario.usuarioService.model.Administrativo;
import ms_usuario.usuarioService.service.AdministrativoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrativo")
public class AdministrativoController {

    private final AdministrativoService administrativoService;

    public AdministrativoController(AdministrativoService administrativoService) {
        this.administrativoService = administrativoService;
    }

    @GetMapping()
    public ResponseEntity<List<Administrativo>> listarAdministrativos() {
        return ResponseEntity.ok(administrativoService.listarAdministrativos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrativo> buscarAdministrativoPorId(@PathVariable Long id) {
        Optional<Administrativo> administrativo = administrativoService.buscarAdministrativoPorId(id);
        return administrativo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Administrativo> buscarAdministrativoPorIdUsuario(@PathVariable Long idUsuario) {
        Optional<Administrativo> administrativo = administrativoService.buscarAdministrativoPorIdUsuario(idUsuario);
        return administrativo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<List<Administrativo>> obtenerAdministrativosPorDepartamento(@PathVariable String departamento) {
        return ResponseEntity.ok(administrativoService.obtenerAdministrativosPorDepartamento(departamento));
    }

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<List<Administrativo>> obtenerAdministrativosPorCargo(@PathVariable String cargo) {
        return ResponseEntity.ok(administrativoService.obtenerAdministrativosPorCargo(cargo));
    }

    @PostMapping()
    public ResponseEntity<Administrativo> crearAdministrativo(@Valid @RequestBody AdministrativoDTO dto) {
        Administrativo administrativoCreado = administrativoService.crearAdministrativo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(administrativoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrativo> actualizarAdministrativo(
            @PathVariable Long id,
            @Valid @RequestBody AdministrativoDTO dto) {
        Administrativo administrativoActualizado = administrativoService.actualizarAdministrativo(id, dto);
        return ResponseEntity.ok(administrativoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrativo(@PathVariable Long id) {
        administrativoService.eliminarAdministrativo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verificar/usuario/{idUsuario}")
    public ResponseEntity<Boolean> esAdministrativo(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(administrativoService.esAdministrativo(idUsuario));
    }
}
