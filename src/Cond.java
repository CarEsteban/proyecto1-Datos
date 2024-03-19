import java.lang.reflect.Array;
import java.util.Arrays;


public class Cond implements IFunction  {
    //para que funcione el cond, he imprimir en pantalla se usa quote
    String operator, resp1,resp2,n1, n2;
    @Override
    public String execute(String input, Environment env) {
        // reducir el input de entrada
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        Predicate predicate = new Predicate();
        Quote quote = new Quote();
        operator = tokens[1];
        n1 = tokens[2];
        n2 = tokens[3];
        resp1 = tokens[5];
        resp2 = tokens[7];
        
        //falta hacer que se puedan tener espacios en la resp y manejarlo con el quote para seguir la estrcutura de lisp
        if (tokens.length >= 3 & tokens.length ==8) {

            String expressionToEvaluate, result,resultPredicate ;

            expressionToEvaluate = "( "+operator+" "+n1+" "+n2+" )";
            
            if (predicate.execute(expressionToEvaluate, env).equals("true")) {
                resultPredicate = resp1;
            }else if(predicate.execute(expressionToEvaluate, env).equals("false")){
                resultPredicate = resp2;
            }else{
                resultPredicate = "No valid";
            }
            

            result = "( "+tokens[4]+" "+resultPredicate+" )";

            return quote.execute(result, env);

            
        } else {
            return "Error: input no válido.";
        }
    }
    
}
