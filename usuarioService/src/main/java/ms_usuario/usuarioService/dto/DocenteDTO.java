package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.*;

public class DocenteDTO {

    @NotBlank(message = "El RUT del usuario es requerido")
    private String usuarioRutUsuario;

    @Size(max = 200, message = "El título profesional no puede exceder 200 caracteres")
    private String tituloProfesionalDocente;

    @Size(max = 100, message = "La especialidad no puede exceder 100 caracteres")
    private String especialidadDocente;

    // Getters y Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }

    public String getTituloProfesionalDocente() {
        return tituloProfesionalDocente;
    }

    public void setTituloProfesionalDocente(String tituloProfesionalDocente) {
        this.tituloProfesionalDocente = tituloProfesionalDocente;
    }

    public String getEspecialidadDocente() {
        return especialidadDocente;
    }

    public void setEspecialidadDocente(String especialidadDocente) {
        this.especialidadDocente = especialidadDocente;
    }
}
