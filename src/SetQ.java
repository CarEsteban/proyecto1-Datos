//unicamente se usa para verificar que tiene el tokens de información
import java.util.Arrays;

public class SetQ implements IFunction {

    String key, value, chars; 


    @Override
    public String execute(String input, Environment env) {
        // reducir el input de entrada
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        chars = String.join(" ", tokens);
        int startIndex = 7;

        
        if (tokens.length >= 3) {
            String key = tokens[1];
            value = chars.substring(startIndex).trim();
            
            env.defineVariable(key, value);
            
            // Imprimir el array de tokens para depuración
            //System.out.println(Arrays.toString(tokens));
            
            return key+": "+env.getStringVariable(key);
        } else {
            return "Error: input no válido.";
        }
    }
        
        
}
