package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocenteRepository extends  JpaRepository<Docente, Long> {
    Optional<Docente> findByUsuario_IdUsuario(Long idUsuario);
    List<Docente> findByEspecialidadDocente(String especialidad);
    List<Docente> findByEstadoDocente(String estado);
    boolean existsByUsuario_IdUsuario(Long idUsuario);
}
