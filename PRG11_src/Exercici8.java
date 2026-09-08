import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Exercici 8 - Escriptura de fitxers de text.
 *
 * Demana a l'usuari que introduïsca línies de text fins que escriga "fi".
 * Guarda totes les línies en un fitxer de text anomenat "sortida.txt".
 */
public class Exercici8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (FileWriter fw = new FileWriter("sortida.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {

            System.out.println("Escriu línies de text. Escriu 'fi' per acabar.");
            while (true) {
                System.out.print("> ");
                String linia = sc.nextLine();
                if (linia.equalsIgnoreCase("fi")) break;
                bw.write(linia);
                bw.newLine();
            }
            System.out.println("Text guardat a 'sortida.txt'.");

        } catch (IOException e) {
            System.out.println("Error escrivint el fitxer: " + e.getMessage());
        }
    }
}
