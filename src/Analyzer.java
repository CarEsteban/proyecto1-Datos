import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {
    // Método para analizar el string de entrada
    public int tokenizer(String input) {
        
		//tokens para operaciones aritmeticas
        // try with this: ( + 10 ( * 2 3 ) ( - 15 ( / 30 17 ) ) ( + 4 ( - 20 10 ) ) ( * ( / 18 7 ) ( + 1 2 ) ) 18.7 )  
        if (analyzer("^[(][ ]+([+-/*]+[ ])((([-]?[0-9]+(\\.[0-9]+)?[ ]))|(([+-/*]+[ ]))|(([aA-zZ]+[ ]))|(([(]+[ ]))|(([)]+[ ])))+[)][ ]*$",input)) 
            return 1;
        //tokens para setq
        // try with this: ( setq valor holaMundo )
        else if (analyzer("^[(][ ]+(setq+[ ])+([a-zA-Z]+[ ])((.))+[ ]+[)][ ]*$",input))
            return 2;
        //tokens para quotes
        // try with this: ( quote valor ) o ( ' valor )
        else if (analyzer("^[(][ ]+(quote+[ ]|'+[ ])((.))+[ ]+[)][ ]*$",input)) 
            return 3;
        //tokens para cond 
        // try with this : ( cond ( ( < 4 5 ) quote menor ) ( quote mayor ) )                 
        else if(analyzer("^[(][ ]+(cond+[ ])[(][ ]+[(][ ]+(<|>|==|<=|>=|equal)[ ]+(([a-z]+|[0-9]+)[ ]+)(([a-z]+|[0-9]+)[ ]+)[)][ ]+(quote+[ ]|'+[ ])(.)*[ ]+[)][ ]+[(][ ]+(quote+[ ]|'+[ ])(.)*[ ]+[)][ ]+[)][ ]*$", input))
            return 4;
        //tokens para predicados  
        // try with this: ( atom valor ) ( list (1,3,4,5) )               
        else if(analyzer("^[(][ ]+(atom|list|equal|<|>)(.)*[ ]+[)][ ]*$", input))
            return 5;
        // tokens para el defun
        // try with this: ( defun f-c { f } { * ( / 5 9 ) ( - f 32 ) } )    ( f-c 100 )
        //  try with this: ( defun c-f { c } { + ( * c ( / 9 5 ) ) 32 } )   ( c-f 28)
        else if(analyzer("^[(][ ]+defun+[ ](.)*[ ]+[{][ ]+(.)*[ ]+[}][ ]+[{][ ]+(.)*[}][ ]+[)][ ]*$", input))
            return 6;
		else 
			return -1; 
    }


    private static boolean analyzer(String base, String input){
        Pattern pattern = Pattern.compile(base, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(input);
	    return matcher.find();
    }

}