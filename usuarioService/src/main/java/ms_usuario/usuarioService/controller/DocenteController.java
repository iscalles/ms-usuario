package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.Docente;
import ms_usuario.usuarioService.dto.DocenteDTO;
import ms_usuario.usuarioService.service.DocenteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/docente")
public class DocenteController {
    private final DocenteService service;

    public DocenteController(DocenteService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Docente>> listarDocentes() {
        return ResponseEntity.ok(service.listarDocentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> buscarDocentePorId(@PathVariable String id) {
        Docente docente = service.buscarDocentePorId(id);
        if (docente != null) {
            return ResponseEntity.ok(docente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Docente> crearDocente(@Valid @RequestBody DocenteDTO dto) {
        Docente docenteCreado = service.crearDocente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(docenteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizarDocente(@PathVariable String id, @Valid @RequestBody DocenteDTO dto) {
        Docente docenteActualizado = service.actualizarDocente(id, dto);
        if (docenteActualizado != null) {
            return ResponseEntity.ok(docenteActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable String id) {
        service.eliminarDocente(id);
        return ResponseEntity.noContent().build();
    }
}
