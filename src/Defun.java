public class Defun implements IFunction {

    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        String functionName = tokens[1];
        String parameters = tokens[2];
        String body = extractBody(tokens); // Extract the body from the input

        // Define the function in the global environment
        env.defineVariable(functionName, new UserDefinedFunction(parameters, body));

        // Return a message indicating that the function has been defined
        return "Function " + functionName + " defined.";
    }

    private String extractBody(String[] tokens) {
        StringBuilder bodyBuilder = new StringBuilder();
        boolean insideBody = false;
        for (int i = 3; i < tokens.length; i++) {
            if (tokens[i].equals("(")) {
                insideBody = true;
            } else if (tokens[i].equals(")")) {
                insideBody = false;
            }

            if (insideBody) {
                bodyBuilder.append(tokens[i]).append(" ");
            }
        }
        return bodyBuilder.toString().trim();
    }

    private static class UserDefinedFunction {
        private final String parameters;
        private final String body;

        public UserDefinedFunction(String parameters, String body) {
            this.parameters = parameters;
            this.body = body;
        }

        public String getParameters() {
            return parameters;
        }

        public String getBody() {
            return body;
        }
    }
}
