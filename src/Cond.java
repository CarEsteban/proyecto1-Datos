import java.util.Arrays;

public class Cond implements IFunction  {
    //para que funcione el cond, he imprimir en pantalla se usa quote
    String operator, n1, n2;
    @Override
    public String execute(String input, Environment env) {
        // reducir el input de entrada
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        operator = tokens[1];
        n1 = tokens[2];
        n2 = tokens[3];
        if (tokens.length >= 3) {

            //Imprimir el array de tokens para depuración
            return Arrays.toString(tokens);
            
        } else {
            return "Error: input no válido.";
        }
    }
    
}
