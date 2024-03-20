/**
 * Clase que implementa la interfaz IFunction y proporciona la funcionalidad para definir variables en el entorno.
 */
public class SetQ implements IFunction {

    /**
     * Define una variable en el entorno con el valor proporcionado.
     * @param input La entrada que contiene la variable y su valor a definir.
     * @param env El entorno en el que se define la variable.
     * @return Un mensaje indicando la variable y su valor después de definirse.
     */
    @Override
    public String execute(String input, Environment env) {
        // Reducir el input de entrada
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
                
        if (tokens.length >= 3) {
            String key = tokens[1];
            String value = tokens[2];
            
            env.defineVariable(key, value);
            
            // Imprimir el array de tokens para depuración
            // System.out.println(Arrays.toString(tokens));
            
            return key + ": " + env.getStringVariable(key);
        } else {
            return "Error: input no válido.";
        }
    }
}
