package ms_usuario.usuarioService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.util.Date;

public class EstudianteDTO {

    @NotNull(message = "El id del usuario es requerido")
    private Long idUsuario;

    @NotNull(message = "La fecha de ingreso es requerida")
    @PastOrPresent(message = "La fecha de ingreso no puede ser futura")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaIngresoEstudiante;

    @NotBlank(message = "El estado del estudiante es requerido")
    @Size(max = 40)
    private String estadoEstudiante;

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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