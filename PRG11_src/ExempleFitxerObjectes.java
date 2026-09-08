import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Exemple §6 - Fitxers d'objectes amb ObjectOutputStream i ObjectInputStream.
 * Guarda dos objectes Alumne en un fitxer i torna a llegir-los.
 */
public class ExempleFitxerObjectes {

    static class Alumne implements Serializable {
        String nom;
        int edat;

        Alumne(String nom, int edat) {
            this.nom = nom;
            this.edat = edat;
        }

        @Override
        public String toString() {
            return nom + " (" + edat + " anys)";
        }
    }

    public static void main(String[] args) {
        String nomFitxer = "alumnes.dat";

        // --- Escriptura ---
        try (FileOutputStream fos = new FileOutputStream(nomFitxer);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new Alumne("Anna", 20));
            oos.writeObject(new Alumne("Pere", 22));
            System.out.println("Objectes guardats a '" + nomFitxer + "'.");
        } catch (IOException e) {
            System.out.println("Error escrivint: " + e.getMessage());
            return;
        }

        // --- Lectura ---
        try (FileInputStream fis = new FileInputStream(nomFitxer);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                Alumne a = (Alumne) ois.readObject();
                System.out.println("Alumne llegit: " + a);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error llegint: " + e.getMessage());
        }
    }
}
