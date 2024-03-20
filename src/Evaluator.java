import java.util.Stack;

public class Evaluator implements IEvaluator {

    private Analyzer analyzer;

    public Evaluator() {
        this.analyzer = new Analyzer();
    }

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

        
        Stack<String> stackParametros = new Stack<>();


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

    private String evaluateArithmeticOperation(String expression, Environment env) {
        ArithmeticOperations arithmeticOperation = new ArithmeticOperations();
        return arithmeticOperation.execute(expression, env);
    }

    private String evaluateSetQ(String expression, Environment env) {
        SetQ setQ = new SetQ();
        return setQ.execute(expression, env);
    }

    private String evaluateQuote(String expression, Environment env) {
        Quote quote = new Quote();
        return quote.execute(expression, env);
    }

    private String evaluateCond(String expression, Environment env) {
        Cond cond = new Cond();
        return cond.execute(expression, env);
    }

    private String evaluatePredicate(String expression, Environment env) {
        Predicate predicate = new Predicate();
        return predicate.execute(expression, env);
    }

    private String evaluateDefun(String expression, Environment env) {
        Defun defun = new Defun();
        return defun.execute(expression, env);
    }

}
