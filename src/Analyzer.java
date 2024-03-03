import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {
    // Método para analizar el string de entrada
    public ArrayList<String> analyze(String input) {
        ArrayList<String> result = new ArrayList<>();
        
        // Patrón para operaciones aritméticas (ejemplo básico)
        Pattern arithmeticPattern = Pattern.compile("[-+*/0-9]+");

        // Verificar si es una operación aritmética
        Matcher arithmeticMatcher = arithmeticPattern.matcher(input);
        if (arithmeticMatcher.find()) {
            result.add("1"); // Identificador para operación aritmética
            for (char c : input.toCharArray()) {
                result.add(String.valueOf(c));
            }
            return result;
        }

        // Añadir más patrones y verificaciones según sea necesario




    
        // Si no coincide con ningún patrón
        result.add("0"); // Identificador para "no reconocido"
        for (char c : input.toCharArray()) {
            result.add(String.valueOf(c));
        }
        return result;
    }

}