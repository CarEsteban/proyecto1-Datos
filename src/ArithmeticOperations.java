import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class ArithmeticOperations implements IFunction {
    private class Node {
        String value;
        ArrayList<Node> children;

        public Node(String value) {
            this.value = value;
            children = new ArrayList<>();
        }
    }

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
                nodesList.add(operatorNode); // Use add for push
                currentNode = operatorNode;
            } else if (token.equals("(")) {
                // No action needed, just indicating the start of a subexpression
            } else if (token.equals(")")) {
                nodesList.remove(nodesList.size() - 1); // Use remove for pop
                if (!nodesList.isEmpty()) {
                    currentNode = nodesList.get(nodesList.size() - 1); // Use get for peek
                }
            } else { // It's a number
                currentNode.children.add(new Node(token));
            }
        }
        
        int result = evaluate(root);
        return Integer.toString(result);
    }

    private int evaluate(Node node) {
        if (node.children.size() == 0) {
            return Integer.parseInt(node.value);
        }
        int result = 0;
        switch (node.value) {
            case "+":
                for (Node child : node.children) {
                    result += evaluate(child);
                }
                break;
            case "-":
                result = evaluate(node.children.remove(0));
                for (Node child : node.children) {
                    result -= evaluate(child);
                }
                break;
            case "*":
                result = 1;
                for (Node child : node.children) {
                    result *= evaluate(child);
                }
                break;
            case "/":
                result = evaluate(node.children.remove(0));
                for (Node child : node.children) {
                    result /= evaluate(child);
                }
                break;
        }
        return result;
    }

    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }

}
