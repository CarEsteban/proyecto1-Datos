import java.util.ArrayList;
import java.util.List;

public class ArithmeticOperations implements IFunction {
    public String execute(String input, Environment env) {
        input = input.trim();

        // Procesamiento iterativo para eliminar todos los paréntesis externos
        // innecesarios
        while (input.startsWith("(") && input.endsWith(")") && findClosingParenthesis(input) == input.length() - 1) {
            input = input.substring(1, input.length() - 1).trim();
        }

        try {
            int result = Integer.parseInt(input);
            return "Resultado de la operación: " + result;
        } catch (NumberFormatException ignored) {
            // Manejar expresiones más complejas aquí, si es necesario
        }

        String[] tokens = splitInput(input);

        if (tokens != null && tokens.length == 3) {
            String operator = tokens[0];
            if ("+-*/".contains(operator)) {
                String operand1String = tokens[1];
                String operand2String = tokens[2];
                String evalOperand1 = operand1String.matches("\\d+") ? operand1String : execute(operand1String, env);
                String evalOperand2 = operand2String.matches("\\d+") ? operand2String : execute(operand2String, env);
                try {
                    int operand1 = Integer.parseInt(evalOperand1);
                    int operand2 = Integer.parseInt(evalOperand2);
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
                    }
                } catch (NumberFormatException e) {
                    return "Error en la expresión aritmética";
                }
            }
            return "Error: Operador no válido";
        } else {
            return "Error en la expresión aritmética";
        }
    }

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
        return input.length() - 1; // Devuelve el último índice si los paréntesis están equilibrados
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
            return null; // Indica un error o que la entrada no se puede dividir correctamente
    }
}
