package ms_usuario.usuarioService.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RutValidator implements ConstraintValidator<ValidRut, String> {

    @Override
    public void initialize(ValidRut constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Formato esperado: xx.xxx.xxx-x
        if (!value.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9k]$")) {
            addConstraintViolation(context, "El RUT debe tener formato: xx.xxx.xxx-x");
            return false;
        }

        // Validar dígito verificador
        String rutSinFormato = value.replace(".", "").replace("-", "");
        return validarDigitoVerificador(rutSinFormato, context);
    }

    private boolean validarDigitoVerificador(String rut, ConstraintValidatorContext context) {
        if (rut.length() < 2) {
            addConstraintViolation(context, "El RUT debe tener al menos 2 dígitos");
            return false;
        }

        String numero = rut.substring(0, rut.length() - 1);
        String digitoVerificador = rut.substring(rut.length() - 1).toUpperCase();

        try {
            int num = Integer.parseInt(numero);
            String digitoEsperado = calcularDigitoVerificador(num);

            if (!digitoEsperado.equals(digitoVerificador)) {
                addConstraintViolation(context, "El dígito verificador del RUT es inválido");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            addConstraintViolation(context, "El RUT contiene caracteres no válidos");
            return false;
        }
    }

    private String calcularDigitoVerificador(int rut) {
        int suma = 0;
        int multiplicador = 2;

        while (rut > 0) {
            suma += (rut % 10) * multiplicador;
            rut /= 10;
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }

        int digito = 11 - (suma % 11);

        if (digito == 11) {
            return "0";
        } else if (digito == 10) {
            return "k";
        } else {
            return String.valueOf(digito);
        }
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String mensaje) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(mensaje)
                .addConstraintViolation();
    }
}