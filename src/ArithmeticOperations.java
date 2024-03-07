import java.util.Scanner;

public class ArithmeticOperations implements IFunction {
    public void processOperation(String operation, String input) {
        if (operation.equals("arithmetic")) {
            // Elimina los paréntesis y divide la expresión en tokens
            String[] tokens = input.replaceAll("[()]", "").split("\\s+");

            if (tokens.length >= 3) {
                // El primer token es el operador (+, -, *, /)
                String operator = tokens[0];

                // Los siguientes tokens son operandos
                double operand1 = Double.parseDouble(tokens[1]);
                double operand2 = Double.parseDouble(tokens[2]);

                // Realiza la operación aritmética
                double result = 0;
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
                        result = operand1 / operand2;
                        break;
                    default:
                        System.out.println("Operador no válido");
                        return;
                }

                System.out.println("Resultado de la operación: " + result);
            } else {
                System.out.println("Error en la expresión aritmética");
            }
        } else {
            System.out.println("Operación no soportada por ArithmeticOperations");
        }
    }
}
