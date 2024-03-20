public class Quote implements IFunction {

    @Override
    public String execute(String input, Environment env) {
        
        String processedInput = input.trim();
        if (processedInput.startsWith("(") && processedInput.endsWith(")")) {
            processedInput = processedInput.substring(1, processedInput.length() - 1).trim();
        }

        String[] tokens = processedInput.split("\\s+");
        
        if (tokens.length == 2) {
            String quoted = tokens[1];
            
            
            if(env.variableExists(quoted)){
                String value = env.getVariable(quoted);
                return value.toString(); 
            } else {
                return quoted;
            }
        } else {
            
            StringBuilder operation = new StringBuilder();
            for (int i = 1; i < tokens.length ; i++) { 
                operation.append(tokens[i]).append(" ");
            }
            
            try {
                
                return evaluateArithmeticExpression(operation.toString().trim(), env);
            } catch (Exception e) {
                return operation.toString().trim(); 
            }
        }
    }

    private String evaluateArithmeticExpression(String expression, Environment env) {
        
        ArithmeticOperations arithmeticOperations = new ArithmeticOperations();
        return arithmeticOperations.execute("( " + expression + " )", env);
    }
}
