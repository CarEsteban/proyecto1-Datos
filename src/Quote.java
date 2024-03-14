public class Quote implements IFunction {

    private String expression;

    public Quote(String expression) {
        this.expression = expression;
    }

    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        if (tokens.length == 2) {
            String quoted = tokens[1];
            if (quoted.startsWith("\"") && quoted.endsWith("\"")) {
                return quoted.substring(1, quoted.length() - 1);
            } else {
                return "sintax error";
            }
        } else {
            return "Error: input no válido para Quote.";
        }
    }
}
