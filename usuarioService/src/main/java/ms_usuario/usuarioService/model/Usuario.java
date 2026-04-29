package ms_usuario.usuarioService.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIO")


public class Usuario {
    @Id
    @Column(name = "rut_usuario")
    private String rutUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 40)
    private String nombreUsuario;

    @Column(name = "primer_apellido_usuario", nullable = false, length = 40)
    private String primerApellidoUsuario;

    @Column(name = "segundo_apellido_usuario", length = 40)
    private String segundoApellidoUsuario;

    @Column(name = "correo_usuario", nullable = false, unique = true, length = 100)
    private String correoUsuario;

    @Column(name = "fecha_nac_usuario")
    private Date fechaNacUsuario;

    // Constructores
    public Usuario() {}

    public Usuario(String rutUsuario, String nombreUsuario, String primerApellidoUsuario,
                   String segundoApellidoUsuario, String correoUsuario, Date fechaNacUsuario) {
        this.rutUsuario = rutUsuario;
        this.nombreUsuario = nombreUsuario;
        this.primerApellidoUsuario = primerApellidoUsuario;
        this.segundoApellidoUsuario = segundoApellidoUsuario;
        this.correoUsuario = correoUsuario;
        this.fechaNacUsuario = fechaNacUsuario;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPrimerApellidoUsuario() {
        return primerApellidoUsuario;
    }

    public void setPrimerApellidoUsuario(String primerApellidoUsuario) {
        this.primerApellidoUsuario = primerApellidoUsuario;
    }

    public String getSegundoApellidoUsuario() {
        return segundoApellidoUsuario;
    }

    public void setSegundoApellidoUsuario(String segundoApellidoUsuario) {
        this.segundoApellidoUsuario = segundoApellidoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public Date getFechaNacUsuario() {
        return fechaNacUsuario;
    }

    public void setFechaNacUsuario(Date fechaNacUsuario) {
        this.fechaNacUsuario = fechaNacUsuario;
    }
}
