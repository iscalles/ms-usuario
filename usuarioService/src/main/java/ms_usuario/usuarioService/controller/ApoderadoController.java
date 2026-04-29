package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.dto.ApoderadoDTO;
import ms_usuario.usuarioService.service.ApoderadoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/apoderado")
public class ApoderadoController {
    private final ApoderadoService service;

    public ApoderadoController(ApoderadoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Apoderado>> listarApoderados() {
        return ResponseEntity.ok(service.listarApoderados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apoderado> buscarApoderadoPorId(@PathVariable String id) {
        Apoderado apoderado = service.buscarApoderadoPorId(id);
        if (apoderado != null) {
            return ResponseEntity.ok(apoderado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Apoderado> crearApoderado(@Valid @RequestBody ApoderadoDTO dto) {
        Apoderado apoderadoCreado = service.crearApoderado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apoderadoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apoderado> actualizarApoderado(@PathVariable String id, @Valid @RequestBody ApoderadoDTO dto) {
        Apoderado apoderadoActualizado = service.actualizarApoderado(id, dto);
        if (apoderadoActualizado != null) {
            return ResponseEntity.ok(apoderadoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarApoderado(@PathVariable String id) {
        service.eliminarApoderado(id);
        return ResponseEntity.noContent().build();
    }
}
