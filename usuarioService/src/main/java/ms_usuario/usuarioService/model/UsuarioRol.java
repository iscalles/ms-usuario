package ms_usuario.usuarioService.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIO_ROL")
public class UsuarioRol {

    @EmbeddedId
    private UsuarioRolId id;

    @Column(name = "fecha_asignacion")
    private Date fechaAsignacion = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    // Constructores
    public UsuarioRol() {}

    public UsuarioRol(Long idUsuario, String tipoRol) {
        this.id = new UsuarioRolId(idUsuario, tipoRol);
        this.fechaAsignacion = new Date();
    }

    public UsuarioRol(UsuarioRolId id) {
        this.id = id;
        this.fechaAsignacion = new Date();
    }

    // Getters y Setters
    public UsuarioRolId getId() {
        return id;
    }

    public void setId(UsuarioRolId id) {
        this.id = id;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return id != null ? id.getIdUsuario() : null;
    }

    public String getTipoRol() {
        return id != null ? id.getTipoRol() : null;
    }
}