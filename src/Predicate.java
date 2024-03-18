public class Predicate implements IFunction {

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
            default:
                return "Error: Comando desconocido.";
        }
    }

    private String evalAtom(String argument, Environment env) {
        // Verificar si el argumento es una referencia de variable
        String value = resolveVariable(argument, env);
        // Verificar si el valor resuelto es un átomo
        return (value.matches("^\\d+$") || value.matches("^[a-zA-Z]+$")) ? "true" : "false";
    }

    private String evalList(String argument, Environment env) {
        // Verificar si el argumento es una referencia de variable
        String value = resolveVariable(argument, env);
        // Verificar si el valor resuelto es una lista
        return value.startsWith("(") && value.endsWith(")") ? "true" : "false";
    }

    private String evalEqual(String arguments, Environment env) {
        // Dividir los argumentos y resolverlos
        String[] parts = arguments.split("\\s+", 2);
        String firstValue = resolveVariable(parts[0], env);
        String secondValue = resolveVariable(parts[1], env);
        // Comparar los valores resueltos
        return firstValue.equals(secondValue) ? "true" : "false";
    }

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

    // Método auxiliar para resolver el valor real de un argumento, ya sea una
    // variable o un literal
    private String resolveVariable(String argument, Environment env) {
        // Verificar si el argumento es una variable y obtener su valor, si no, usar el
        // argumento literal
        return env.variableExists(argument) ? env.getVariable(argument) : argument;
    }
}
