import java.util.ArrayList;

/**
 * Clase que implementa la interfaz IFunction para realizar operaciones aritméticas.
 */
public class ArithmeticOperations implements IFunction {

    /**
     * Clase interna para representar un nodo en la expresión aritmética.
     */
    private class Node {
        String value;
        ArrayList<Node> children;

        /**
         * Constructor de la clase Node.
         * @param value El valor del nodo.
         */
        public Node(String value) {
            this.value = value;
            children = new ArrayList<>();
        }
    }

    /**
     * Método para ejecutar la operación aritmética.
     * @param input Cadena de entrada que contiene la expresión aritmética.
     * @param env Entorno en el que se ejecuta la función, proporcionando acceso a las variables.
     * @return Una cadena que representa el resultado de la expresión aritmética o un mensaje de error.
     */
    @Override
    public String execute(String input, Environment env) {
        ArrayList<Node> nodesList = new ArrayList<>();
        String[] tokens = input.split("\\s+");
        
        Node root = null;
        Node currentNode = null;

        for (String token : tokens) {
            if (isOperator(token)) {
                Node operatorNode = new Node(token);
                if (root == null) {
                    root = operatorNode;
                } else {
                    currentNode.children.add(operatorNode);
                }
                nodesList.add(operatorNode);
                currentNode = operatorNode;
            } else if (token.equals("(")) {
                // No action needed, just indicating the start of a subexpression
            } else if (token.equals(")")) {
                nodesList.remove(nodesList.size() - 1);
                if (!nodesList.isEmpty()) {
                    currentNode = nodesList.get(nodesList.size() - 1);
                }
            } else {
                // It's a variable or a number
                currentNode.children.add(new Node(token));
            }
        }
        
        // Before evaluation, check for variable definitions
        String evalResult = evaluate(root, env);
        if (evalResult == null) {
            return "Variable undefined";
        }
        return evalResult;
    }

    /**
     * Método recursivo para evaluar la expresión aritmética.
     * @param node El nodo actual en el árbol de expresión aritmética.
     * @param env Entorno en el que se ejecuta la función, proporcionando acceso a las variables.
     * @return Una cadena que representa el resultado de la expresión aritmética o null si hay un error.
     */
    private String evaluate(Node node, Environment env) {
        if (node.children.size() == 0) {
            // If it's a variable, check if it's defined in the environment
            if (isVariable(node.value) && env.variableExists(node.value)) {
                return env.getVariable(node.value);
            } else if (isVariable(node.value) && !env.variableExists(node.value)) {
                // Variable is not defined
                return null;
            }
            // It's a number
            return node.value;
        }
        
        double result = 0;
        String firstOperand = evaluate(node.children.remove(0), env);
        if (firstOperand == null) {
            return null;
        }
        result = Double.parseDouble(firstOperand);

        for (Node child : node.children) {
            String childValue = evaluate(child, env);
            if (childValue == null) {
                return null;
            }
            double childDouble = Double.parseDouble(childValue);
            switch (node.value) {
                case "+":
                    result += childDouble;
                    break;
                case "-":
                    result -= childDouble;
                    break;
                case "*":
                    result *= childDouble;
                    break;
                case "/":
                    if (childDouble == 0) {
                        return "Error: Division by zero";
                    }
                    result /= childDouble;
                    break;
            }
        }
        
        // Usar DecimalFormat para formatear el resultado a dos decimales
        java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
        return df.format(result);
    }

    /**
     * Método para verificar si un valor es una variable.
     * @param value El valor a verificar.
     * @return true si el valor es una variable (una sola letra), de lo contrario false.
     */
    private boolean isVariable(String value) {
        // A variable is a single letter
        return value.matches("[a-zA-Z]");
    }

    /**
     * Método para verificar si un valor es un operador aritmético.
     * @param value El valor a verificar.
     * @return true si el valor es un operador aritmético (+, -, *, /), de lo contrario false.
     */
    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }
    
    // El resto de la clase permanece igual
}
