package ms_usuario.usuarioService.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Errores de negocio lanzados desde los servicios (duplicados, no encontrado, etc.)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());
        body.put("status", 400);
        return ResponseEntity.badRequest().body(body);
    }

    // Violación de restricción única en base de datos (ORA-00001, UK duplicado)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Ya existe un registro con esos datos. Verifica RUT, correo u otros campos únicos.");
        body.put("status", 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    // Errores de validación de campos (@Valid, @NotBlank, @Size, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(e -> "Campo '" + e.getField() + "': " + e.getDefaultMessage())
                .orElse("Error de validación en los datos enviados.");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", mensaje);
        body.put("status", 400);
        return ResponseEntity.badRequest().body(body);
    }
}
