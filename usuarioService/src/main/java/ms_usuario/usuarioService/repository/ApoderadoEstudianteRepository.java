package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.model.ApoderadoEstudianteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApoderadoEstudianteRepository extends JpaRepository<ApoderadoEstudiante, ApoderadoEstudianteId> {

    // Todos los estudiantes de un apoderado (por PK de apoderado)
    @Query("SELECT ae FROM ApoderadoEstudiante ae WHERE ae.id.idApoderado = :idApoderado")
    List<ApoderadoEstudiante> findByApoderado(@Param("idApoderado") Long idApoderado);

    // Todos los apoderados de un estudiante (por PK de estudiante)
    @Query("SELECT ae FROM ApoderadoEstudiante ae WHERE ae.id.idEstudiante = :idEstudiante")
    List<ApoderadoEstudiante> findByEstudiante(@Param("idEstudiante") Long idEstudiante);

    // Una relación puntual
    @Query("SELECT ae FROM ApoderadoEstudiante ae WHERE ae.id.idApoderado = :idApoderado AND ae.id.idEstudiante = :idEstudiante")
    Optional<ApoderadoEstudiante> findRelacion(@Param("idApoderado") Long idApoderado,
                                               @Param("idEstudiante") Long idEstudiante);
}
