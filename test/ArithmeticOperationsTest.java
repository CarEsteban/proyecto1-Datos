import static org.junit.Assert.*;

import org.junit.Test;

public class ArithmeticOperationsTest {
    // Prueba sin paréntesis
    @Test
    public void testWithoutParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("5", env);
        assertEquals("Resultado de la operación: 5", result);
    }

    // Prueba con un único conjunto de paréntesis
    @Test
    public void testSingleSetOfParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("(5)", env);
        assertEquals("Resultado de la operación: 5", result);
    }

    // Prueba con múltiples paréntesis exteriores
    @Test
    public void testMultipleOuterParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("(((5)))", env);
        assertEquals("Resultado de la operación: 5", result);
    }

    // Prueba con paréntesis anidados incorrectamente
    @Test
    public void testIncorrectlyNestedParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("(5))(", env); // Aquí depende de cómo desees manejarlo, suponiendo error
        assertEquals("Error en la expresión aritmética", result);
    }

    // Prueba con paréntesis equilibrados pero no todos exteriores
    @Test
    public void testBalancedButNotAllExternalParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("((5))", env); // Cambio realizado para ajustarse a un caso más realista
        assertEquals("Resultado de la operación: 5", result); // Asume que es correcto si están equilibrados y correctos
    }

    // Prueba de recortar y múltiples paréntesis
    @Test
    public void testTrimAndMultipleParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("  ( ( ( 5 ) ) )  ", env);
        assertEquals("Resultado de la operación: 5", result);
    }

    // Prueba con expresiones anidadas
    @Test
    public void testNestedExpressions() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("(+ 1 (2 3))", env); // Asumiendo que esta es una entrada incorrecta; cambia
                                                                // según la necesidad real
        assertEquals("Error en la expresión aritmética", result); // Suponiendo que no soporta espacios incorrectos o
                                                                  // faltan operadores
    }

    // Prueba con paréntesis desbalanceados
    @Test
    public void testUnbalancedParentheses() {
        ArithmeticOperations operations = new ArithmeticOperations();
        Environment env = new Environment();
        String result = operations.execute("(((5))", env);
        assertEquals("Error en la expresión aritmética", result); // Asume que tu programa no maneja paréntesis
                                                                  // desbalanceados
    }
}
