public class Quote implements IFunction {

    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");

        if (tokens.length == 2) {
            String quoted = tokens[1];

            if(env.variableExists(quoted)){
                return env.getStringVariable(quoted);
            }else{
                return quoted;
            }

        } else {
            return "undefined";
        }
    }
}
