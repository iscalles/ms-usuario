package ms_usuario.usuarioService.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_id")
    @SequenceGenerator(name = "seq_usuario_id", sequenceName = "SEQ_USUARIO_ID", allocationSize = 1)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "rut_usuario", nullable = false, unique = true, length = 12)
    private String rutUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 40)
    private String nombreUsuario;

    @Column(name = "primer_apellido_usuario", nullable = false, length = 40)
    private String primerApellidoUsuario;

    @Column(name = "segundo_apellido_usuario", length = 40)
    private String segundoApellidoUsuario;

    @Column(name = "correo_usuario", nullable = false, unique = true, length = 100)
    private String correoUsuario;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "fecha_nac_usuario")
    private Date fechaNacUsuario;

    // Lados inversos de las relaciones (mappedBy) — se ignoran en la serialización JSON
    // para evitar ciclos infinitos: Estudiante→Usuario→Estudiante→...
    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Docente docente;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Estudiante estudiante;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Apoderado apoderado;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Administrativo administrativo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UsuarioRol> roles;

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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public Administrativo getAdministrativo() {
        return administrativo;
    }

    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }

    public Set<UsuarioRol> getRoles() {
        return roles;
    }

    public void setRoles(Set<UsuarioRol> roles) {
        this.roles = roles;
    }
}
