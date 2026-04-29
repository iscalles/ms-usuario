package ms_usuario.usuarioService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable  // componente embebido (clave)
public class ApoderadoEstudianteId implements Serializable {

    @Column(name = "APODERADO_USUARIO_rut_usuario")
    private String apoderadoRutUsuario;

    @Column(name = "ESTUDIANTE_USUARIO_rut_usuario")
    private String estudianteRutUsuario;

    public ApoderadoEstudianteId() {}

    public ApoderadoEstudianteId(String apoderadoRutUsuario, String estudianteRutUsuario) {
        this.apoderadoRutUsuario = apoderadoRutUsuario;
        this.estudianteRutUsuario = estudianteRutUsuario;
    }

    // Getters y Setters...

    public String getApoderadoRutUsuario() {
        return apoderadoRutUsuario;
    }

    public void setApoderadoRutUsuario(String apoderadoRutUsuario) {
        this.apoderadoRutUsuario = apoderadoRutUsuario;
    }

    public String getEstudianteRutUsuario() {
        return estudianteRutUsuario;
    }

    public void setEstudianteRutUsuario(String estudianteRutUsuario) {
        this.estudianteRutUsuario = estudianteRutUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApoderadoEstudianteId)) return false;
        ApoderadoEstudianteId that = (ApoderadoEstudianteId) o;
        return Objects.equals(apoderadoRutUsuario, that.apoderadoRutUsuario) &&
                Objects.equals(estudianteRutUsuario, that.estudianteRutUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apoderadoRutUsuario, estudianteRutUsuario);
    }
}
