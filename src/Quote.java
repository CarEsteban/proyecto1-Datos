public class Quote implements IFunction {

    
    /**
     * Método para ejecutar la función quote.
     * @param input Cadena de entrada que contiene la expresión a citar.
     * @param env Entorno en el que se ejecuta la función, proporcionando acceso a las variables.
     * @return Una cadena que representa la expresión citada o "undefined" si la entrada no es válida.
     */
    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");

        if (tokens.length == 2) {
            String quoted = tokens[1];

            if(env.variableExists(quoted)){
                return env.getStringVariable(quoted);
            }else{
                return quoted;
            }

        } else {
            return "undefined";
        }
    }
}
