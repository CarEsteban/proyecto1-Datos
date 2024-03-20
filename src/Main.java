import java.util.Scanner;

/**
 * Clase principal que funciona como intérprete de expresiones LISP-like.
 */
public class Main {

    /**
     * Método principal que ejecuta el intérprete.
     * @param args Los argumentos de línea de comandos (no utilizados en este programa).
     * @throws Exception Si ocurre un error durante la evaluación de expresiones.
     */
    public static void main(String[] args) throws Exception {
        IEvaluator evaluator = new Evaluator();
        Environment env = new Environment();
        Scanner scan = new Scanner(System.in);
        String input;
        String result;
        boolean keep = true;

        System.out.println("Welcome to the LISP Compiler");

        while (keep) {
            System.out.println("Enter an expression...");
            input = scan.nextLine();

            result = evaluator.evaluate(input, env);

            if (input.equals("(exit)")) {
                keep = false;
                System.exit(0);
            } else if (result.equals("-1")) {
                System.out.println("Syntax Error");
            } else {
                // here the result of any operation
                System.out.println(result);
            }
        }
    }
}
