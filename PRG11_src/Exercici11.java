import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Exercici 11 - Fitxers d'objectes.
 *
 * Classe Producte (nom, preu, stock) serialitzable.
 * Programa amb menú que permet:
 *   1. Afegir un producte al fitxer
 *   2. Llistar tots els productes
 *   0. Sortir
 *
 * Estratègia recomanada (§6.2): carrega tots els objectes en un ArrayList,
 * opera sobre la llista i volcà-la al fitxer en sortir.
 */
public class Exercici11 {

    // -----------------------------------------------------------------------
    // Classe Producte
    // -----------------------------------------------------------------------
    static class Producte implements Serializable {
        String nom;
        double preu;
        int    stock;

        Producte(String nom, double preu, int stock) {
            this.nom   = nom;
            this.preu  = preu;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return String.format("%-20s  Preu: %8.2f€  Stock: %d", nom, preu, stock);
        }
    }

    static final String FITXER = "productes.dat";

    // -----------------------------------------------------------------------
    // Carregar tots els productes del fitxer a un ArrayList
    // -----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    static ArrayList<Producte> carregarProductes() {
        File f = new File(FITXER);
        if (!f.exists() || f.length() == 0) {
            return new ArrayList<>();
        }
        try (FileInputStream fis = new FileInputStream(FITXER);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (ArrayList<Producte>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Avís carregant productes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // -----------------------------------------------------------------------
    // Guardar tot l'ArrayList al fitxer (sobreescriptura completa)
    // -----------------------------------------------------------------------
    static void guardarProductes(ArrayList<Producte> llista) {
        try (FileOutputStream fos = new FileOutputStream(FITXER);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(llista);
        } catch (IOException e) {
            System.out.println("Error guardant productes: " + e.getMessage());
        }
    }

    // -----------------------------------------------------------------------
    // main
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producte> llista = carregarProductes();

        boolean sortir = false;
        while (!sortir) {
            System.out.println("\n=== Gestió de Productes ===");
            System.out.println("1. Afegir producte");
            System.out.println("2. Llistar productes");
            System.out.println("0. Sortir i guardar");
            System.out.print("Opció: ");
            String opcio = sc.nextLine().trim();

            switch (opcio) {
                case "1":
                    System.out.print("Nom   : ");
                    String nom = sc.nextLine().trim();
                    System.out.print("Preu  : ");
                    double preu = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Stock : ");
                    int stock = Integer.parseInt(sc.nextLine().trim());
                    llista.add(new Producte(nom, preu, stock));
                    System.out.println("Producte afegit.");
                    break;

                case "2":
                    if (llista.isEmpty()) {
                        System.out.println("No hi ha productes.");
                    } else {
                        System.out.println("\n--- Llista de productes ---");
                        for (int i = 0; i < llista.size(); i++) {
                            System.out.println((i + 1) + ". " + llista.get(i));
                        }
                    }
                    break;

                case "0":
                    guardarProductes(llista);
                    System.out.println("Productes guardats. Fins aviat!");
                    sortir = true;
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }
}
