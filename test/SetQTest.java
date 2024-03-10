import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class SetQTest {
    
    private SetQ setQ;
    private Environment env;

    @Before
    public void setUp() {
        env = new Environment();
        setQ = new SetQ();
    }

    @Test
    public void testEjecucionValidaConString() {
        // Asumiendo que el valor debe ser interpretado como String
        String resultado = setQ.execute("( setq x valor )", env);
        assertEquals("x: valor", resultado);
        assertEquals("valor", env.getStringVariable("x"));
    }

    @Test
    public void testEjecucionValidaConNumero() {
        // Asumiendo que el valor debe ser interpretado como Integer
        String resultado = setQ.execute("( setq x 10 )", env);
        assertEquals("x: 10", resultado);
        assertEquals("10", env.getVariable("x"));
    }

    @Test
    public void testEntradaInvalida() {
        String resultado = setQ.execute("( setq x )", env);
        assertEquals("Error: input no válido.", resultado);
    }

    @Test
    public void testManejoEspaciosAdicionales() {
        // Este test verifica que el manejo de espacios extra no afecte el resultado
        String resultado = setQ.execute("   ( setq  z  30 )  ", env);
        assertEquals("z: 30", resultado);
        assertEquals("30", env.getVariable("z"));
    }
}
