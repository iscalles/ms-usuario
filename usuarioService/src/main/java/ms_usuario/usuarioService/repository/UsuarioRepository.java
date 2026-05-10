package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByRutUsuario(String rutUsuario);

    Optional<Usuario> findByCorreoUsuario(String correoUsuario);

    boolean existsByRutUsuario(String rutUsuario);

    boolean existsByCorreoUsuario(String correoUsuario);
}
