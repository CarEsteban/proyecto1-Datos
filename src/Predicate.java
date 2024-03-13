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
        // Aquí se implementa la lógica para verificar si es un átomo
        return (argument.matches("^\\d+$") || argument.matches("^[a-zA-Z]+$")) ? "true" : "false";
    }

    private String evalList(String argument, Environment env) {
        // Aquí se implementa la lógica para verificar si es una lista
        return argument.startsWith("(") && argument.endsWith(")") ? "true" : "false";
    }

    private String evalEqual(String arguments, Environment env) {
        // Aquí se implementa la lógica para comparar dos elementos
        String[] parts = arguments.split("\\s+", 2);
        return parts[0].equals(parts[1]) ? "true" : "false";
    }

    private String evalLessThan(String arguments, Environment env) {
        // Aquí se implementa la lógica para comparar números
        String[] parts = arguments.split("\\s+");
        try {
            return Integer.parseInt(parts[0]) < Integer.parseInt(parts[1]) ? "true" : "false";
        } catch (NumberFormatException e) {
            return "Error: Uno de los operandos no es un número válido";
        }
    }

    private String evalGreaterThan(String arguments, Environment env) {
        // Aquí se implementa la lógica para comparar números
        String[] parts = arguments.split("\\s+");
        try {
            return Integer.parseInt(parts[0]) > Integer.parseInt(parts[1]) ? "true" : "false";
        } catch (NumberFormatException e) {
            return "Error: Uno de los operandos no es un número válido";
        }
    }
}