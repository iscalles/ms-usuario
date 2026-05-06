package ms_usuario.usuarioService.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RutValidator.class)
@Documented
public @interface ValidRut {
    String message() default "Rut inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
