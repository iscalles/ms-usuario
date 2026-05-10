package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DocenteDTO {

    @NotNull(message = "El id del usuario es requerido")
    private Long idUsuario;

    @NotBlank(message = "El título profesional es requerido")
    @Size(max = 200)
    private String tituloProfesionalDocente;

    @NotBlank(message = "La especialidad es requerida")
    @Size(max = 100)
    private String especialidadDocente;

    private String estadoDocente = "ACTIVO";

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getEstadoDocente() {
        return estadoDocente;
    }

    public void setEstadoDocente(String estadoDocente) {
        this.estadoDocente = estadoDocente;
    }
}