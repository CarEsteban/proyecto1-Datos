import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa un entorno de ejecución para variables y funciones.
 */
public class Environment {
    private Map<String, Object> variables;
    private Environment parent;

    /**
     * Constructor predeterminado que inicializa un nuevo entorno sin padre.
     */
    public Environment() {
        this.variables = new HashMap<>();
        this.parent = null;
    }

    /**
     * Constructor que inicializa un nuevo entorno con un entorno padre.
     * @param parent El entorno padre.
     */
    public Environment(Environment parent) {
        this();
        this.parent = parent;
    }

    /**
     * Define una variable en el entorno actual.
     * @param name Nombre de la variable.
     * @param value Valor de la variable.
     */
    public void defineVariable(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * Obtiene el valor de una variable del entorno actual o de uno de sus padres.
     * @param name Nombre de la variable.
     * @return El valor de la variable como una cadena, o null si la variable no está definida.
     */
    public String getVariable(String name) {
        Object value = variables.get(name);
        if (value != null) {
            return value.toString(); // Convertir el valor a cadena
        } else if (parent != null) {
            return parent.getVariable(name);
        } else {
            return null;
        }
    }

    /**
     * Obtiene el valor de una variable como un número de punto flotante (double).
     * @param name Nombre de la variable.
     * @return El valor de la variable como un double.
     * @throws IllegalArgumentException Si la variable no es numérica o no está definida.
     */
    public Double getDoubleVariable(String name) {
        Object value = getVariable(name);
        if (value instanceof Double) {
            return (Double) value;
        } else {
            try {
                return Double.parseDouble(value.toString());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Variable '" + name + "' is not a double.");
            }
        }
    }

    /**
     * Obtiene el valor de una variable como una cadena de texto.
     * @param name Nombre de la variable.
     * @return El valor de la variable como una cadena de texto.
     * @throws IllegalArgumentException Si la variable no es una cadena de texto o no está definida.
     */
    public String getStringVariable(String name) {
        Object value = getVariable(name);
        if (value instanceof String) {
            return (String) value;
        } else {
            throw new IllegalArgumentException("Variable '" + name + "' is not a string.");
        }
    }

    /**
     * Establece el valor de una variable en el entorno actual o en uno de sus padres.
     * @param name Nombre de la variable.
     * @param value Nuevo valor de la variable.
     */
    public void setVariable(String name, Object value) {
        if (variables.containsKey(name)) {
            variables.put(name, value);
        } else if (parent != null) {
            parent.setVariable(name, value);
        } else {
            defineVariable(name, value);
        }
    }

    /**
     * Verifica si una variable está definida en el entorno actual o en uno de sus padres.
     * @param name Nombre de la variable.
     * @return true si la variable está definida, false en caso contrario.
     */
    public boolean variableExists(String name) {
        return getVariable(name) != null;
    }

    /**
     * Elimina una variable del entorno actual.
     * @param name Nombre de la variable a eliminar.
     */
    public void removeVariable(String name) {
        variables.remove(name);
    }
}
