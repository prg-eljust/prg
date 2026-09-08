import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Exercici 10 - Fitxers binaris.
 *
 * Guarda 10 enters aleatoris en un fitxer binari i després els llig i mostra.
 */
public class Exercici10 {

    public static void main(String[] args) {
        String nomFitxer = "enters.bin";
        Random rnd = new Random();

        // Escriptura
        try (FileOutputStream fos = new FileOutputStream(nomFitxer);
             DataOutputStream dos = new DataOutputStream(fos)) {
            System.out.print("Enters escrits: ");
            for (int i = 0; i < 10; i++) {
                int n = rnd.nextInt(1000);
                dos.writeInt(n);
                System.out.print(n + (i < 9 ? ", " : "\n"));
            }
        } catch (IOException e) {
            System.out.println("Error escrivint: " + e.getMessage());
            return;
        }

        // Lectura
        try (FileInputStream fis = new FileInputStream(nomFitxer);
             DataInputStream dis = new DataInputStream(fis)) {
            System.out.print("Enters llegits : ");
            for (int i = 0; i < 10; i++) {
                int n = dis.readInt();
                System.out.print(n + (i < 9 ? ", " : "\n"));
            }
        } catch (IOException e) {
            System.out.println("Error llegint: " + e.getMessage());
        }
    }
}
