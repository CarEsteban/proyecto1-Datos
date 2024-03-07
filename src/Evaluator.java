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
            // case 2: // Quote
            //     evaluatedResult = evaluateQuote(expression, env);
            //     return evaluatedResult;
            // case 3: // SetQ
            //     evaluatedResult = evaluateSetQ(expression, env);
            //     return evaluatedResult;
            default:
                evaluatedResult = "Invalid expression";
        }

        return "-1";
    }

    private String evaluateArithmeticOperation(String expression, Environment env) {
        ArithmeticOperations arithmeticOperation = new ArithmeticOperations(expression);
        return arithmeticOperation.processOperation(expression);
    }

    // private String evaluateQuote(String expression, Environment env) {
    //     Quote quote = new Quote(expression);
    //     return quote.evaluate(env);
    // }

    // private String evaluateSetQ(String expression, Environment env) {
    //     SetQ setQ = new SetQ(expression);
    //     return setQ.evaluate(env);
    // }
}
