import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * EliminarEstudiant.java
 *
 * Exemple d'eliminació (DELETE) amb el driver de MongoDB.
 * Elimina un o múltiples documents de la col·lecció.
 *
 * Conceptes il·lustrats:
 *   - Camps en valencià: "nom", "edat", "cicle"
 *   - deleteOne() per eliminar el primer document que coincideix
 *   - deleteMany() per eliminar tots els documents que coincideixen
 *   - Filters.in() per filtrar per una llista de valors
 *   - DeleteResult per verificar quants documents s'han eliminat
 */
public class EliminarEstudiant {

    static final String URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create(URI)) {

            MongoCollection<Document> col = client.getDatabase("escola")
                                                  .getCollection("estudiants");

            System.out.println("Estudiants abans: " + col.countDocuments());

            // --- Exemple 1: Eliminar un sol document per nom ---
            // El camp és "nom" (en valencià), no "nombre"
            Bson filtre1 = Filters.eq("nom", "Pau Martí");
            DeleteResult res1 = col.deleteOne(filtre1);

            System.out.println("Eliminat 'Pau Martí': " + res1.getDeletedCount() + " document/s");

            if (res1.getDeletedCount() == 0) {
                System.out.println("  -> No s'ha trobat l'estudiant.");
            }

            System.out.println();

            // --- Exemple 2: Eliminar múltiples documents per nom ---
            // Filters.in() filtra documents on el camp coincideix amb qualsevol
            // dels valors de la llista (equivalent a WHERE nom IN (...) en SQL)
            Bson filtre2 = Filters.in("nom", "Clara Vidal", "Marc Soler");
            DeleteResult res2 = col.deleteMany(filtre2);

            System.out.println("Eliminats estudiants de prova: " + res2.getDeletedCount() + " document/s");

            System.out.println();

            // --- Exemple 3: Eliminar per cicle ---
            // Eliminem tots els estudiants del cicle "ASIX" (exemple)
            Bson filtre3 = Filters.eq("cicle", "ASIX");
            DeleteResult res3 = col.deleteMany(filtre3);

            System.out.println("Eliminats estudiants d'ASIX: " + res3.getDeletedCount() + " document/s");

            System.out.println();
            System.out.println("Estudiants després: " + col.countDocuments());
        }
    }
}
