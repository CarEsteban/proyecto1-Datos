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
            return value.toString(); // Convertir el valor a cadena
        } else if (parent != null) {
            return parent.getVariable(name);
        } else {
            return null;
        }
    }

    // int variableEntera = (int) environment.getVariable("miVariableEntera");
    // public Integer getIntegerVariable(String name) {
    //     Object value = getVariable(name);
    //     if (value instanceof Integer) {
    //         return (Integer) value;
    //     } else {
    //         throw new IllegalArgumentException("Variable '" + name + "' is not an integer.");
    //     }
    // }
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

    // string variableString = (String) environment.getVariable("miVariableString");
    public String getStringVariable(String name) {
        Object value = getVariable(name);
        if (value instanceof String) {
            return (String) value;
        } else {
            throw new IllegalArgumentException("Variable '" + name + "' is not a string.");
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