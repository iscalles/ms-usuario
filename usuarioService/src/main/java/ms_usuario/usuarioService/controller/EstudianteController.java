package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.dto.EstudianteDTO;
import ms_usuario.usuarioService.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(service.listarEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarEstudiantePorId(@PathVariable String id) {
        Estudiante estudiante = service.buscarEstudiantePorId(id);
        if (estudiante != null) {
            return ResponseEntity.ok(estudiante);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Estudiante> crearEstudiante(@Valid @RequestBody EstudianteDTO dto) {
        Estudiante estudianteCreado = service.crearEstudiante(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable String id, @Valid @RequestBody EstudianteDTO dto) {
        Estudiante estudianteActualizado = service.actualizarEstudiante(id, dto);
        if (estudianteActualizado != null) {
            return ResponseEntity.ok(estudianteActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable String id) {
        service.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}
