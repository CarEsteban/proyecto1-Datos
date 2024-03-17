import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class QuoteTest {

    @Test
    public void testQuoteWithLiteral() {
        Environment env = new Environment();
        Quote quote = new Quote();

        assertEquals("hello", quote.execute("(quote hello)", env));
    }

    @Test
    public void testQuoteWithVariable() {
        Environment env = new Environment();
        env.defineVariable("x", "world");
        Quote quote = new Quote();

        assertEquals("world", quote.execute("(quote x)", env));
    }

    @Test
    public void testQuoteInvalidExpression() {
        Environment env = new Environment();
        Quote quote = new Quote();

        assertEquals("Index out of bounds exception", quote.execute("(quote)", env));
        assertEquals("Index out of bounds exception", quote.execute("(quote hello world)", env));
    }
}
