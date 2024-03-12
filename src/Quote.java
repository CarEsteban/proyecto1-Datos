public class Quote implements IFunction {

    private String expression;

    public Quote(String expression) {
        this.expression = expression;
    }

    
    public String evaluate(Environment env) {
        String expressionWithoutQuote = expression.substring(expression.indexOf(" ") + 1);
        if (expressionWithoutQuote.startsWith("\"") && expressionWithoutQuote.endsWith("\"")) {
            return expressionWithoutQuote.substring(1, expressionWithoutQuote.length() - 1);
        } else {
            String variableName = expressionWithoutQuote.trim();
            if (env.variableExists(variableName)) {
                return "quote " + env.getVariable(variableName);
            } else {
                return "sintax error";
            }
        }
    }
}
