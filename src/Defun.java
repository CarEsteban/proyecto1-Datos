public class Defun implements IFunction {

    @Override
    public String execute(String input, Environment env) {
        // Limpiar la entrada y dividirla en tokens
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        String functionName = tokens[1];
        String parameters = tokens[2];
        String body = tokens[3];

        // Crear un nuevo entorno para la función
        Environment functionEnv = new Environment(env);

        // Definir la función en el entorno global
        env.defineVariable(functionName, new FunctionValue(parameters, body, functionEnv));

        // Devolver un mensaje indicando que la función se ha definido correctamente
        return "Function " + functionName + " defined.";
    }

}
