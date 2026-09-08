import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Exercici 6 - Corregir exàmens tipus test.
 *
 * - Fitxer de l'alumne (p.ex. "Pep.txt"): primera línia amb 20 respostes (A/B/C/D/-)
 *   Exemple: ABCD-BBA-CCDBACBC-DA
 * - Fitxer solucio.txt: primera línia amb la solució (sense guions)
 *   Exemple: ABCDDCBAACCDBAABCDDA
 *
 * Puntuació: correcta +0.5, incorrecta -0.125, no contestada 0.
 *
 * El programa demana noms de fitxers en bucle fins que s'introduïsca "fi".
 */
public class Exercici6 {

    static final String FITXER_SOLUCIO = "solucio.txt";
    static final int    NUM_PREGUNTES  = 20;

    /** Llig la primera línia d'un fitxer i retorna els caràcters com a array. */
    static char[] llegirRespostes(String nomFitxer) throws IOException {
        FileReader fr = new FileReader(nomFitxer);
        StringBuilder sb = new StringBuilder();
        int codi;
        while ((codi = fr.read()) != -1) {
            char c = (char) codi;
            if (c == '\n' || c == '\r') break;
            sb.append(c);
        }
        fr.close();
        return sb.toString().toCharArray();
    }

    /** Corregeix un alumne donats els seus respostes i la solució. */
    static void corregir(String nomFitxerAlumne, char[] solucio) {
        char[] respostes;
        try {
            respostes = llegirRespostes(nomFitxerAlumne);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir '" + nomFitxerAlumne + "': " + e.getMessage());
            return;
        }

        int encertades  = 0;
        int fallades    = 0;
        int noContestes = 0;

        for (int i = 0; i < NUM_PREGUNTES; i++) {
            char r = (i < respostes.length) ? respostes[i] : '-';
            if (r == '-') {
                noContestes++;
            } else if (r == solucio[i]) {
                encertades++;
            } else {
                fallades++;
            }
        }

        double nota = encertades * 0.5 - fallades * 0.125;

        System.out.println("Alumne    : " + nomFitxerAlumne);
        System.out.println("Encertades: " + encertades);
        System.out.println("Fallades  : " + fallades);
        System.out.println("No contest: " + noContestes);
        System.out.printf( "Nota      : %.3f%n%n", nota);
    }

    public static void main(String[] args) {
        // Carreguem la solució
        char[] solucio;
        try {
            solucio = llegirRespostes(FITXER_SOLUCIO);
        } catch (IOException e) {
            System.out.println("No s'ha pogut llegir el fitxer de solució '" + FITXER_SOLUCIO + "': " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Nom del fitxer de l'alumne (o 'fi' per acabar): ");
            String nom = sc.nextLine().trim();
            if (nom.equalsIgnoreCase("fi")) break;
            corregir(nom, solucio);
        }
    }
}
