import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * LlistarEstudiants.java
 *
 * Exemple bàsic de lectura (READ) amb el driver de MongoDB.
 * Obté tots els documents de la col·lecció 'estudiants' i els mostra.
 *
 * Conceptes il·lustrats:
 *   - Connexió a MongoDB amb MongoClients.create()
 *   - Accés a base de dades i col·lecció
 *   - find() sense filtre per obtenir tots els documents
 *   - Iteració amb for-each sobre una col·lecció MongoDB
 *   - Accés a camps simples, documents imbricats i arrays
 *   - getDouble() per llegir notes amb decimals (0.0 - 10.0)
 *   - Try-with-resources per tancar el MongoClient
 */
public class LlistarEstudiants {

    // URI de connexió estàndard de MongoDB
    static final String URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        // MongoClient implementa Closeable, per tant funciona amb try-with-resources
        try (MongoClient client = MongoClients.create(URI)) {

            // Seleccionem la base de dades "escola"
            MongoDatabase db = client.getDatabase("escola");

            // Seleccionem la col·lecció "estudiants"
            MongoCollection<Document> col = db.getCollection("estudiants");

            System.out.println("=== LLISTA D'ESTUDIANTS ===");
            System.out.println("Total: " + col.countDocuments() + " estudiants");
            System.out.println();

            // find() sense arguments retorna tots els documents de la col·lecció
            for (Document doc : col.find()) {

                // Llegim camps simples: getString() i getInteger()
                String nom   = doc.getString("nom");
                int    edat  = doc.getInteger("edat");
                String cicle = doc.getString("cicle");

                System.out.println("Nom: " + nom);
                System.out.println("Edat: " + edat + " anys | Cicle: " + cicle);

                // El camp 'contacte' és un document imbricat (Document dins Document)
                Document contacte = doc.get("contacte", Document.class);
                if (contacte != null) {
                    System.out.println("Email: " + contacte.getString("email"));
                    System.out.println("Tel:   " + contacte.getString("telefon"));
                }

                // El camp 'moduls' és un array de documents
                List<Document> moduls = doc.getList("moduls", Document.class);
                if (moduls != null && !moduls.isEmpty()) {
                    System.out.println("Mòduls:");
                    for (Document modul : moduls) {
                        String nomModul = modul.getString("nom");
                        int    credits  = modul.getInteger("credits");
                        // La nota és un double (0.0 - 10.0): cal usar getDouble()
                        double nota     = modul.getDouble("nota");

                        // Marquem els mòduls suspesos (nota < 5) amb un indicador
                        String estat = nota < 5.0 ? " *** SUSPÈS ***" : "";
                        System.out.printf("  - %-42s %d crèd.  %.1f%s%n",
                                nomModul, credits, nota, estat);
                    }
                }

                System.out.println("-".repeat(55));
            }
        }
    }
}
