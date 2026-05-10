package ms_usuario.usuarioService.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "APODERADO")
public class Apoderado {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_apoderado_id")
    @SequenceGenerator(name = "seq_apoderado_id", sequenceName = "SEQ_APODERADO_ID", allocationSize = 1)
    @Column(name = "id_apoderado")
    private Long idApoderado;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "direccion_apoderado", length = 200)
    private String direccionApoderado;

    @Column(name = "telefono_apoderado", length = 15)
    private String telefonoApoderado;

    @OneToMany(mappedBy = "apoderado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ApoderadoEstudiante> estudiantes;

    // Constructores
    public Apoderado() {}

    public Apoderado(Usuario usuario, String direccionApoderado, String telefonoApoderado) {
        this.usuario = usuario;
        this.direccionApoderado = direccionApoderado;
        this.telefonoApoderado = telefonoApoderado;
    }

    // Getters y Setters
    public Long getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(Long idApoderado) {
        this.idApoderado = idApoderado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Set<ApoderadoEstudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<ApoderadoEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}