/**
 * Interfaz para definir un evaluador de expresiones.
 */
public interface IEvaluator {

    /**
     * Evalúa una expresión en el contexto de un entorno dado.
     * @param expression La expresión a evaluar.
     * @param env El entorno en el que se evalúa la expresión.
     * @return El resultado de la evaluación como una cadena.
     * @throws Exception Si ocurre un error durante la evaluación.
     */
    String evaluate(String expression, Environment env) throws Exception;
}
