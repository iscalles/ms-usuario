package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.dto.EstudianteDTO;
import ms_usuario.usuarioService.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping()
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarEstudiantePorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.buscarEstudiantePorId(id);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Estudiante> buscarEstudiantePorIdUsuario(@PathVariable Long idUsuario) {
        Optional<Estudiante> estudiante = estudianteService.buscarEstudiantePorIdUsuario(idUsuario);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(estudianteService.obtenerEstudiantesPorEstado(estado));
    }

    @PostMapping()
    public ResponseEntity<Estudiante> crearEstudiante(@Valid @RequestBody EstudianteDTO dto) {
        Estudiante estudianteCreado = estudianteService.crearEstudiante(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @PathVariable Long id,
            @Valid @RequestBody EstudianteDTO dto) {
        Estudiante estudianteActualizado = estudianteService.actualizarEstudiante(id, dto);
        return ResponseEntity.ok(estudianteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verificar/usuario/{idUsuario}")
    public ResponseEntity<Boolean> esEstudiante(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(estudianteService.esEstudiante(idUsuario));
    }
}
