package ms_usuario.usuarioService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "DOCENTE")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_docente_id")
    @SequenceGenerator(name = "seq_docente_id", sequenceName = "SEQ_DOCENTE_ID", allocationSize = 1)
    @Column(name = "id_docente")
    private Long idDocente;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "titulo_profesional_docente", length = 200)
    private String tituloProfesionalDocente;

    @Column(name = "especialidad_docente", length = 100)
    private String especialidadDocente;

    @Column(name = "estado_docente", length = 20)
    private String estadoDocente = "ACTIVO";

    // Constructores
    public Docente() {}

    public Docente(Usuario usuario, String tituloProfesionalDocente, String especialidadDocente) {
        this.usuario = usuario;
        this.tituloProfesionalDocente = tituloProfesionalDocente;
        this.especialidadDocente = especialidadDocente;
        this.estadoDocente = "ACTIVO";
    }

    // Getters y Setters
    public Long getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Long idDocente) {
        this.idDocente = idDocente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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