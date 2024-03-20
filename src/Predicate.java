/**
 * Clase que implementa la interfaz IFunction y proporciona funciones para evaluar predicados.
 */
public class Predicate implements IFunction {

    /**
     * Evalúa un predicado dado en función del comando proporcionado.
     * @param input La entrada que contiene el predicado a evaluar.
     * @param env El entorno en el que se evalúa el predicado.
     * @return El resultado de la evaluación del predicado como una cadena.
     */
    @Override
    public String execute(String input, Environment env) {
        String cleanedInput = input.trim().replaceAll("^\\(|\\)$", "").trim();
        String[] parts = cleanedInput.split("\\s+", 2);
        if (parts.length < 2) {
            return "Error: entrada no válida.";
        }

        String command = parts[0].toLowerCase();
        String arguments = parts[1];

        switch (command) {
            case "atom":
                return evalAtom(arguments, env);
            case "list":
                return evalList(arguments, env);
            case "equal":
                return evalEqual(arguments, env);
            case "<":
                return evalLessThan(arguments, env);
            case ">":
                return evalGreaterThan(arguments, env);
            case ">=":
                return evalGreaterThanOrEqual(arguments, env);
            case "<=":
                return evalLessThanOrEqual(arguments, env);
            default:
                return "Error: Comando desconocido.";
        }
    }

    /**
     * Evalúa si un argumento es un átomo.
     * @param argument El argumento a evaluar.
     * @param env El entorno en el que se evalúa el argumento.
     * @return "true" si el argumento es un átomo, "nil" si es una lista, "false" en caso contrario.
     */
    private String evalAtom(String argument, Environment env) {
        // Verificar si el argumento es una referencia de variable
        String value = resolveVariable(argument, env);
        // Verificar si el valor resuelto es un átomo. Devuelve "nil" en lugar de
        // "false" si es una lista.
        boolean isAtom = value.matches("^\\d+$") || value.matches("^[a-zA-Z]+$");
        boolean isList = value.startsWith("(") && value.endsWith(")");
        return isAtom ? "true" : (isList ? "nil" : "false");
    }

    /**
     * Evalúa si un argumento es una lista.
     * @param argument El argumento a evaluar.
     * @param env El entorno en el que se evalúa el argumento.
     * @return "true" si el argumento es una lista, "false" en caso contrario.
     */
    private String evalList(String argument, Environment env) {
        // Verificar si el argumento es una referencia de variable
        String value = resolveVariable(argument, env);
        // Verificar si el valor resuelto es una lista
        return value.startsWith("(") && value.endsWith(")") ? "true" : "false";
    }

    /**
     * Evalúa si dos argumentos son iguales.
     * @param arguments Los dos argumentos separados por un espacio.
     * @param env El entorno en el que se evalúan los argumentos.
     * @return "true" si los argumentos son iguales, "false" en caso contrario.
     */
    private String evalEqual(String arguments, Environment env) {
        // Dividir los argumentos y resolverlos
        String[] parts = arguments.split("\\s+", 2);
        String firstValue = resolveVariable(parts[0], env);
        String secondValue = resolveVariable(parts[1], env);
        // Comparar los valores resueltos
        return firstValue.equals(secondValue) ? "true" : "false";
    }

    /**
     * Evalúa si dos argumentos son iguales.
     * @param arguments Los dos argumentos separados por un espacio.
     * @param env El entorno en el que se evalúan los argumentos.
     * @return "true" si los argumentos son iguales, "false" en caso contrario.
     */
    private String evalLessThan(String arguments, Environment env) {
        // Dividir los argumentos y resolverlos
        String[] parts = arguments.split("\\s+");
        try {
            int firstValue = Integer.parseInt(resolveVariable(parts[0], env));
            int secondValue = Integer.parseInt(resolveVariable(parts[1], env));
            return firstValue < secondValue ? "true" : "false";
        } catch (NumberFormatException e) {
            return "Error: Uno de los operandos no es un número válido";
        }
    }

    /**
     * Evalúa si dos argumentos son iguales.
     * @param arguments Los dos argumentos separados por un espacio.
     * @param env El entorno en el que se evalúan los argumentos.
     * @return "true" si los argumentos son iguales, "false" en caso contrario.
     */
    private String evalGreaterThan(String arguments, Environment env) {
        // Dividir los argumentos y resolverlos
        String[] parts = arguments.split("\\s+");
        try {
            int firstValue = Integer.parseInt(resolveVariable(parts[0], env));
            int secondValue = Integer.parseInt(resolveVariable(parts[1], env));
            return firstValue > secondValue ? "true" : "false";
        } catch (NumberFormatException e) {
            return "Error: Uno de los operandos no es un número válido";
        }
    }

    
    /**
     * Evalúa si dos argumentos son iguales.
     * @param arguments Los dos argumentos separados por un espacio.
     * @param env El entorno en el que se evalúan los argumentos.
     * @return "true" si los argumentos son iguales, "false" en caso contrario.
     */
    private String evalGreaterThanOrEqual(String arguments, Environment env) {
        // Dividir los argumentos y resolverlos
        String[] parts = arguments.split("\\s+");
        try {
            int firstValue = Integer.parseInt(resolveVariable(parts[0], env));
            int secondValue = Integer.parseInt(resolveVariable(parts[1], env));
            return firstValue >= secondValue ? "true" : "false";
        } catch (NumberFormatException e) {
            return "Error: Uno de los operandos no es un número válido";
        }
    }

    /**
     * Evalúa si dos argumentos son iguales.
     * @param arguments Los dos argumentos separados por un espacio.
     * @param env El entorno en el que se evalúan los argumentos.
     * @return "true" si los argumentos son iguales, "false" en caso contrario.
     */
    private String evalLessThanOrEqual(String arguments, Environment env) {
        // Dividir los argumentos y resolverlos
        String[] parts = arguments.split("\\s+");
        try {
            int firstValue = Integer.parseInt(resolveVariable(parts[0], env));
            int secondValue = Integer.parseInt(resolveVariable(parts[1], env));
            return firstValue <= secondValue ? "true" : "false";
        } catch (NumberFormatException e) {
            return "Error: Uno de los operandos no es un número válido";
        }
    }

    /**
     * Método auxiliar para resolver el valor real de un argumento, ya sea una
     * variable o un literal.
     * @param argument El argumento a resolver.
     * @param env El entorno en el que se evalúa el argumento.
     * @return El valor real del argumento como una cadena.
     */
    // Método auxiliar para resolver el valor real de un argumento, ya sea una
    // variable o un literal
    private String resolveVariable(String argument, Environment env) {
        // Verificar si el argumento es una variable y obtener su valor, si no, usar el
        // argumento literal
        return env.variableExists(argument) ? env.getVariable(argument) : argument;
    }
}
