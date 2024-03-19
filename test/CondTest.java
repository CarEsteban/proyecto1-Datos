import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CondTest {

    @Test
    void testExecute() {
        // Mock environment for testing
        Environment env = new Environment();
        env.defineVariable("a", "3");
        env.defineVariable("b", "5");

        // Test input
        String input = "( cond ( > a b ) ( quote mayor ) ( quote menor ) )";
        Cond cond = new Cond();
        String result = cond.execute(input, env);

        // Assert the result
        assertEquals("( quote menor )", result);
    }
}
