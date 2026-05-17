package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByUsuario_IdUsuario(Long idUsuario);
    List<Estudiante> findByEstadoEstudiante(String estado);
    boolean existsByUsuario_IdUsuario(Long idUsuario);
}
