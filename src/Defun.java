public class Defun implements IFunction {

    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        String functionName = tokens[1];
        String parameters = tokens[2];
        String body = tokens[3];

        // Define the function in the global environment
        env.defineVariable(functionName, new UserDefinedFunction(parameters, body));

        // Return a message indicating that the function has been defined
        return "Function " + functionName + " defined.";
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
