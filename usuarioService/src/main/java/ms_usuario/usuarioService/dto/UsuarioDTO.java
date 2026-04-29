package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.*;
import java.util.Date;

public class UsuarioDTO {

    @NotBlank(message = "El RUT no puede estar vacío")
    private String rutUsuario;

    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 40)
    private String nombreUsuario;

    @NotBlank(message = "El primer apellido es requerido")
    @Size(min = 2, max = 40)
    private String primerApellidoUsuario;

    @Size(max = 40)
    private String segundoApellidoUsuario;

    @NotBlank(message = "El correo es requerido")
    @Email(message = "El correo debe ser válido")
    private String correoUsuario;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser futura")
    private Date fechaNacUsuario;

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
