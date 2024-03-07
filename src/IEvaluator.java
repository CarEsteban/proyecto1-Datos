public interface IEvaluator {
    Object evaluate(String expression, Environment env) throws Exception;
}
