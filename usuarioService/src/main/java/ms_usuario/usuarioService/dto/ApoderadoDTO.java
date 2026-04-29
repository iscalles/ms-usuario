package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.*;

public class ApoderadoDTO {

    @NotBlank(message = "El RUT del usuario es requerido")
    private String usuarioRutUsuario;

    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccionApoderado;

    @Size(max = 15, message = "El teléfono no puede exceder 15 caracteres")
    @Pattern(regexp = "^[0-9+\\-()\\s]*$", message = "El teléfono debe contener solo números y caracteres válidos")
    private String telefonoApoderado;

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
