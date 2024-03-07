import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> variables;
    private Environment parent; // Si el entorno tiene un entorno padre

    public Environment() {
        this.variables = new HashMap<>();
        this.parent = null;
    }

    public Environment(Environment parent) {
        this();
        this.parent = parent;
    }

    public void defineVariable(String name, Object value) {
        variables.put(name, value);
    }

    public Object getVariable(String name) {
        Object value = variables.get(name);
        if (value != null) {
            return value; // La variable está definida en este entorno
        } else if (parent != null) {
            return parent.getVariable(name); // Busca en el entorno padre
        } else {
            return null; // La variable no está definida en ningún entorno
        }
    }

    public void setVariable(String name, Object value) {
        if (variables.containsKey(name)) {
            variables.put(name, value); // Actualiza el valor de la variable existente
        } else if (parent != null) {
            parent.setVariable(name, value); // Si no está en este entorno, busca en el padre
        } else {
            // La variable no está definida en ningún entorno, puedes definirla aquí
            defineVariable(name, value);
        }
    }

    public boolean variableExists(String name) {
        return getVariable(name) != null;
    }

    public void removeVariable(String name) {
        variables.remove(name);
    }
}