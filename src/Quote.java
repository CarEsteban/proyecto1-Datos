public class Quote implements IFunction {
    @Override
    public String processOperation(String expression) {
        // Mostrar la expresión
        System.out.println("Expresión original: " + expression);

        // Pasar string y borrar la primera parte
        String[] parts = expression.split("\\s+", 2);
        if (parts.length < 2) {
            return "Invalid quote expression";
        }
        String remaining = parts[1];

        // Retornar la frase
        return remaining.trim();
    }
}
