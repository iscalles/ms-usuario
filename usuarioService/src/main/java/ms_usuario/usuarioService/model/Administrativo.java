package ms_usuario.usuarioService.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ADMINISTRATIVO")
public class Administrativo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_administrativo_id")
    @SequenceGenerator(name = "seq_administrativo_id", sequenceName = "SEQ_ADMINISTRATIVO_ID", allocationSize = 1)
    @Column(name = "id_administrativo")
    private Long idAdministrativo;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "cargo_administrativo", length = 100)
    private String cargoAdministrativo;

    @Column(name = "departamento_administrativo", length = 100)
    private String departamentoAdministrativo;

    // Constructores
    public Administrativo() {}

    public Administrativo(Usuario usuario, String cargoAdministrativo, String departamentoAdministrativo) {
        this.usuario = usuario;
        this.cargoAdministrativo = cargoAdministrativo;
        this.departamentoAdministrativo = departamentoAdministrativo;
    }

    // Getters y Setters
    public Long getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(Long idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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