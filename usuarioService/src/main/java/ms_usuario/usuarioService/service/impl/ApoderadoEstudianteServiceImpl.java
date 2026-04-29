package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.model.ApoderadoEstudiante;
import ms_usuario.usuarioService.model.ApoderadoEstudianteId;
import ms_usuario.usuarioService.dto.ApoderadoEstudianteDTO;
import ms_usuario.usuarioService.repository.ApoderadoEstudianteRepository;
import ms_usuario.usuarioService.service.ApoderadoEstudianteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApoderadoEstudianteServiceImpl implements ApoderadoEstudianteService {
    private final ApoderadoEstudianteRepository repository;

    public ApoderadoEstudianteServiceImpl(ApoderadoEstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ApoderadoEstudiante> listarRelaciones() {
        return repository.findAll();
    }

    @Override
    public ApoderadoEstudiante buscarRelacion(String apoderadoRut, String estudianteRut) {
        ApoderadoEstudianteId id = new ApoderadoEstudianteId(apoderadoRut, estudianteRut);
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ApoderadoEstudiante> buscarEstudiantesPorApoderado(String apoderadoRut) {
        return repository.findByIdApoderadoRutUsuario(apoderadoRut);
    }

    @Override
    public List<ApoderadoEstudiante> buscarApoderadosPorEstudiante(String estudianteRut) {
        return repository.findByIdEstudianteRutUsuario(estudianteRut);
    }

    @Override
    public ApoderadoEstudiante crearRelacion(ApoderadoEstudianteDTO dto) {
        ApoderadoEstudianteId id = new ApoderadoEstudianteId(
                dto.getApoderadoRutUsuario(),
                dto.getEstudianteRutUsuario()
        );

        ApoderadoEstudiante relacion = new ApoderadoEstudiante();
        relacion.setId(id);
        relacion.setParentescoApoderadoEstudiante(dto.getParentescoApoderadoEstudiante());

        return repository.save(relacion);
    }

    @Override
    public ApoderadoEstudiante actualizarRelacion(String apoderadoRut, String estudianteRut, ApoderadoEstudianteDTO dto) {
        ApoderadoEstudianteId id = new ApoderadoEstudianteId(apoderadoRut, estudianteRut);
        ApoderadoEstudiante relacionExistente = repository.findById(id).orElse(null);

        if (relacionExistente != null) {
            relacionExistente.setParentescoApoderadoEstudiante(dto.getParentescoApoderadoEstudiante());
            return repository.save(relacionExistente);
        } else {
            throw new RuntimeException("Relación no encontrada entre apoderado: " + apoderadoRut + " y estudiante: " + estudianteRut);
        }
    }

    @Override
    public void eliminarRelacion(String apoderadoRut, String estudianteRut) {
        ApoderadoEstudianteId id = new ApoderadoEstudianteId(apoderadoRut, estudianteRut);
        ApoderadoEstudiante relacionExistente = repository.findById(id).orElse(null);

        if (relacionExistente != null) {
            repository.delete(relacionExistente);
        } else {
            throw new RuntimeException("Relación no encontrada entre apoderado: " + apoderadoRut + " y estudiante: " + estudianteRut);
        }
    }
}