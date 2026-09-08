import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Exemple §5.1 - Accés aleatori amb RandomAccessFile.
 * Escriu 5 enters, després torna al principi i els rellegeix d'un en un.
 */
public class ExempleAccesAleatori {

    public static void main(String[] args) {
        String nomFitxer = "aleatori.bin";

        try (RandomAccessFile raf = new RandomAccessFile(nomFitxer, "rw")) {
            // Escrivim 5 enters
            for (int i = 1; i <= 5; i++) {
                raf.writeInt(i * 10);
            }
            System.out.println("Grandària del fitxer: " + raf.length() + " bytes");

            // Tornem al principi i llegim seqüencialment
            raf.seek(0);
            System.out.println("Lectura seqüencial:");
            for (int i = 0; i < 5; i++) {
                System.out.println("  Posició " + raf.getFilePointer() + " -> " + raf.readInt());
            }

            // Accés directe: llegim el 3r enter (posició 2*4 = 8 bytes des de l'inici)
            raf.seek(2 * Integer.BYTES);
            int tercer = raf.readInt();
            System.out.println("3r enter (accés directe): " + tercer);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
