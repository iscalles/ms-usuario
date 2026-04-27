package ms_usuario.usuarioService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable  // ← Indica que es un componente embebido (clave)
public class ApoderadoEstudianteId implements Serializable {

    @Column(name = "APODERADO_USUARIO_rut_usuario")
    private Integer apoderadoRutUsuario;

    @Column(name = "ESTUDIANTE_USUARIO_rut_usuario")
    private Integer estudianteRutUsuario;

    public ApoderadoEstudianteId() {}

    public ApoderadoEstudianteId(Integer apoderadoRutUsuario, Integer estudianteRutUsuario) {
        this.apoderadoRutUsuario = apoderadoRutUsuario;
        this.estudianteRutUsuario = estudianteRutUsuario;
    }

    // Getters y Setters...

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
