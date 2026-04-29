package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.Apoderado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApoderadoRepository extends JpaRepository<Apoderado, String> {
}
