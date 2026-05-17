package ms_usuario.usuarioService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioRolId implements Serializable {

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "tipo_rol")
    private String tipoRol;

    public UsuarioRolId() {}

    public UsuarioRolId(Long idUsuario, String tipoRol) {
        this.idUsuario = idUsuario;
        this.tipoRol = tipoRol;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioRolId)) return false;
        UsuarioRolId that = (UsuarioRolId) o;
        return Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(tipoRol, that.tipoRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, tipoRol);
    }
}