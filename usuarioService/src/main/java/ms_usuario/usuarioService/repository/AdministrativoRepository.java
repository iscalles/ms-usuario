package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministrativoRepository extends JpaRepository<Administrativo, Long> {

    Optional<Administrativo> findByUsuario_IdUsuario(Long idUsuario);
    List<Administrativo> findByDepartamentoAdministrativo(String departamento);
    List<Administrativo> findByCargoAdministrativo(String cargo);
    boolean existsByUsuario_IdUsuario(Long idUsuario);
}