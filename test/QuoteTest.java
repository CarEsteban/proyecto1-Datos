import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QuoteTest {

    @Test
    public void testQuoteWithLiteral() {
        Environment env = new Environment();
        Quote quote = new Quote("(quote \"hello\")");

        assertEquals("hello", quote.execute("(quote \"hello\")", env));
    }

    @Test
    public void testQuoteWithVariable() {
        Environment env = new Environment();
        env.defineVariable("x", "world");
        Quote quote = new Quote("(quote x)");

        assertEquals("world", quote.execute("(quote x)", env));
    }

    @Test
    public void testQuoteInvalidExpression() {
        Environment env = new Environment();
        Quote quote = new Quote("(quote)");

        assertEquals("Error: input no válido para Quote.", quote.execute("(quote)", env));
    }
}
