import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Exemple §3.2 - Lectura línia a línia amb BufferedReader.
 * Llegim d'un fitxer de text i ho mostrem per pantalla.
 */
public class ExempleLecturaLinies {

    public static void mostrarLiniesDeFitxer(String nomFitxer) throws IOException {
        FileReader fr = new FileReader(nomFitxer);
        BufferedReader br = new BufferedReader(fr);
        String s;
        while (br.ready()) {
            s = br.readLine();
            System.out.println(s);
        }
        br.close();
        fr.close();
    }

    public static void main(String[] args) {
        String nomFitxer = args.length > 0 ? args[0] : "prova.txt";
        try {
            mostrarLiniesDeFitxer(nomFitxer);
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer: " + e.getMessage());
        }
    }
}
