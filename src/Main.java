import java.util.Scanner;

public class Main {
    public static void main(String[] args) {        
        Analyzer analyzer = new Analyzer();
        Scanner scan = new Scanner(System.in);
        String input;
        int result;
        boolean keep = true;

        System.out.println("Welcome to the LISP Compiler");

        while (keep) {
            System.out.println("Enter an expression...");
            input = scan.nextLine();
    
            result = analyzer.tokenizer(input);

            if(input.equals("exit")){
                keep=false;
                System.exit(0);
            }else if(result==-1){
                System.out.println("Sintaxis Error");
            }else{
                //here the result of any operation
                System.out.println("valid operation: "+result);
            }

        }


    }
}