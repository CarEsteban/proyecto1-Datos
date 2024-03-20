public class Quote implements IFunction {

    @Override
    public String execute(String input, Environment env) {
        // Elimina los paréntesis exteriores y divide la entrada en tokens
        String[] tokens = input.trim().replaceAll("^\\(|\\)$", "").trim().split("\\s+", 2);

        if (tokens.length >= 2) {
            // El primer token es "quote", así que todo lo demás debe ser devuelto como está
            // Comprueba si después de "quote" hay más tokens y devuélvelos tal cual
            String quotedContent = tokens[1]; // Obtiene todo lo que sigue después de "quote"

            return quotedContent; // Devuelve el contenido citado directamente
        } else {
            return "undefined"; // En caso de que la entrada no sea válida
        }
    }
}
