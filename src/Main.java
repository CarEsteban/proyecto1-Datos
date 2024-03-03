import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {        
        Analyzer analyzer = new Analyzer();
        //ArrayList<String> result = analyzer.analyze("3 4 2 + *");
        ArrayList<String> result = analyzer.analyze("(quote 'hola')");
        System.out.println(result);

    }
}
