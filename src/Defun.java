import java.rmi.StubNotFoundException;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.text.Style;

public class Defun implements IFunction {
    
    @Override
    public String execute(String input, Environment env) {
        String[] tokens = input.split("\\s+");
        
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

        
        StringBuilder nombreFuncion = new StringBuilder();
        for (String funcion : stackNombreFuncion) {
            nombreFuncion.append(funcion).append(" "); // Concatena cada string del Stack
        }

        
        StringBuilder cuerpoFuncion = new StringBuilder();
        for (String funcion : stackCuerpoFuncion) {
            cuerpoFuncion.append(funcion).append(" "); // Concatena cada string del Stack
        }


        
        
        return cuerpoFuncion.toString();
    }
    
}
