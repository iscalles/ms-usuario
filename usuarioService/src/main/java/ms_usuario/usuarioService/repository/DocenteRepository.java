package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends  JpaRepository<Docente, String>
{
}
