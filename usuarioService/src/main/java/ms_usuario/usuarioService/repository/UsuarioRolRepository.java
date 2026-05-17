package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
    List<UsuarioRol> findByUsuario_IdUsuario(Long idUsuario);
    Optional<UsuarioRol> findByUsuario_IdUsuarioAndTipoRol(Long idUsuario, String tipoRol);
    List<UsuarioRol> findByTipoRol(String tipoRol);
    boolean existsByUsuario_IdUsuarioAndTipoRol(Long idUsuario, String tipoRol);
    void deleteByUsuario_IdUsuario(Long idUsuario);
}