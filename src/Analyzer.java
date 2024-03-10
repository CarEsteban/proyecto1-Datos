import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {
    // Método para analizar el string de entrada
    public int tokenizer(String input) {
		//tokens para operaciones aritmeticas 
		if (analyzer("^[(][ ]+([+-/*]+[ ])((([0-9]+[ ]))|(([+-/*]+[ ]))|(([(]+[ ]))|(([)]+[ ])))+[)][ ]*$",input)) 
			return 1;
        //tokens para setq
        else if (analyzer("^[(][ ]+(setq+[ ])+([a-z]+[ ])((.))+[ ]+[)][ ]*$",input)) 
            return 2;
        //tokens para quotes
        else if (analyzer("^[(][ ]+(quote+[ ]|'+[ ])((.))+[ ]+[)][ ]*$",input)) 
            return 3;
        //tokens para cond                  
        else if(analyzer("^[(][ ]+(cond+[ ])[(][ ]+[(][ ]+(<|>|=|<=|>=|equal)[ ]+(([a-z]+|[0-9]+)[ ]+)(([a-z]+|[0-9]+)[ ]+)[)][ ]+[(][ ]+(quote+[ ]|'+[ ])(.)*[ ]+[)][ ]+[)][ ]+[(][ ]+(quote+[ ]|'+[ ])(.)*[ ]+[)][ ]+[)][ ]*$", input))
            return 4;

		else 
			return -1; 
    }


    private static boolean analyzer(String base, String input){
        Pattern pattern = Pattern.compile(base, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(input);
	    return matcher.find();
    }

}