import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Exercici 5 - Lectura caràcter a caràcter amb FileReader.
 *
 * Donat un fitxer de text, indica:
 *  - Quantes vocals hi ha
 *  - Quants espais en blanc
 *  - Quantes majúscules
 *  - Quantes minúscules
 *  - Ampliació: quantes paraules (separadors: espai, . , ; : ! ?)
 */
public class Exercici5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom del fitxer: ");
        String nomFitxer = sc.nextLine().trim();

        int vocals     = 0;
        int espais     = 0;
        int majuscules = 0;
        int minuscules = 0;
        int paraules   = 0;

        // Per a comptar paraules necessitem saber si l'últim caràcter era separador
        boolean enParaula = false;
        String separadors = " .,;:!?";

        try (FileReader fr = new FileReader(nomFitxer)) {
            int codi;
            while ((codi = fr.read()) != -1) {
                char c = (char) codi;

                // Vocals (incloem accentuades comunes)
                if ("aeiouàèéíïóòúüAEIOUÀÈÉÍÏÓÒÚÜ".indexOf(c) >= 0) {
                    vocals++;
                }

                // Espais
                if (c == ' ') {
                    espais++;
                }

                // Majúscules / minúscules
                if (Character.isUpperCase(c)) {
                    majuscules++;
                } else if (Character.isLowerCase(c)) {
                    minuscules++;
                }

                // Paraules
                if (separadors.indexOf(c) >= 0 || c == '\n' || c == '\r' || c == '\t') {
                    enParaula = false;
                } else {
                    if (!enParaula) {
                        paraules++;
                        enParaula = true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Vocals     : " + vocals);
        System.out.println("Espais     : " + espais);
        System.out.println("Majúscules : " + majuscules);
        System.out.println("Minúscules : " + minuscules);
        System.out.println("Paraules   : " + paraules);
    }
}
