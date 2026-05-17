package ms_usuario.usuarioService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "APODERADO_ESTUDIANTE")
public class ApoderadoEstudiante {

    @EmbeddedId
    private ApoderadoEstudianteId id;

    @Column(name = "parentesco_apoderado_estudiante", length = 40)
    private String parentescoApoderadoEstudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_apoderado", insertable = false, updatable = false)
    private Apoderado apoderado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", insertable = false, updatable = false)
    private Estudiante estudiante;

    // Constructores
    public ApoderadoEstudiante() {}

    public ApoderadoEstudiante(ApoderadoEstudianteId id, String parentescoApoderadoEstudiante) {
        this.id = id;
        this.parentescoApoderadoEstudiante = parentescoApoderadoEstudiante;
    }

    // Getters y Setters
    public ApoderadoEstudianteId getId() {
        return id;
    }

    public void setId(ApoderadoEstudianteId id) {
        this.id = id;
    }

    public String getParentescoApoderadoEstudiante() {
        return parentescoApoderadoEstudiante;
    }

    public void setParentescoApoderadoEstudiante(String parentescoApoderadoEstudiante) {
        this.parentescoApoderadoEstudiante = parentescoApoderadoEstudiante;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}