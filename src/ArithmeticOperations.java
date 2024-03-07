public class ArithmeticOperations implements IFunction {
    public String processOperation(String input) {
        // Elimina los paréntesis, los espacios al principio y al final, y luego divide la expresión en tokens
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");

        // Asegura que hay al menos un operador y dos operandos
        if (tokens.length >= 3) {
            String operator = tokens[0];

            // Convierte los operandos a enteros
            int operand1, operand2;
            try {
                operand1 = Integer.parseInt(tokens[1]);
                operand2 = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e) {
                return "Error: Uno de los operandos no es un entero válido";
            }

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
            // Devuelve el resultado como String
            return "Resultado de la operación: " + result;
        } else {
            return "Error en la expresión aritmética";
        }
    }
}
