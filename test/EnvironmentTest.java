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

        assertEquals("42", env.getVariable("myInteger"));
        assertEquals("Hello", env.getVariable("myString"));
    }

    @Test
    public void testSetAndGetVariable() {
        Environment env = new Environment();
        env.defineVariable("myInteger", 42);
        env.defineVariable("myString", "Hello");

        env.setVariable("myInteger", "100");
        env.setVariable("myString", "World");

        assertEquals("100", env.getVariable("myInteger"));
        assertEquals("World", env.getVariable("myString"));
    }

    @Test
    public void testVariableDoesNotExist() {
        Environment env = new Environment();

        assertFalse(env.variableExists("nonExistentVariable"));
        assertNull(env.getVariable("nonExistentVariable"));
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
