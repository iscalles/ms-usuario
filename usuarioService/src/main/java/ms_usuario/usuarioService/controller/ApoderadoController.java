package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.dto.ApoderadoDTO;
import ms_usuario.usuarioService.service.ApoderadoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apoderado")
public class ApoderadoController {
    private final ApoderadoService apoderadoService;

    public ApoderadoController(ApoderadoService apoderadoService) {
        this.apoderadoService = apoderadoService;
    }

    @GetMapping()
    public ResponseEntity<List<Apoderado>> listarApoderados() {
        return ResponseEntity.ok(apoderadoService.listarApoderados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apoderado> buscarApoderadoPorId(@PathVariable Long id) {
        Optional<Apoderado> apoderado = apoderadoService.buscarApoderadoPorId(id);
        return apoderado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Apoderado> buscarApoderadoPorIdUsuario(@PathVariable Long idUsuario) {
        Optional<Apoderado> apoderado = apoderadoService.buscarApoderadoPorIdUsuario(idUsuario);
        return apoderado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Apoderado> crearApoderado(@Valid @RequestBody ApoderadoDTO dto) {
        Apoderado apoderadoCreado = apoderadoService.crearApoderado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apoderadoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apoderado> actualizarApoderado(
            @PathVariable Long id,
            @Valid @RequestBody ApoderadoDTO dto) {
        Apoderado apoderadoActualizado = apoderadoService.actualizarApoderado(id, dto);
        return ResponseEntity.ok(apoderadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarApoderado(@PathVariable Long id) {
        apoderadoService.eliminarApoderado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verificar/usuario/{idUsuario}")
    public ResponseEntity<Boolean> esApoderado(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(apoderadoService.esApoderado(idUsuario));
    }
}
