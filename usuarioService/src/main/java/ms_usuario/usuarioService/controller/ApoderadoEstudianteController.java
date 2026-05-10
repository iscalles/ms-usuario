package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.dto.ApoderadoEstudianteDTO;
import ms_usuario.usuarioService.service.ApoderadoEstudianteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apoderado-estudiante")
public class ApoderadoEstudianteController {
    private final ApoderadoEstudianteService apoderadoEstudianteService;

    public ApoderadoEstudianteController(ApoderadoEstudianteService apoderadoEstudianteService) {
        this.apoderadoEstudianteService = apoderadoEstudianteService;
    }

    @GetMapping("/apoderado/{idApoderado}/estudiantes")
    public ResponseEntity<List<ApoderadoEstudiante>> obtenerEstudiantesDelApoderado(@PathVariable Long idApoderado) {
        return ResponseEntity.ok(apoderadoEstudianteService.obtenerEstudiantesDelApoderado(idApoderado));
    }

    @GetMapping("/estudiante/{idEstudiante}/apoderados")
    public ResponseEntity<List<ApoderadoEstudiante>> obtenerApoderadosDelEstudiante(@PathVariable Long idEstudiante) {
        return ResponseEntity.ok(apoderadoEstudianteService.obtenerApoderadosDelEstudiante(idEstudiante));
    }
    @GetMapping("/apoderado/{idApoderado}/estudiante/{idEstudiante}")
    public ResponseEntity<ApoderadoEstudiante> buscarRelacion(
            @PathVariable Long idApoderado,
            @PathVariable Long idEstudiante) {
        Optional<ApoderadoEstudiante> relacion = apoderadoEstudianteService.buscarRelacion(idApoderado, idEstudiante);
        return relacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ApoderadoEstudiante> crearRelacion(@Valid @RequestBody ApoderadoEstudianteDTO dto) {
        ApoderadoEstudiante relacionCreada = apoderadoEstudianteService.crearRelacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(relacionCreada);
    }

    @PutMapping("/apoderado/{idApoderado}/estudiante/{idEstudiante}/parentesco/{parentesco}")
    public ResponseEntity<ApoderadoEstudiante> actualizarRelacion(
            @PathVariable Long idApoderado,
            @PathVariable Long idEstudiante,
            @PathVariable String parentesco) {
        ApoderadoEstudiante relacionActualizada = apoderadoEstudianteService.actualizarRelacion(idApoderado, idEstudiante, parentesco);
        return ResponseEntity.ok(relacionActualizada);
    }

    @DeleteMapping("/apoderado/{idApoderado}/estudiante/{idEstudiante}")
    public ResponseEntity<Void> eliminarRelacion(
            @PathVariable Long idApoderado,
            @PathVariable Long idEstudiante) {
        apoderadoEstudianteService.eliminarRelacion(idApoderado, idEstudiante);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/existe/apoderado/{idApoderado}/estudiante/{idEstudiante}")
    public ResponseEntity<Boolean> existeRelacion(
            @PathVariable Long idApoderado,
            @PathVariable Long idEstudiante) {
        return ResponseEntity.ok(apoderadoEstudianteService.existeRelacion(idApoderado, idEstudiante));
    }
}
