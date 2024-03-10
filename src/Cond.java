import java.util.Arrays;

import javax.print.DocFlavor.READER;

public class Cond implements IFunction  {
    //para que funcione el cond, he imprimir en pantalla se usa quote
    String operator, resp1,resp2;
    int n1, n2;
    @Override
    public String execute(String input, Environment env) {
        // reducir el input de entrada
        String[] tokens = input.trim().replaceAll("[()]", "").trim().split("\\s+");
        operator = tokens[1];
        n1 = Integer.parseInt(tokens[2]);
        n2 = Integer.parseInt(tokens[3]);
        resp1 = tokens[5];
        resp2 = tokens[7];

        
        if (tokens.length >= 3 & tokens.length ==8) {

            switch (operator) {
                case "<":
                    if (n1<n2) {
                        return resp1;
                    }else{
                        return resp2;
                    }
                case "<=":
                    if(n1<=n2){
                        return resp1;
                    }else{
                        return resp2;
                    }
                case ">":
                    if(n1>n2){
                        return resp1;
                    }else{
                        return resp2;
                    }
                case ">=":
                    if(n1>=n2){
                        return resp1;
                    }else{
                        return resp2;
                    }
                case "==":
                    if(n1==n2){
                        return resp1;
                    }else{
                        return resp2;
                    }
                case "equal":
                    return "me falta implementar el equal we";
                default:
                    return "no joto";
            }
            
        } else {
            return "Error: input no válido.";
        }
    }
    
}
