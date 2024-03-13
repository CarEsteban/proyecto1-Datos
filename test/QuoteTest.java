import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class QuoteTest {

    private Environment env;

    @Before
    public void setUp() {
        env = new Environment();
        env.defineVariable("x", "10");
    }

    @Test
    public void testEvaluateStringLiteral() {
        Quote quote = new Quote("quote \"hello\"");
        assertEquals("hello", quote.evaluate(env));
    }

    @Test
    public void testEvaluateVariable() {
        Quote quote = new Quote("quote x");
        assertEquals("quote 10", quote.evaluate(env));
    }

    @Test
    public void testEvaluateInvalidVariable() {
        Quote quote = new Quote("quote y");
        assertEquals("sintax error", quote.evaluate(env));
    }

    @Test
    public void testEvaluateInvalidSyntax() {
        Quote quote = new Quote("quote hello world");
        assertEquals("sintax error", quote.evaluate(env));
    }
}
