package ms_usuario.usuarioService.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ESTUDIANTE")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_estudiante_id")
    @SequenceGenerator(name = "seq_estudiante_id", sequenceName = "SEQ_ESTUDIANTE_ID", allocationSize = 1)
    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "fecha_ingreso_estudiante")
    private Date fechaIngresoEstudiante;

    @Column(name = "estado_estudiante", length = 40)
    private String estadoEstudiante;

    // Constructores
    public Estudiante() {}

    public Estudiante(Usuario usuario, Date fechaIngresoEstudiante, String estadoEstudiante) {
        this.usuario = usuario;
        this.fechaIngresoEstudiante = fechaIngresoEstudiante;
        this.estadoEstudiante = estadoEstudiante;
    }

    // Getters y Setters
    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Long idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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