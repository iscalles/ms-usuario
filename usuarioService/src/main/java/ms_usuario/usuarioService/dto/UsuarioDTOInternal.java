package ms_usuario.usuarioService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Set;

/**
 * DTO interno para Usuario
 * Expone RUT (información sensible)
 * Usado SOLO internamente en MS_USUARIO o endpoints internos
 */
public class UsuarioDTOInternal {

    private Long idUsuario;

    private String rutUsuario;

    private String nombreUsuario;

    private String primerApellidoUsuario;

    private String segundoApellidoUsuario;

    private String correoUsuario;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaNacUsuario;

    private Set<String> roles;

    // Constructores
    public UsuarioDTOInternal() {}

    public UsuarioDTOInternal(Long idUsuario, String rutUsuario, String nombreUsuario,
                              String primerApellidoUsuario, String segundoApellidoUsuario,
                              String correoUsuario, Date fechaNacUsuario) {
        this.idUsuario = idUsuario;
        this.rutUsuario = rutUsuario;
        this.nombreUsuario = nombreUsuario;
        this.primerApellidoUsuario = primerApellidoUsuario;
        this.segundoApellidoUsuario = segundoApellidoUsuario;
        this.correoUsuario = correoUsuario;
        this.fechaNacUsuario = fechaNacUsuario;
    }

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}