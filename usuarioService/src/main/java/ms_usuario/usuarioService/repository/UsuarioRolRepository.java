package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.UsuarioRol;
import ms_usuario.usuarioService.model.UsuarioRolId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {

    // Navega por la relación @ManyToOne usuario → idUsuario (funciona)
    List<UsuarioRol> findByUsuario_IdUsuario(Long idUsuario);

    // tipoRol está en la clave embebida @EmbeddedId id acceso vía Id_TipoRol
    Optional<UsuarioRol> findByUsuario_IdUsuarioAndId_TipoRol(Long idUsuario, String tipoRol);

    List<UsuarioRol> findById_TipoRol(String tipoRol);

    boolean existsByUsuario_IdUsuarioAndId_TipoRol(Long idUsuario, String tipoRol);

    void deleteByUsuario_IdUsuario(Long idUsuario);
}
