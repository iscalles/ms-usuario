package ms_usuario.usuarioService.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ESTUDIANTE")
public class Estudiante {
    @Id
    @Column(name = "USUARIO_rut_usuario")
    private String usuarioRutUsuario;

    @Column(name = "fecha_ingreso_estudiante")
    private Date fechaIngresoEstudiante;

    @Column(name = "estado_estudiante", length = 40)
    private String estadoEstudiante;

    // Constructores
    public Estudiante() {}

    public Estudiante(String usuarioRutUsuario, Date fechaIngresoEstudiante, String estadoEstudiante) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.fechaIngresoEstudiante = fechaIngresoEstudiante;
        this.estadoEstudiante = estadoEstudiante;
    }

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
