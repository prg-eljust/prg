import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * BuscarEstudiants.java
 *
 * Exemple de lectura amb filtre (READ) usant la classe Filters.
 * Cerca estudiants per cicle, per edat i per nota mínima als mòduls.
 *
 * Conceptes il·lustrats:
 *   - Filters.eq() per igualtat sobre camps en valencià ("cicle", "nom"...)
 *   - Filters.lte() / Filters.gte() per comparacions numèriques
 *   - Filters.and() per combinar condicions
 *   - first() per obtenir un sol document
 *   - getDouble() per llegir notes amb decimals
 */
public class BuscarEstudiants {

    static final String URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create(URI)) {

            MongoCollection<Document> col = client.getDatabase("escola")
                                                  .getCollection("estudiants");

            // --- Exemple 1: Filtrar per cicle ---
            Bson filtre1 = Filters.eq("cicle", "Enginyeria de Sistemes");

            System.out.println("=== Estudiants d'Enginyeria de Sistemes ===");
            for (Document doc : col.find(filtre1)) {
                System.out.printf("  %s, %d anys%n",
                        doc.getString("nom"),
                        doc.getInteger("edat"));
            }

            System.out.println();

            // --- Exemple 2: Filtrar per edat màxima ---
            // Filters.lte("camp", valor) → camp <= valor
            Bson filtre2 = Filters.lte("edat", 21);

            System.out.println("=== Estudiants de 21 anys o menys ===");
            for (Document doc : col.find(filtre2)) {
                System.out.printf("  %s, %d anys - %s%n",
                        doc.getString("nom"),
                        doc.getInteger("edat"),
                        doc.getString("cicle"));
            }

            System.out.println();

            // --- Exemple 3: Combinar condicions amb AND ---
            Bson filtre3 = Filters.and(
                    Filters.lte("edat", 23),
                    Filters.gte("edat", 20)   // gte = greater than or equal (>=)
            );

            System.out.println("=== Estudiants entre 20 i 23 anys ===");
            for (Document doc : col.find(filtre3)) {
                System.out.printf("  %s, %d anys - %s%n",
                        doc.getString("nom"),
                        doc.getInteger("edat"),
                        doc.getString("cicle"));
            }

            System.out.println();

            // --- Exemple 4: Obtenir un sol document i mostrar els mòduls ---
            // first() retorna el primer resultat o null si no n'hi ha cap
            Document ana = col.find(Filters.eq("nom", "Ana Pérez")).first();

            if (ana != null) {
                System.out.println("=== Mòduls d'Ana Pérez ===");

                List<Document> moduls = ana.getList("moduls", Document.class);
                for (Document modul : moduls) {
                    // getDouble() per llegir la nota amb decimals (0.0 - 10.0)
                    double nota  = modul.getDouble("nota");
                    String estat = nota < 5.0 ? "SUSPÈS" : "APROVAT";
                    System.out.printf("  %-42s %.1f  [%s]%n",
                            modul.getString("nom"), nota, estat);
                }
            } else {
                System.out.println("No s'ha trobat l'estudiant.");
            }
        }
    }
}
