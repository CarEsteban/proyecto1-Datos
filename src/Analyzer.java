import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Analyzer {

    String input;

    public static void evaluar(String texto) {
        // Patrones para identificar diferentes tipos de caracteres
        String patronLetras = "[a-zA-Z]";
        String patronDigitos = "\\d";
        String patronEspaciosBlancos = "\\s";
        String patronEspeciales = "\\W"; // No incluye espacios en blanco ni dígitos

        // Listas para almacenar los caracteres separados
        ArrayList<String> letras = new ArrayList<>();
        ArrayList<String> digitos = new ArrayList<>();
        ArrayList<String> espaciosBlancos = new ArrayList<>();
        ArrayList<String> caracteresEspeciales = new ArrayList<>();

        // Función para buscar y almacenar los caracteres según el patrón
        buscarYAlmacenar(texto, patronLetras, letras);
        buscarYAlmacenar(texto, patronDigitos, digitos);
        buscarYAlmacenar(texto, patronEspaciosBlancos, espaciosBlancos);
        buscarYAlmacenar(texto, patronEspeciales, caracteresEspeciales);

        // Imprimir las listas
        System.out.println("Letras: " + letras);
        System.out.println("Dígitos: " + digitos);
        System.out.println("Espacios en blanco: " + espaciosBlancos);
        System.out.println("Caracteres especiales: " + caracteresEspeciales);
    }

    private static void buscarYAlmacenar(String texto, String patron, ArrayList<String> lista) {
        Pattern pat = Pattern.compile(patron);
        Matcher mat = pat.matcher(texto);
        while (mat.find()) {
            lista.add(mat.group());
        }
    }
}