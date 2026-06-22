package ms_usuario.usuarioService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ms-auth", url = "${ms-auth.url}")
public interface AuthClient {

    // Marca la cuenta de acceso del usuario como ELIMINADO en ms-auth y revoca sus refresh tokens.
    // Si el usuario nunca tuvo cuenta de acceso, ms-auth no hace nada (no es un error).
    @PutMapping("/cuenta-acceso/usuario/{idUsuario}/desactivar")
    void desactivarCuenta(@PathVariable("idUsuario") Long idUsuario);
}
