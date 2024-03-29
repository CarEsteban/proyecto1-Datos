import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Clase que implementa la interfaz IFunction para definir funciones personalizadas.
 */

public class Defun implements IFunction {
    
    /**
     * Método para ejecutar la función definición de función.
     * @param input Cadena de entrada que contiene la definición de la función.
     * @param env Entorno en el que se ejecuta la función, proporcionando acceso a las variables.
     * @return Una cadena que indica el éxito de la definición de la función.
     */
    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.split("\\s+");
        
        if (!Arrays.stream(tokens).anyMatch("defun"::equals)) {
            return executeFunction(input, env);
        }

        Stack<String> stackNombreFuncion = new Stack<>();
        Stack<String> stackCuerpoFuncion = new Stack<>();
       
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].trim().equals("}")) {
                i++;
                i++;
                for (int j = i; j < tokens.length; j++) {
                    if (tokens[j].trim().equals("}")) {
                        //stackCuerpoFuncion.push(tokens[j]);
                        i = tokens.length;
                        break;
                    }
                    stackCuerpoFuncion.push(tokens[j]);
                    
                }
            }
            else if(tokens[i].trim().equals("{")){
                continue;
            }
            if(i!=tokens.length){
                stackNombreFuncion.push(tokens[i]);
            }
        } 

        ArrayList<String> parametros = new ArrayList<>();
        for (int i = 1; i < stackNombreFuncion.size(); i++) {
            parametros.add(stackNombreFuncion.get(i));
        }
        
        env.defineVariable("parametros", parametros);
        
        StringBuilder nombreFuncion = new StringBuilder();
        for (String funcion : stackNombreFuncion) {
            nombreFuncion.append(funcion).append(" "); // Concatena cada string del Stack
        }

        
        StringBuilder cuerpoFuncion = new StringBuilder();
        for (String funcion : stackCuerpoFuncion) {
            cuerpoFuncion.append(funcion).append(" "); // Concatena cada string del Stack
        }

        //agregamos el nombre de la funcion como key y el cuerpo de la funcion como value
        env.defineVariable(nombreFuncion.toString().trim(), cuerpoFuncion.toString().trim());


        
        return nombreFuncion.toString()+"{set}";
    }

    /**
     * Método estático para ejecutar una función definida previamente.
     * @param operation Cadena de entrada que contiene la llamada a la función.
     * @param env Entorno en el que se ejecuta la función, proporcionando acceso a las variables.
     * @return Una cadena que representa el resultado de la ejecución de la función.
     */
    public static String executeFunction(String operation, Environment env) {



        IEvaluator evaluator = new Evaluator();
        
        String[] elementsOperation = operation.split(" ");

        Stack<String> operationArray = new Stack<>();
        operationArray.push("(");
        for (int i = 0; i < elementsOperation.length; i++) {
            if (env.variableExists(elementsOperation[i])) {
                operationArray.push(env.getStringVariable(elementsOperation[i]));
            }else{
                operationArray.push(elementsOperation[i]);
            }
        }
        operationArray.push(")");

    
        StringBuilder input = new StringBuilder();
        for (String funcion : operationArray) {
            input.append(funcion).append(" "); // Concatena cada string del Stack
        }


        try {
            return evaluator.evaluate(input.toString().trim(), env);
        } catch (Exception e) {
            //e.printStackTrace();
            return e.toString();
        }


    }
    
}
