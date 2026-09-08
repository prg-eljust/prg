import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Exercici 7 - Lectura línia a línia amb BufferedReader.
 *
 * Donat un fitxer de text, indica:
 *  - Quants paràgrafs té (línies no buides separades per línies buides)
 *  - Per a cada paràgraf: el seu número i la quantitat de lletres que conté
 *
 * Es considera paràgraf qualsevol bloc de línies consecutives no buides.
 */
public class Exercici7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom del fitxer: ");
        String nomFitxer = sc.nextLine().trim();

        try (FileReader fr = new FileReader(nomFitxer);
             BufferedReader br = new BufferedReader(fr)) {

            int numParagraf  = 0;
            int lletresParagraf = 0;
            boolean enParagraf = false;

            String linia;
            // readLine() retorna null en arribar al final; usem aquest patró
            // en lloc de ready() per no perdre l'última línia
            while ((linia = br.readLine()) != null) {
                if (linia.trim().isEmpty()) {
                    // Línia buida: tanca el paràgraf actual si n'hi havia
                    if (enParagraf) {
                        System.out.println("  Paràgraf " + numParagraf + ": " + lletresParagraf + " lletres");
                        lletresParagraf = 0;
                        enParagraf = false;
                    }
                } else {
                    // Línia amb contingut
                    if (!enParagraf) {
                        numParagraf++;
                        enParagraf = true;
                    }
                    // Comptem només lletres (no espais ni signes de puntuació)
                    for (char c : linia.toCharArray()) {
                        if (Character.isLetter(c)) {
                            lletresParagraf++;
                        }
                    }
                }
            }
            // Si l'arxiu acaba sense línia buida final, tanquem l'últim paràgraf
            if (enParagraf) {
                System.out.println("  Paràgraf " + numParagraf + ": " + lletresParagraf + " lletres");
            }

            System.out.println("Total de paràgrafs: " + numParagraf);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
