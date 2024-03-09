import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> variables;
    private Environment parent;

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

    public String getVariable(String name) {
        Object value = variables.get(name);
        if (value != null) {
            return (String) value;
        } else if (parent != null) {
            return parent.getVariable(name);
        } else {
            return null;
        }
    }

    public void setVariable(String name, Object value) {
        if (variables.containsKey(name)) {
            variables.put(name, value);
        } else if (parent != null) {
            parent.setVariable(name, value);
        } else {
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