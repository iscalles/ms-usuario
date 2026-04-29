package ms_usuario.usuarioService.controller;

import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.dto.ApoderadoEstudianteDTO;
import ms_usuario.usuarioService.service.ApoderadoEstudianteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/apoderado-estudiante")
public class ApoderadoEstudianteController {
    private final ApoderadoEstudianteService service;

    public ApoderadoEstudianteController(ApoderadoEstudianteService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<ApoderadoEstudiante>> listarRelaciones() {
        return ResponseEntity.ok(service.listarRelaciones());
    }
    @GetMapping("/{apoderadoRut}/{estudianteRut}")
    public ResponseEntity<ApoderadoEstudiante> buscarRelacion(
            @PathVariable String apoderadoRut,
            @PathVariable String estudianteRut) {
        ApoderadoEstudiante relacion = service.buscarRelacion(apoderadoRut, estudianteRut);
        if (relacion != null) {
            return ResponseEntity.ok(relacion);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/apoderado/{apoderadoRut}")
    public ResponseEntity<List<ApoderadoEstudiante>> buscarEstudiantesPorApoderado(
            @PathVariable String apoderadoRut) {
        List<ApoderadoEstudiante> estudiantes = service.buscarEstudiantesPorApoderado(apoderadoRut);
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/estudiante/{estudianteRut}")
    public ResponseEntity<List<ApoderadoEstudiante>> buscarApoderadosPorEstudiante(
            @PathVariable String estudianteRut) {
        List<ApoderadoEstudiante> apoderados = service.buscarApoderadosPorEstudiante(estudianteRut);
        return ResponseEntity.ok(apoderados);
    }
    @PostMapping()
    public ResponseEntity<ApoderadoEstudiante> crearRelacion(@Valid @RequestBody ApoderadoEstudianteDTO dto) {
        ApoderadoEstudiante relacionCreada = service.crearRelacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(relacionCreada);
    }

    @PutMapping("/{apoderadoRut}/{estudianteRut}")
    public ResponseEntity<ApoderadoEstudiante> actualizarRelacion(
            @PathVariable String apoderadoRut,
            @PathVariable String estudianteRut,
            @Valid @RequestBody ApoderadoEstudianteDTO dto) {
        ApoderadoEstudiante relacionActualizada = service.actualizarRelacion(apoderadoRut, estudianteRut, dto);
        if (relacionActualizada != null) {
            return ResponseEntity.ok(relacionActualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{apoderadoRut}/{estudianteRut}")
    public ResponseEntity<Void> eliminarRelacion(
            @PathVariable String apoderadoRut,
            @PathVariable String estudianteRut) {
        service.eliminarRelacion(apoderadoRut, estudianteRut);
        return ResponseEntity.noContent().build();
    }
}
