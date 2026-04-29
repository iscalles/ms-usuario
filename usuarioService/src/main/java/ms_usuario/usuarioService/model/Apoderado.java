package ms_usuario.usuarioService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "APODERADO")
public class Apoderado {
    @Id
    @Column(name = "USUARIO_rut_usuario")
    private String usuarioRutUsuario;

    @Column(name = "direccion_apoderado", length = 200)
    private String direccionApoderado;

    @Column(name = "telefono_apoderado", length = 15)
    private String telefonoApoderado;

    // Constructores
    public Apoderado() {}

    public Apoderado(String usuarioRutUsuario, String direccionApoderado, String telefonoApoderado) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.direccionApoderado = direccionApoderado;
        this.telefonoApoderado = telefonoApoderado;
    }

    // Getters y Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }

    public String getDireccionApoderado() {
        return direccionApoderado;
    }

    public void setDireccionApoderado(String direccionApoderado) {
        this.direccionApoderado = direccionApoderado;
    }

    public String getTelefonoApoderado() {
        return telefonoApoderado;
    }

    public void setTelefonoApoderado(String telefonoApoderado) {
        this.telefonoApoderado = telefonoApoderado;
    }
}
