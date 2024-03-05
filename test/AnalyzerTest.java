import org.junit.Test;
import org.junit.Assert;

public class AnalyzerTest {

    @Test
    public void testTokenizerWithArithmeticOperations() {
        Analyzer analyzer = new Analyzer();
        int result = analyzer.tokenizer("( 1 2 + ) ");
        Assert.assertEquals("Debería reconocer una operación aritmética", 1, result);
    }

    @Test
    public void testTokenizerWithQuote() {
        Analyzer analyzer = new Analyzer();
        int result = analyzer.tokenizer("( quote algo ) ");
        Assert.assertEquals("Debería reconocer una operación quote", 2, result);
    }

    @Test
    public void testTokenizerWithSetq() {
        Analyzer analyzer = new Analyzer();
        int result = analyzer.tokenizer("( setq variable 10 ) ");
        Assert.assertEquals("Debería reconocer una operación setq", 3, result);
    }

    @Test
    public void testTokenizerWithUnknownOperation() {
        Analyzer analyzer = new Analyzer();
        int result = analyzer.tokenizer("( unknown op ) ");
        Assert.assertEquals("Debería retornar -1 para operaciones desconocidas", -1, result);
    }
}
