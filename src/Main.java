import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        IEvaluator evaluator = new Evaluator();
        Environment env = new Environment();
        Scanner scan = new Scanner(System.in);
        String input;
        String result;
        boolean keep = true;

        System.out.println("Welcome to the LISP Compiler");

        IFunction setq = new SetQ();
        System.out.println(setq.execute("( setq x hola )", env));

        while (keep) {
            System.out.println("Enter an expression...");
            input = scan.nextLine();

            result = evaluator.evaluate(input, env);

            if (input.equals("exit")) {
                keep = false;
                System.exit(0);
            } else if (result.equals("-1")) {
                System.out.println("Sintaxis Error");
            } else {
                // here the result of any operation
                System.out.println(result);
            }

        }

    }
}