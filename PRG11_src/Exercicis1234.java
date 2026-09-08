import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Exercicis 1, 2, 3 i 4 - Consultar característiques de fitxers.
 *
 * Ex1: llegirFitxer()           → demana nom per teclat, retorna File o null
 * Ex2: mostraAtributsFitxer()   → mostra info del fitxer, retorna false si no existeix
 * Ex3: llistaFitxers()          → llista contingut d'un directori
 * Ex4: llistaFitxersRecursivament() → llistat recursiu
 */
public class Exercicis1234 {

    // -----------------------------------------------------------------------
    // Funció auxiliar per formatar dates
    // -----------------------------------------------------------------------
    public static String dataString(long data) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(data));
    }

    // -----------------------------------------------------------------------
    // Exercici 1
    // -----------------------------------------------------------------------
    public static File llegirFitxer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix el nom del fitxer o directori: ");
        String nom = sc.nextLine().trim();
        File f = new File(nom);
        if (f.exists()) {
            return f;
        } else {
            System.out.println("El fitxer/directori '" + nom + "' no existeix.");
            return null;
        }
    }

    // -----------------------------------------------------------------------
    // Exercici 2
    // -----------------------------------------------------------------------
    public static boolean mostraAtributsFitxer(File f) {
        if (!f.exists()) {
            System.out.println("El fitxer no existeix.");
            return false;
        }
        System.out.println("Nom        : " + f.getName());
        System.out.println("Ruta abs.  : " + f.getAbsolutePath());
        System.out.println("Directori? : " + f.isDirectory());
        System.out.println("Fitxer?    : " + f.isFile());
        System.out.println("Grandària  : " + f.length() + " bytes");
        System.out.println("Lectura?   : " + f.canRead());
        System.out.println("Escriptura?: " + f.canWrite());
        System.out.println("Data       : " + dataString(f.lastModified()));
        return true;
    }

    // -----------------------------------------------------------------------
    // Exercici 3
    // -----------------------------------------------------------------------
    public static void llistaFitxers(File directori) {
        if (!directori.isDirectory()) {
            System.out.println("'" + directori.getName() + "' no és un directori.");
            return;
        }
        File[] fitxers = directori.listFiles();
        if (fitxers == null || fitxers.length == 0) {
            System.out.println("El directori és buit.");
            return;
        }
        for (File f : fitxers) {
            mostraAtributsFitxer(f);
            System.out.println("---");
        }
    }

    // -----------------------------------------------------------------------
    // Exercici 4
    // -----------------------------------------------------------------------
    public static void llistaFitxersRecursivament(File directori) {
        llistaFitxersRecursivament(directori, 0);
    }

    private static void llistaFitxersRecursivament(File directori, int nivell) {
        if (!directori.isDirectory()) {
            return;
        }
        File[] fitxers = directori.listFiles();
        if (fitxers == null) return;
        String indent = "  ".repeat(nivell);
        for (File f : fitxers) {
            System.out.print(indent);
            mostraAtributsFitxer(f);
            System.out.println(indent + "---");
            if (f.isDirectory()) {
                llistaFitxersRecursivament(f, nivell + 1);
            }
        }
    }

    // -----------------------------------------------------------------------
    // main de demostració
    // -----------------------------------------------------------------------
    public static void main(String[] args) {
        // Ex1: demanem un fitxer/directori
        File f = llegirFitxer();
        if (f == null) return;

        System.out.println("\n--- Atributs ---");
        mostraAtributsFitxer(f);

        if (f.isDirectory()) {
            System.out.println("\n--- Llistat pla ---");
            llistaFitxers(f);

            System.out.println("\n--- Llistat recursiu ---");
            llistaFitxersRecursivament(f);
        }
    }
}
