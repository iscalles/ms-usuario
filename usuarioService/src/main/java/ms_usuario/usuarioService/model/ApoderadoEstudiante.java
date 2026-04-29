package ms_usuario.usuarioService.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity  // mapea la tabla
@Table(name = "APODERADO_ESTUDIANTE")
public class ApoderadoEstudiante {

    @EmbeddedId  // ← Embebe la clave compuesta aquí
    private ApoderadoEstudianteId id;

    @Column(name = "parentesco_apoderado_estudiante", length = 40)
    private String parentescoApoderadoEstudiante;

    public ApoderadoEstudiante() {}

    public ApoderadoEstudiante(ApoderadoEstudianteId id, String parentescoApoderadoEstudiante) {
        this.id = id;
        this.parentescoApoderadoEstudiante = parentescoApoderadoEstudiante;
    }

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
}