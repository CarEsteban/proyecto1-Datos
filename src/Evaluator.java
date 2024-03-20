import java.util.Stack;

/**
 * Clase que implementa la interfaz IEvaluator para evaluar expresiones en un entorno dado.
 */
public class Evaluator implements IEvaluator {

    private Analyzer analyzer;

    /**
     * Constructor predeterminado que inicializa un nuevo analizador para evaluación de expresiones.
     */
    public Evaluator() {
        this.analyzer = new Analyzer();
    }

    /**
     * Método para evaluar una expresión en un entorno dado.
     * @param expression La expresión a evaluar.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación de la expresión como una cadena.
     */
    @Override
    public String evaluate(String expression, Environment env) {
        int result = analyzer.tokenizer(expression);
        String evaluatedResult = null;

        switch (result) {
            case 1: // Aritmetic operation
                evaluatedResult = evaluateArithmeticOperation(expression, env);
                return evaluatedResult;
            case 2: // SetQ
                evaluatedResult = evaluateSetQ(expression, env);
                return evaluatedResult;
            case 3: // Quote
                evaluatedResult = evaluateQuote(expression, env);
                return evaluatedResult;
            case 4: // cond
                evaluatedResult = evaluateCond(expression, env);
                return evaluatedResult;
            case 5: // predicate
                evaluatedResult = evaluatePredicate(expression, env);
                return evaluatedResult;
            case 6: //defun
                evaluatedResult = evaluateDefun(expression, env);
                return evaluatedResult;
            default:
                evaluatedResult = "-1";
        }

        String expressionSimplified = expression.trim().replaceAll("[()]", "").trim();

        String[] elements = expressionSimplified.split(" ");

        Stack<String> expressionWithParameters = new Stack<>();

        String parameters, parameterWithOutBrackets ;

        parameters = env.getStringVariable("parametros");
        parameterWithOutBrackets = parameters.substring(1, parameters.length() - 1);


        String[] separatedParametros = parameterWithOutBrackets.split(",\\s*");


        // Para mantener el orden original al reconstruir el stack, se debe iterar en orden inverso
        for (int i = 0 ; i < elements.length; i++) {
            if (i==0) {
                expressionWithParameters.push(elements[i]);
                i++;
            }
            for(int j = 0 ; j < separatedParametros.length ; j++ ){
                expressionWithParameters.push(separatedParametros[j]);
                env.defineVariable(separatedParametros[j], elements[j+1]);
            }
            i = elements.length;
        }

        StringBuilder processedExpression = new StringBuilder();
        for (String token : expressionWithParameters) {
            processedExpression.append(token).append(" "); // Concatena cada string del Stack
        }


        //evalua solamente si el error proviene de una funcion ya existente para operarla, si no tira error de sintaxis

        if(evaluatedResult.equals("-1") && env.variableExists(processedExpression.toString().trim()) ){
            String input ;
            input = env.getVariable(processedExpression.toString().trim());
            evaluatedResult = evaluateDefun(input, env);
            return evaluatedResult;
        }

        return evaluatedResult;
    }

    /**
     * Método privado para evaluar operaciones aritméticas.
     * @param expression La expresión de operación aritmética.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la operación aritmética como una cadena.
     */
    private String evaluateArithmeticOperation(String expression, Environment env) {
        ArithmeticOperations arithmeticOperation = new ArithmeticOperations();
        return arithmeticOperation.execute(expression, env);
    }

    /**
     * Método privado para evaluar la función SetQ.
     * @param expression La expresión de la función SetQ.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación de SetQ como una cadena.
     */
    private String evaluateSetQ(String expression, Environment env) {
        SetQ setQ = new SetQ();
        return setQ.execute(expression, env);
    }

    /**
     * Método privado para evaluar la función Quote.
     * @param expression La expresión de la función Quote.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación de Quote como una cadena.
     */
    private String evaluateQuote(String expression, Environment env) {
        Quote quote = new Quote();
        return quote.execute(expression, env);
    }

    /**
     * Método privado para evaluar la función Cond.
     * @param expression La expresión de la función Cond.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación de Cond como una cadena.
     */
    private String evaluateCond(String expression, Environment env) {
        Cond cond = new Cond();
        return cond.execute(expression, env);
    }

    /**
     * Método privado para evaluar predicados.
     * @param expression La expresión del predicado.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación del predicado como una cadena.
     */
    private String evaluatePredicate(String expression, Environment env) {
        Predicate predicate = new Predicate();
        return predicate.execute(expression, env);
    }

    /**
     * Método privado para evaluar la función Defun.
     * @param expression La expresión de la función Defun.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación de Defun como una cadena.
     */
    private String evaluateDefun(String expression, Environment env) {
        Defun defun = new Defun();
        return defun.execute(expression, env);
    }
}
