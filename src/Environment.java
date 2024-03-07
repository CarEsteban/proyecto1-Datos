import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> variables;

    public Environment() {
        this.variables = new HashMap<>();
    }

    public void defineVariable(String name, Object value) {
        variables.put(name, value);
    }

    public Object getVariable(String name) {
        return variables.get(name);
    }

    public boolean variableExists(String name) {
        return variables.containsKey(name);
    }

    public void removeVariable(String name) {
        variables.remove(name);
    }
}
