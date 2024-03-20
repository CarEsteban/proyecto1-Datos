import java.util.Stack;

public class Cond implements IFunction {
    
    /**
     * Método para ejecutar la función cond.
     * @param input Cadena de entrada que contiene las condiciones y operaciones a evaluar.
     * @param env Entorno en el que se ejecuta la función, proporcionando acceso a las variables.
     * @return Una cadena que representa el resultado de la evaluación de la función cond.
     */
    @Override
    public String execute(String input, Environment env) {
        // Simplifica el procesamiento del input
        String[] tokens = input.trim().split("\\s+");
        if (tokens.length < 16) {
            return "Error: input no válido.";
        }

        Stack<String> stackGeneral = new Stack<>();
        Stack<String> stackAuxi = new Stack<>();
        int parenthesClosed = 0;

        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].equals(")")&&parenthesClosed!=2) {
                parenthesClosed+=1;
            }
            stackAuxi.push(tokens[i]);
            if (parenthesClosed==2) {

                StringBuilder currentStructure = new StringBuilder();
                for (String item : stackAuxi) {
                    currentStructure.append(item).append(" ");
                }

                stackGeneral.push(currentStructure.toString().trim());
                parenthesClosed=0;
                stackAuxi.clear();
            }
        }

        //quitarl el ultimo parentesis al ultimo del stackGeneral y luego operar cada index del stack
        String lastString;

        lastString = stackGeneral.peek();
        stackGeneral.pop();
        String modifiedString = lastString.substring(0, lastString.length() - 2);
        stackGeneral.push(modifiedString);

        
        Predicate predicate = new Predicate();
        Quote quote = new Quote();

        for (int i = 0; i < stackGeneral.size(); i++) {
            String operation = stackGeneral.get(i);
            if (i==stackGeneral.size()-1) {
                return quote.execute(operation, env);
            }else{
                String condition = operation.substring(2, 11);
                
                String resultOperation = "( "+operation.substring(12, operation.length()-2)+" )";
                
                if (!condition.endsWith(")")) {
                    condition = operation.substring(2, 15);
                    resultOperation = "( "+operation.substring(16, operation.length()-2)+" )";
                }
                if (predicate.execute(condition, env).equals("true")) {
                    return quote.execute(resultOperation, env);
                }else{
                    continue;
                }
            }
        }
        
        return "Error";
    }
}
