package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.Docente;
import ms_usuario.usuarioService.dto.DocenteDTO;
import ms_usuario.usuarioService.service.DocenteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping()
    public ResponseEntity<List<Docente>> listarDocentes() {
        return ResponseEntity.ok(docenteService.listarDocentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscarDocentePorId(@PathVariable Long id) {
        Optional<Docente> docente = docenteService.buscarDocentePorId(id);
        return docente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Docente> buscarDocentePorIdUsuario(@PathVariable Long idUsuario) {
        Optional<Docente> docente = docenteService.buscarDocentePorIdUsuario(idUsuario);
        return docente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Docente>> obtenerDocentesPorEspecialidad(@PathVariable String especialidad) {
        return ResponseEntity.ok(docenteService.obtenerDocentesPorEspecialidad(especialidad));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Docente>> obtenerDocentesPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(docenteService.obtenerDocentesPorEstado(estado));
    }

    @PostMapping()
    public ResponseEntity<Docente> crearDocente(@Valid @RequestBody DocenteDTO dto) {
        Docente docenteCreado = docenteService.crearDocente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(docenteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizarDocente(
            @PathVariable Long id,
            @Valid @RequestBody DocenteDTO dto) {
        Docente docenteActualizado = docenteService.actualizarDocente(id, dto);
        return ResponseEntity.ok(docenteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verificar/usuario/{idUsuario}")
    public ResponseEntity<Boolean> esDocente(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(docenteService.esDocente(idUsuario));
    }
}
