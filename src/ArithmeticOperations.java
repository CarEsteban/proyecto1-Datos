import java.util.Scanner;

public class ArithmeticOperations implements IFunction {
    public String processOperation(String input) {
        // Elimina los paréntesis y divide la expresión en tokens
        String[] tokens = input.replaceAll("[()]", "").split("\\s+");

        if (tokens.length >= 3) {
            // El primer token es el operador (+, -, *, /)
            String operator = tokens[0];

            // Los siguientes tokens son operandos
            int operand1 = Integer.parseInt(tokens[1]);
            int operand2 = Integer.parseInt(tokens[2]);

            // Realiza la operación aritmética
            int result = 0;
            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        return "Error: División por cero";
                    }
                    break;
                default:
                    return "Operador no válido";
            }
            return "Resultado de la operación: " + result;
        } else {
            return "Error en la expresión aritmética";
        }
    }
}
