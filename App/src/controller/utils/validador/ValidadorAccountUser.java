package controller.utils.validador;

public class ValidadorAccountUser {

    public static void validateName(String name) throws ValidationException {
        boolean containsOnlyLetters = name.matches("[a-zA-Z ]{2,}");
        boolean doesNotContainNumbers = !name.matches(".*\\d.*");
        boolean doesNotContainSpecialCharacters = !name.matches(".*[!@#$%¨&*()_+^{}:;\"'<>?,./].*");
    
        if(name.length() < 2)
            throw new ValidationException("Nome invalido:Deve conter mais dois caracteres");
        if (!containsOnlyLetters || !doesNotContainNumbers || !doesNotContainSpecialCharacters)
            throw new ValidationException("Nome invalido:Não pode conter letras e caracteres especiais.");

    }
        
    public static void validateEmail(String email) throws ValidationException {
        String usernameRegex = "[A-Za-z0-9+_.-]+";
        String domainRegex = "[A-Za-z0-9.-]+";
        String regex = "^" + usernameRegex + "@" + domainRegex + "$";
        boolean validFormat = email.matches(regex);

        if (!validFormat || email.length() == 1) {
            throw new ValidationException("Email inválido. O email deve estar no formato correto (exemplo: usuario@dominio.com).");
        }
    }

    public static void validatePassword(String password) throws ValidationException {
        if (password.length() <= 3) {
            throw new ValidationException("Senha inválida. A senha deve conter no mínimo 4 caracteres.");
        }
    }

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}
