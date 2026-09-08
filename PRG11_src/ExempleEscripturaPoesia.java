import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Exemple §4.2 - Escriptura línia a línia amb BufferedWriter.
 * Escriu una poesia a un fitxer de text.
 */
public class ExempleEscripturaPoesia {

    public static void main(String[] args) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("poesia.txt");
            bw = new BufferedWriter(fw);
            bw.write("No hi havia a València dos amants com nosaltres.");
            bw.newLine();
            bw.write("Feroçment ens amàvem des del matí a la nit.");
            bw.newLine();
            bw.write("Tot ho recorde mentre vas estenent la roba.");
            bw.newLine();
            bw.write("Han passat anys, molts anys; han passat moltes coses.");
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Poesia guardada a 'poesia.txt'.");
        } catch (IOException ex) {
            System.out.println("Error escrivint el fitxer: " + ex.getMessage());
        }
    }
}
