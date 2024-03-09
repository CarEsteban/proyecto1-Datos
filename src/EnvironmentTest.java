import static org.junit.Assert.*;

import org.junit.Test;

public class EnvironmentTest {
    @Test
    public void testDefineAndGetVariable() {
        Environment env = new Environment();
        env.defineVariable("myInteger", 42);
        env.defineVariable("myString", "Hello");

        assertTrue(env.variableExists("myInteger"));
        assertTrue(env.variableExists("myString"));
        assertFalse(env.variableExists("nonExistentVariable"));

        assertEquals(Integer.valueOf(42), env.getIntegerVariable("myInteger"));
        assertEquals("Hello", env.getStringVariable("myString"));
    }

    @Test
    public void testSetAndGetVariable() {
        Environment env = new Environment();
        env.defineVariable("myInteger", 42);
        env.defineVariable("myString", "Hello");

        env.setVariable("myInteger", 100);
        env.setVariable("myString", "World");

        assertEquals(Integer.valueOf(100), env.getIntegerVariable("myInteger"));
        assertEquals("World", env.getStringVariable("myString"));
    }

    @Test
    public void testVariableDoesNotExist() {
        Environment env = new Environment();

        assertFalse(env.variableExists("nonExistentVariable"));
        try {
            env.getIntegerVariable("nonExistentVariable");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Excepción esperada
        }
        try {
            env.getStringVariable("nonExistentVariable");
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Excepción esperada
        }
    }

    @Test
    public void testRemoveVariable() {
        Environment env = new Environment();
        env.defineVariable("myInteger", 42);
        env.defineVariable("myString", "Hello");

        assertTrue(env.variableExists("myInteger"));
        assertTrue(env.variableExists("myString"));

        env.removeVariable("myInteger");
        env.removeVariable("myString");

        assertFalse(env.variableExists("myInteger"));
        assertFalse(env.variableExists("myString"));
    }
}
