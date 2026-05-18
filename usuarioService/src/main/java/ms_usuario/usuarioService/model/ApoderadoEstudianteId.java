package ms_usuario.usuarioService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable  // componente embebido (clave)
public class ApoderadoEstudianteId implements Serializable {

    // Guardan las PK de Apoderado y Estudiante (no ids de usuario)
    @Column(name = "id_apoderado")
    private Long idApoderado;

    @Column(name = "id_estudiante")
    private Long idEstudiante;

    public ApoderadoEstudianteId() {}

    public ApoderadoEstudianteId(Long idApoderado, Long idEstudiante) {
        this.idApoderado = idApoderado;
        this.idEstudiante = idEstudiante;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApoderadoEstudianteId)) return false;
        ApoderadoEstudianteId that = (ApoderadoEstudianteId) o;
        return Objects.equals(idApoderado, that.idApoderado) &&
                Objects.equals(idEstudiante, that.idEstudiante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApoderado, idEstudiante);
    }
}
