import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Exercici 9 - Còpia de fitxer caràcter a caràcter.
 *
 * Demana el nom del fitxer d'origen i el de destí per teclat.
 * Copia el contingut de l'origen al destí usant FileReader i FileWriter.
 */
public class Exercici9 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Fitxer d'origen : ");
        String origen = sc.nextLine().trim();
        System.out.print("Fitxer de destí : ");
        String desti  = sc.nextLine().trim();

        try (FileReader fr = new FileReader(origen);
             FileWriter fw = new FileWriter(desti)) {
            int codi;
            while ((codi = fr.read()) != -1) {
                fw.write((char) codi);
            }
            System.out.println("Còpia completada: '" + origen + "' -> '" + desti + "'.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
