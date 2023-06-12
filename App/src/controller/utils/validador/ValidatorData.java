package controller.utils.validador;

public class ValidatorData {
    public static void isValidNumber(String input) throws NumberFormatException {        
        if(!input.matches("\\d+")){
            throw new NumberFormatException();
        }
    }
}
