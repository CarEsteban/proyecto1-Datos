public class Quote implements IFunction {

    public String execute(String expression,Environment env) {
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
