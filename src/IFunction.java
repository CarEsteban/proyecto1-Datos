/**
 * Interfaz para definir una función que puede ejecutar una operación dada una entrada y un entorno.
 */
public interface IFunction {

    /**
     * Ejecuta la función con la entrada dada en el entorno especificado.
     * @param input La entrada para la función.
     * @param env El entorno en el que se ejecuta la función.
     * @return El resultado de la ejecución de la función como una cadena.
     */
    String execute(String input, Environment env);
}
