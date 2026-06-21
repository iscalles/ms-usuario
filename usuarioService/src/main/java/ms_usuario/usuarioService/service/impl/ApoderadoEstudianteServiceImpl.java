package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.dto.ApoderadoEstudianteDTO;
import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.model.ApoderadoEstudianteId;
import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.model.Estudiante;
import ms_usuario.usuarioService.repository.ApoderadoEstudianteRepository;
import ms_usuario.usuarioService.repository.ApoderadoRepository;
import ms_usuario.usuarioService.repository.EstudianteRepository;
import ms_usuario.usuarioService.service.ApoderadoEstudianteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApoderadoEstudianteServiceImpl implements ApoderadoEstudianteService {

    private final ApoderadoEstudianteRepository apoderadoEstudianteRepository;
    private final ApoderadoRepository apoderadoRepository;
    private final EstudianteRepository estudianteRepository;

    public ApoderadoEstudianteServiceImpl(ApoderadoEstudianteRepository apoderadoEstudianteRepository,
                                          ApoderadoRepository apoderadoRepository,
                                          EstudianteRepository estudianteRepository) {
        this.apoderadoEstudianteRepository = apoderadoEstudianteRepository;
        this.apoderadoRepository = apoderadoRepository;
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<ApoderadoEstudiante> obtenerEstudiantesDelApoderado(Long idApoderado) {
        // Verificar que existe el apoderado
        apoderadoRepository.findById(idApoderado)
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con id: " + idApoderado));

        return apoderadoEstudianteRepository.findByApoderado(idApoderado);
    }

    @Override
    public List<ApoderadoEstudiante> obtenerApoderadosDelEstudiante(Long idEstudiante) {
        // Verificar que existe el estudiante
        estudianteRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + idEstudiante));

        return apoderadoEstudianteRepository.findByEstudiante(idEstudiante);
    }

    @Override
    public Optional<ApoderadoEstudiante> buscarRelacion(Long idApoderado, Long idEstudiante) {
        return apoderadoEstudianteRepository.findRelacion(idApoderado, idEstudiante);
    }

    @Override
    public ApoderadoEstudiante crearRelacion(ApoderadoEstudianteDTO dto) {
        // Verificar que existe apoderado
        apoderadoRepository.findById(dto.getIdApoderado())
                .orElseThrow(() -> new RuntimeException("Apoderado no encontrado con id: " + dto.getIdApoderado()));

        // Verificar que existe estudiante
        estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + dto.getIdEstudiante()));

        // Verificar que no existe ya la relación
        if (apoderadoEstudianteRepository.findRelacion(dto.getIdApoderado(), dto.getIdEstudiante()).isPresent()) {
            throw new RuntimeException("Ya existe relación apoderado-estudiante");
        }

        validarParentescoUnico(dto.getIdEstudiante(), dto.getParentescoApoderadoEstudiante(), null);

        // La clave compuesta usa las PK de apoderado y estudiante
        ApoderadoEstudianteId id = new ApoderadoEstudianteId(dto.getIdApoderado(), dto.getIdEstudiante());

        ApoderadoEstudiante relacion = new ApoderadoEstudiante();
        relacion.setId(id);
        relacion.setParentescoApoderadoEstudiante(dto.getParentescoApoderadoEstudiante());

        return apoderadoEstudianteRepository.save(relacion);
    }

    @Override
    public ApoderadoEstudiante actualizarRelacion(Long idApoderado, Long idEstudiante, String parentesco) {
        ApoderadoEstudiante relacion = apoderadoEstudianteRepository
                .findRelacion(idApoderado, idEstudiante)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));

        // Al cambiar el parentesco, excluye esta misma relación de la validación de unicidad
        validarParentescoUnico(idEstudiante, parentesco, idApoderado);

        relacion.setParentescoApoderadoEstudiante(parentesco);
        return apoderadoEstudianteRepository.save(relacion);
    }

    // Un estudiante solo puede tener un apoderado con parentesco "Padre" y uno con "Madre".
    // idApoderadoExcluir se usa al actualizar, para no comparar la relación contra sí misma.
    private void validarParentescoUnico(Long idEstudiante, String parentesco, Long idApoderadoExcluir) {
        if (parentesco == null) return;
        boolean esPadreOMadre = "Padre".equalsIgnoreCase(parentesco) || "Madre".equalsIgnoreCase(parentesco);
        if (!esPadreOMadre) return;

        boolean yaExiste = apoderadoEstudianteRepository.findByEstudiante(idEstudiante).stream()
                .filter(r -> !r.getId().getIdApoderado().equals(idApoderadoExcluir))
                .anyMatch(r -> parentesco.equalsIgnoreCase(r.getParentescoApoderadoEstudiante()));

        if (yaExiste) {
            throw new RuntimeException("Este estudiante ya tiene un apoderado registrado como " + parentesco + ".");
        }
    }

    @Override
    public void eliminarRelacion(Long idApoderado, Long idEstudiante) {
        ApoderadoEstudianteId id = new ApoderadoEstudianteId(idApoderado, idEstudiante);
        apoderadoEstudianteRepository.deleteById(id);
    }

    @Override
    public boolean existeRelacion(Long idApoderado, Long idEstudiante) {
        return apoderadoEstudianteRepository.findRelacion(idApoderado, idEstudiante).isPresent();
    }
}
