package ms_usuario.usuarioService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable  // componente embebido (clave)
public class ApoderadoEstudianteId implements Serializable {

    @Column(name = "id_apoderado")
    private Long apoderadoIdUsuario;

    @Column(name = "id_estudiante")
    private Long estudianteIdUsuario;

    public ApoderadoEstudianteId() {}

    public ApoderadoEstudianteId(Long apoderadoIdUsuario, Long estudianteIdUsuario) {
        this.apoderadoIdUsuario = apoderadoIdUsuario;
        this.estudianteIdUsuario = estudianteIdUsuario;
    }

    public Long getApoderadoIdUsuario() {
        return apoderadoIdUsuario;
    }

    public void setApoderadoIdUsuario(Long apoderadoIdUsuario) {
        this.apoderadoIdUsuario = apoderadoIdUsuario;
    }

    public Long getEstudianteIdUsuario() {
        return estudianteIdUsuario;
    }

    public void setEstudianteIdUsuario(Long estudianteIdUsuario) {
        this.estudianteIdUsuario = estudianteIdUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApoderadoEstudianteId)) return false;
        ApoderadoEstudianteId that = (ApoderadoEstudianteId) o;
        return Objects.equals(apoderadoIdUsuario, that.apoderadoIdUsuario) &&
                Objects.equals(estudianteIdUsuario, that.estudianteIdUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apoderadoIdUsuario, estudianteIdUsuario);
    }
}
