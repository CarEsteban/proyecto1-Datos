import java.util.ArrayList;
import java.util.List;

public class ArithmeticOperations implements IFunction {
    public String execute(String input, Environment env) {
        input = input.trim();

        while (input.startsWith("(") && input.endsWith(")")
                && findClosingParenthesis(input.substring(1, input.length() - 1)) == input.length() - 2) {
            input = input.substring(1, input.length() - 1).trim();
        }

        try {
            int result = Integer.parseInt(input);
            return "Resultado de la operación: " + result;
        } catch (NumberFormatException ignored) {
            // Si no es un número, se intenta evaluar como una expresión
        }

        // Dividir la entrada en tokens, teniendo en cuenta expresiones dentro de
        // paréntesis
        String[] tokens = splitInput(input);

        // Verificar que hay suficientes tokens para realizar una operación
        if (tokens != null && tokens.length == 3) {
            String operator = tokens[0];

            // Comprobar si el operador es válido
            if ("+-*/".contains(operator)) {
                String operand1String = tokens[1];
                String operand2String = tokens[2];

                // Evaluar el primer operando, que puede ser una expresión en sí
                String evalOperand1 = operand1String.matches("\\d+") ? operand1String : execute(operand1String, env);
                if (evalOperand1.startsWith("Error"))
                    return evalOperand1;
                int operand1 = Integer.parseInt(evalOperand1.replaceAll("Resultado de la operación: ", ""));

                // Evaluar el segundo operando, que también puede ser una expresión
                String evalOperand2 = operand2String.matches("\\d+") ? operand2String : execute(operand2String, env);
                if (evalOperand2.startsWith("Error"))
                    return evalOperand2;
                int operand2 = Integer.parseInt(evalOperand2.replaceAll("Resultado de la operación: ", ""));

                // Realizar la operación aritmética
                switch (operator) {
                    case "+":
                        return "Resultado de la operación: " + (operand1 + operand2);
                    case "-":
                        return "Resultado de la operación: " + (operand1 - operand2);
                    case "*":
                        return "Resultado de la operación: " + (operand1 * operand2);
                    case "/":
                        if (operand2 == 0)
                            return "Error: División por cero";
                        return "Resultado de la operación: " + (operand1 / operand2);
                    default:
                        return "Error: Operador no válido";
                }
            } else {
                return "Error: Operador no válido";
            }
        } else {
            return "Error en la expresión aritmética";
        }
    }

    // Método auxiliar para encontrar el paréntesis de cierre correspondiente
    private int findClosingParenthesis(String input) {
        int balance = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                balance++;
            else if (input.charAt(i) == ')') {
                balance--;
                if (balance == 0)
                    return i;
            }
        }
        return -1;
    }

    private String[] splitInput(String input) {
        List<String> parts = new ArrayList<>();
        int balance = 0;
        StringBuilder currentPart = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(')
                balance++;
            if (c == ')')
                balance--;

            if (balance == 0 && c == ' ' && currentPart.length() > 0) {
                parts.add(currentPart.toString());
                currentPart = new StringBuilder();
            } else {
                currentPart.append(c);
            }
        }
        if (currentPart.length() > 0)
            parts.add(currentPart.toString());

        if (parts.size() == 3)
            return parts.toArray(new String[0]);
        else
            return null;
    }
}
