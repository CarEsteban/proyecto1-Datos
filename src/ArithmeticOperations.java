import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.List;
import java.util.regex.Matcher;


public class ArithmeticOperations {
    public String execute(String input, Environment env) {
        input = input.trim();
        if (input.startsWith("(") && input.endsWith(")")) {
            input = input.substring(1, input.length() - 1).trim();
        }

        String[] tokens = input.split("\\s+");
        Stack<String> stack = new Stack<>();
        Stack<String> stackAuxi = new Stack<>();
        String result = null;


        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(")")) {
                for (int j = tokens.length; j < 0; j--) {
                    
                }   
            }
        }








        return result;
    }

    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }
}