import java.io.FileReader;
import java.io.IOException;

/**
 * Exemple §3.1 - Lectura caràcter a caràcter amb FileReader.
 * Llegim d'un fitxer de text i ho mostrem per pantalla.
 */
public class ExempleLecturaCaracters {

    public static void mostrarCaractersDeFitxer(String nomFitxer) throws IOException {
        FileReader fr = new FileReader(nomFitxer);
        while (fr.ready()) {
            char lletra = (char) fr.read();
            System.out.print(lletra);
        }
        fr.close();
    }

    public static void main(String[] args) {
        // Crea un fitxer de prova si no existeix, o passa el nom com a argument
        String nomFitxer = args.length > 0 ? args[0] : "prova.txt";
        try {
            mostrarCaractersDeFitxer(nomFitxer);
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        }
    }
}
