package ms_usuario.usuarioService.service.impl;

import ms_usuario.usuarioService.model.Apoderado;
import ms_usuario.usuarioService.dto.ApoderadoDTO;
import ms_usuario.usuarioService.repository.ApoderadoRepository;
import ms_usuario.usuarioService.service.ApoderadoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApoderadoServiceImpl implements ApoderadoService {
    private final ApoderadoRepository apoderadoRepository;

    public ApoderadoServiceImpl(ApoderadoRepository apoderadoRepository) {
        this.apoderadoRepository = apoderadoRepository;
    }

    @Override
    public List<Apoderado> listarApoderados() {
        return apoderadoRepository.findAll();
    }

    @Override
    public Apoderado buscarApoderadoPorId(String id) {
        return apoderadoRepository.findById(id).orElse(null);
    }

    @Override
    public Apoderado crearApoderado(ApoderadoDTO dto) {
        Apoderado apoderado = new Apoderado();
        apoderado.setUsuarioRutUsuario(dto.getUsuarioRutUsuario());
        apoderado.setDireccionApoderado(dto.getDireccionApoderado());
        apoderado.setTelefonoApoderado(dto.getTelefonoApoderado());

        return apoderadoRepository.save(apoderado);
    }
    @Override
    public Apoderado actualizarApoderado(String id, ApoderadoDTO dto) {
        Apoderado apoderadoExistente = apoderadoRepository.findById(id).orElse(null);
        if (apoderadoExistente != null) {
            apoderadoExistente.setDireccionApoderado(dto.getDireccionApoderado());
            apoderadoExistente.setTelefonoApoderado(dto.getTelefonoApoderado());

            return apoderadoRepository.save(apoderadoExistente);
        } else {
            throw new RuntimeException("Apoderado no encontrado con id: " + id);
        }
    }

    @Override
    public void eliminarApoderado(String id) {
        Apoderado apoderadoExistente = apoderadoRepository.findById(id).orElse(null);
        if (apoderadoExistente != null) {
            apoderadoRepository.delete(apoderadoExistente);
        } else {
            throw new RuntimeException("Apoderado no encontrado con id: " + id);
        }
    }
}
