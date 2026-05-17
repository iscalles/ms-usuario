package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApoderadoEstudianteDTO {

    @NotNull(message = "El id del apoderado es requerido")
    private Long idApoderado;

    @NotNull(message = "El id del estudiante es requerido")
    private Long idEstudiante;

    @NotBlank(message = "El parentesco es requerido")
    @Size(max = 40)
    private String parentescoApoderadoEstudiante;

    // Getters y Setters
    public Long getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(Long idApoderado) {
        this.idApoderado = idApoderado;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getParentescoApoderadoEstudiante() {
        return parentescoApoderadoEstudiante;
    }

    public void setParentescoApoderadoEstudiante(String parentescoApoderadoEstudiante) {
        this.parentescoApoderadoEstudiante = parentescoApoderadoEstudiante;
    }
}