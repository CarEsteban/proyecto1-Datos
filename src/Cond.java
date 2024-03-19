import java.util.Arrays;

public class Cond implements IFunction {
    @Override
    public String execute(String input, Environment env) {
        // Simplifica el procesamiento del input
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        if (tokens.length != 8) {
            return "Error: input no válido.";
        }
        
        String operator = tokens[1];
        String n1 = tokens[2];
        String n2 = tokens[3];
        String resp1 = tokens[5];
        String resp2 = tokens[7];
        String expressionToEvaluate = String.format("( %s %s %s )", operator, n1, n2);
        
        Predicate predicate = new Predicate();
        Quote quote = new Quote();
        String resultPredicate = predicate.execute(expressionToEvaluate, env);
        
        // Decide qué respuesta usar basándose en el resultado del predicado
        String finalResult;
        if ("true".equals(resultPredicate)) {
            finalResult = resp1;
        } else if ("false".equals(resultPredicate)) {
            finalResult = resp2;
        } else {
            finalResult = "No valid";
        }
        
        // Construye y devuelve el resultado final
        String result = String.format("( %s %s )", tokens[4], finalResult);
        return quote.execute(result, env);
    }
}
