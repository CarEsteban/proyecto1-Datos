public class Quote implements IFunction {

    private String expression;

    public Quote(String expression) {
        this.expression = expression;
    }

    @Override
    public String evaluate(Environment env) {
        String[] parts = expression.split(" ");
        if (parts.length < 2 || !parts[0].equals("quote")) {
            return "Syntax Error";
        }

        String text = parts[1];
        boolean hasVariable = env.variableExists(text);
        if (hasVariable) {
            text = env.getStringVariable(text);
        }

        return text;
    }
}
