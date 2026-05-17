package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ApoderadoDTO {

    @NotNull(message = "El id del usuario es requerido")
    private Long idUsuario;

    @NotBlank(message = "La dirección es requerida")
    @Size(max = 200)
    private String direccionApoderado;

    @NotBlank(message = "El teléfono es requerido")
    @Size(max = 15)
    private String telefonoApoderado;

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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