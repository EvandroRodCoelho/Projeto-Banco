package utils;
public class Validador {
    public static boolean validarNumConta(String numConta) {
        boolean contemApenasNumeros = numConta.matches("[0-9]+");
        return contemApenasNumeros;
    }
    
    public static boolean validarNome(String nome) {
        boolean contemApenasLetras = nome.matches("[a-zA-Z ]{2,}");
        boolean naoContemNumeros = !nome.matches(".*\\d.*");
        boolean naoContemCaracteresEspecial = !nome.matches(".*[!@#$%¨&*()_+^{}:;\"'<>?,./].*");
        return contemApenasLetras && naoContemNumeros && naoContemCaracteresEspecial;
    }
    
    public static boolean validarSaldo(int saldo) {
        boolean saldoPositivo = saldo >= 0;
        return saldoPositivo;
    }
    public static boolean validaEmail(String email) {
        // Verifica se o email possui o formato adequado usando uma expressão regular
        String usernameRegex = "[A-Za-z0-9+_.-]+";
        String domainRegex = "[A-Za-z0-9.-]+";
        String regex = "^" + usernameRegex + "@" + domainRegex + "$";
        boolean formatoValido = email.matches(regex);
        return formatoValido;
    }

    public static boolean validaSenha(String password) {
        return password.length() > 3;
    }
}
