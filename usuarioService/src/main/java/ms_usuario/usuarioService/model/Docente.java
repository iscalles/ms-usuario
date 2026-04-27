package ms_usuario.usuarioService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DOCENTE")
public class Docente {

    @Id
    @Column(name = "USUARIO_rut_usuario")
    private Integer usuarioRutUsuario;

    @Column(name = "titulo_profesional_docente", length = 200)
    private String tituloProfesionalDocente;

    @Column(name = "especialidad_docente", length = 100)
    private String especialidadDocente;

    // Constructores
    public Docente() {}

    public Docente(Integer usuarioRutUsuario, String tituloProfesionalDocente, String especialidadDocente) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.tituloProfesionalDocente = tituloProfesionalDocente;
        this.especialidadDocente = especialidadDocente;
    }

    // Getters y Setters
    public Integer getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(Integer usuarioRutUsuario) {
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

