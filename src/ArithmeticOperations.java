public class ArithmeticOperations implements IFunction {

    public String execute(String input, Environment env) {
        // Remove leading and trailing spaces and parentheses
        input = input.trim().replaceAll("^\\(|\\)$", "");

        // Check if it's a single integer
        try {
            int result = Integer.parseInt(input);
            return "Resultado de la operación: " + result;
        } catch (NumberFormatException ignored) {
            // Continue with evaluating the expression
        }

        // Split the input into tokens
        String[] tokens = input.split("\\s+", 3); // Split only into three parts: operator, first operand, the rest

        // Ensure there are enough tokens for an operation
        if (tokens.length == 3) {
            String operator = tokens[0];

            // Check if it's a valid operator
            if ("+-*/".contains(operator)) {
                String operand1String = tokens[1];
                String operand2String = tokens[2];

                // Evaluate first operand which might be an expression itself
                String evalOperand1 = operand1String.matches("\\d+") ? operand1String : execute(operand1String, env);
                if (evalOperand1.startsWith("Error")) return evalOperand1;
                int operand1 = Integer.parseInt(evalOperand1.replaceAll("Resultado de la operación: ", ""));

                // Evaluate second operand which might be an expression itself
                String evalOperand2 = operand2String.matches("\\d+") ? operand2String : execute(operand2String, env);
                if (evalOperand2.startsWith("Error")) return evalOperand2;
                int operand2 = Integer.parseInt(evalOperand2.replaceAll("Resultado de la operación: ", ""));

                // Perform the arithmetic operation
                switch (operator) {
                    case "+": return "Resultado de la operación: " + (operand1 + operand2);
                    case "-": return "Resultado de la operación: " + (operand1 - operand2);
                    case "*": return "Resultado de la operación: " + (operand1 * operand2);
                    case "/":
                        if (operand2 == 0) return "Error: División por cero";
                        return "Resultado de la operación: " + (operand1 / operand2);
                    default: return "Error: Operador no válido";
                }
            } else {
                return "Error: Operador no válido";
            }
        } else {
            return "Error en la expresión aritmética";
        }
    }

    // This method seems to be fine but ensure it is not mistakenly used in your logic since you're not handling parentheses directly in the execute method.
    private int findClosingParenthesis(String input) {
        int balance = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                balance++;
            } else if (input.charAt(i) == ')') {
                balance--;
                if (balance < 0) {
                    return -1; // Unbalanced parentheses
                }
                if (balance == 0) {
                    return i; // Found closing parenthesis
                }
            }
        }
        return -1; // Unbalanced parentheses or no closing parenthesis found
    }
}
