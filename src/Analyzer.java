import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {
    // Método para analizar el string de entrada
    public int analyze(String input) {
		// 
		if (evaluate("^[(][ ]+((([0-9]+[ ])*)|(([+-/*]+[ ])*)|(([(]+[ ])*)|(([)]+[ ])*))+[)]$",input)) //para operaciones aritmeticas
			return 1;
        //else if (evaluate("^\\(\\s*\\+\\s*([a-z]+|\\d+)\\s+([a-z]+|\\d+)\\s+[-+*/]\\s+\\(\\s*([a-z]+|\\d+)\\s+([a-z]+|\\d+)\\s+[-+*/]\\s+\\)\\s+\\)$", input))
          //  return 2;
        
        
		//else if (evaluate("^[(][ ]*[-][ ]+([a-z]+|[0-9]+)[ ]+([a-z]+|[0-9]+)[ ]*[)]$",input)) //This is a simple add operation of 2 operands
		//	return 2;
		else 
			return -1; 
    }


    private static boolean evaluate(String base, String input){
        Pattern pattern = Pattern.compile(base, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(input);
	    return matcher.find();
    }

}