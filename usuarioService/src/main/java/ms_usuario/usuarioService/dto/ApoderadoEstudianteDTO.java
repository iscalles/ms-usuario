package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.*;

public class ApoderadoEstudianteDTO {

    @NotBlank(message = "El RUT del apoderado es requerido")
    private String apoderadoRutUsuario;

    @NotBlank(message = "El RUT del estudiante es requerido")
    private String estudianteRutUsuario;

    @NotBlank(message = "El parentesco es requerido")
    @Size(max = 40, message = "El parentesco no puede exceder 40 caracteres")
    private String parentescoApoderadoEstudiante;

    // Getters y Setters
    public String getApoderadoRutUsuario() {
        return apoderadoRutUsuario;
    }

    public void setApoderadoRutUsuario(String apoderadoRutUsuario) {
        this.apoderadoRutUsuario = apoderadoRutUsuario;
    }

    public String getEstudianteRutUsuario() {
        return estudianteRutUsuario;
    }

    public void setEstudianteRutUsuario(String estudianteRutUsuario) {
        this.estudianteRutUsuario = estudianteRutUsuario;
    }

    public String getParentescoApoderadoEstudiante() {
        return parentescoApoderadoEstudiante;
    }

    public void setParentescoApoderadoEstudiante(String parentescoApoderadoEstudiante) {
        this.parentescoApoderadoEstudiante = parentescoApoderadoEstudiante;
    }
}
