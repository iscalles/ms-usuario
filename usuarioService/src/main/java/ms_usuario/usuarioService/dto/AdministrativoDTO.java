package ms_usuario.usuarioService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AdministrativoDTO {

    @NotNull(message = "El id del usuario es requerido")
    private Long idUsuario;

    @NotBlank(message = "El cargo es requerido")
    @Size(max = 100)
    private String cargoAdministrativo;

    @NotBlank(message = "El departamento es requerido")
    @Size(max = 100)
    private String departamentoAdministrativo;

    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCargoAdministrativo() {
        return cargoAdministrativo;
    }

    public void setCargoAdministrativo(String cargoAdministrativo) {
        this.cargoAdministrativo = cargoAdministrativo;
    }

    public String getDepartamentoAdministrativo() {
        return departamentoAdministrativo;
    }

    public void setDepartamentoAdministrativo(String departamentoAdministrativo) {
        this.departamentoAdministrativo = departamentoAdministrativo;
    }
}