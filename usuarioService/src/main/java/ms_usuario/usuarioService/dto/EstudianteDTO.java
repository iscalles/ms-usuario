package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.*;
import ms_usuario.usuarioService.validation.ValidRut;
import java.util.Date;

public class EstudianteDTO {

    @NotBlank(message = "El RUT del usuario es requerido")
    @ValidRut(message = "El RUT debe tener formato válido: xx.xxx.xxx-x")
    private String usuarioRutUsuario;

    @NotNull(message = "La fecha de ingreso es requerida")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    private Date fechaIngresoEstudiante;

    @NotBlank(message = "El estado del estudiante es requerido")
    @Size(max = 40, message = "El estado no puede exceder 40 caracteres")
    private String estadoEstudiante;

    // Getters y Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }

    public Date getFechaIngresoEstudiante() {
        return fechaIngresoEstudiante;
    }

    public void setFechaIngresoEstudiante(Date fechaIngresoEstudiante) {
        this.fechaIngresoEstudiante = fechaIngresoEstudiante;
    }

    public String getEstadoEstudiante() {
        return estadoEstudiante;
    }

    public void setEstadoEstudiante(String estadoEstudiante) {
        this.estadoEstudiante = estadoEstudiante;
    }
}
