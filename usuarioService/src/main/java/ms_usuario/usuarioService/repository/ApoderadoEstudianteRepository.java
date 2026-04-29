package ms_usuario.usuarioService.repository;

import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.model.ApoderadoEstudianteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApoderadoEstudianteRepository extends JpaRepository<ApoderadoEstudiante, ApoderadoEstudianteId> {
    // Buscar todos los estudiantes de un apoderado
    List<ApoderadoEstudiante> findByIdApoderadoRutUsuario(String apoderadoRutUsuario);

    // Buscar todos los apoderados de un estudiante
    List<ApoderadoEstudiante> findByIdEstudianteRutUsuario(String estudianteRutUsuario);
}
