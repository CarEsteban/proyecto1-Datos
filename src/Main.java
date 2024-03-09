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

<<<<<<< HEAD
        IFunction setq = new SetQ();
        System.out.println(setq.execute("( setq x hola )", env));

=======
>>>>>>> 5c3d2ba9e50d76a9223b907a9b5e5ee96808b72b
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