import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Exemple §5 - Fitxers binaris amb DataOutputStream i DataInputStream.
 * Escriu un enter, un double i un String en binari i torna a llegir-los.
 */
public class ExempleFitxerBinari {

    public static void main(String[] args) {
        String nomFitxer = "dades.bin";

        // --- Escriptura ---
        try (FileOutputStream fos = new FileOutputStream(nomFitxer);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(42);
            dos.writeDouble(3.14);
            dos.writeUTF("Hola");
            System.out.println("Dades escrites al fitxer '" + nomFitxer + "'.");
        } catch (IOException e) {
            System.out.println("Error escrivint: " + e.getMessage());
            return;
        }

        // --- Lectura (mateix ordre!) ---
        try (FileInputStream fis = new FileInputStream(nomFitxer);
             DataInputStream dis = new DataInputStream(fis)) {
            int n       = dis.readInt();
            double d    = dis.readDouble();
            String text = dis.readUTF();
            System.out.println("Enter llegit  : " + n);
            System.out.println("Double llegit : " + d);
            System.out.println("String llegit : " + text);
        } catch (IOException e) {
            System.out.println("Error llegint: " + e.getMessage());
        }
    }
}
