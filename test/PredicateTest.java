import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PredicateTest {

    @Test
    public void testAtom() {
        Environment env = new Environment();
        Predicate predicate = new Predicate();

        // Prueba con un número (átomo)
        assertEquals("true", predicate.execute("(atom 42)", env));

        // Prueba con una cadena (átomo)
        assertEquals("true", predicate.execute("(atom foo)", env));

        // Prueba con una lista (no debería ser un átomo)
        assertEquals("false", predicate.execute("(atom (1 2 3))", env));
    }

    @Test
    public void testList() {
        Environment env = new Environment();
        Predicate predicate = new Predicate();

        // Prueba con una lista
        env.defineVariable("list", new ArrayList<>(List.of(1, 2, 3))); // Asegurando que sea aceptado como una lista.
        assertEquals("true", predicate.execute("(list (list 1 2 3))", env));

        // Prueba con un átomo (no debería ser una lista)
        assertEquals("false", predicate.execute("(list foo)", env));
    }

    @Test
    public void testEqual() {
        Environment env = new Environment();
        Predicate predicate = new Predicate();

        // Prueba con dos átomos iguales
        assertEquals("true", predicate.execute("(equal 5 5)", env));

        // Prueba con dos átomos diferentes
        assertEquals("false", predicate.execute("(equal 5 10)", env));
    }

    @Test
    public void testLessThan() {
        Environment env = new Environment();
        Predicate predicate = new Predicate();

        // Prueba con dos números donde el primero es menor que el segundo
        assertEquals("true", predicate.execute("(< 3 5)", env));

        // Prueba con dos números donde el primero es mayor que el segundo
        assertEquals("false", predicate.execute("(< 5 3)", env));
    }

    @Test
    public void testGreaterThan() {
        Environment env = new Environment();
        Predicate predicate = new Predicate();

        // Prueba con dos números donde el primero es mayor que el segundo
        assertEquals("true", predicate.execute("(> 5 3)", env));

        // Prueba con dos números donde el primero es menor que el segundo
        assertEquals("false", predicate.execute("(> 3 5)", env));
    }
}
