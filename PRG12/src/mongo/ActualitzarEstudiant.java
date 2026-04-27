import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * ActualitzarEstudiant.java
 *
 * Exemple de modificació (UPDATE) amb el driver de MongoDB.
 * Mostra diverses formes d'actualitzar documents.
 *
 * Conceptes il·lustrats:
 *   - Camps en valencià: "nom", "edat", "cicle", "contacte.email"
 *   - Updates.set() per canviar camps simples
 *   - Updates.set() amb double per actualitzar una nota (0.0 - 10.0)
 *   - Notació de punt per accedir a sub-documents ("contacte.email")
 *   - Notació d'índex per accedir a elements d'array ("moduls.0.nota")
 *   - Updates.combine() per aplicar múltiples canvis alhora
 *   - Updates.inc() per incrementar valors numèrics
 */
public class ActualitzarEstudiant {

    static final String URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create(URI)) {

            MongoCollection<Document> col = client.getDatabase("escola")
                                                  .getCollection("estudiants");

            // --- Exemple 1: Actualitzar un camp simple ---
            // Filters.eq("nom", ...) → el camp és "nom", no "nombre"
            Bson filtre1 = Filters.eq("nom", "Ana Pérez");
            Bson update1 = Updates.set("cicle", "Enginyeria de Sistemes i Xarxes");

            UpdateResult res1 = col.updateOne(filtre1, update1);
            System.out.println("Exemple 1 - Actualitzar cicle:");
            System.out.println("  Trobats: " + res1.getMatchedCount());
            System.out.println("  Modificats: " + res1.getModifiedCount());

            System.out.println();

            // --- Exemple 2: Incrementar l'edat ---
            Bson filtre2 = Filters.eq("nom", "Luis Martínez");
            Bson update2 = Updates.inc("edat", 1);   // camp "edat", no "edad"

            UpdateResult res2 = col.updateOne(filtre2, update2);
            System.out.println("Exemple 2 - Incrementar edat:");
            System.out.println("  Modificats: " + res2.getModifiedCount());

            System.out.println();

            // --- Exemple 3: Actualitzar camp d'un document imbricat ---
            // Notació de punt: "contacte.email" (camp "contacte", no "contacto")
            Bson filtre3 = Filters.eq("nom", "María López");
            Bson update3 = Updates.set("contacte.email", "maria.lopez.nou@uni.edu");

            UpdateResult res3 = col.updateOne(filtre3, update3);
            System.out.println("Exemple 3 - Actualitzar email (document imbricat):");
            System.out.println("  Modificats: " + res3.getModifiedCount());

            System.out.println();

            // --- Exemple 4: Actualitzar la nota d'un mòdul (double) ---
            // "moduls.0.nota" accedeix a l'índex 0 de l'array "moduls"
            // El valor és un double (0.0 - 10.0)
            Bson filtre4 = Filters.eq("nom", "Carlos Rodríguez");
            Bson update4 = Updates.set("moduls.2.nota", 6.5);  // aprova el mòdul suspès

            UpdateResult res4 = col.updateOne(filtre4, update4);
            System.out.println("Exemple 4 - Actualitzar nota d'un mòdul (double):");
            System.out.println("  Modificats: " + res4.getModifiedCount());

            System.out.println();

            // --- Exemple 5: Múltiples canvis en un sol update ---
            Bson filtre5 = Filters.eq("nom", "Ana Pérez");
            Bson update5 = Updates.combine(
                Updates.set("edat", 23),
                Updates.set("contacte.telefon", "555-9999"),  // "telefon", no "telefono"
                Updates.set("moduls.0.nota", 9.5)             // nota com a double
            );

            UpdateResult res5 = col.updateOne(filtre5, update5);
            System.out.println("Exemple 5 - Múltiples canvis:");
            System.out.println("  Modificats: " + res5.getModifiedCount());

            System.out.println();

            // --- Exemple 6: Actualitzar tots els estudiants d'un cicle ---
            Bson filtre6 = Filters.eq("cicle", "Medicina");
            Bson update6 = Updates.set("facultat", "Ciències de la Salut");

            UpdateResult res6 = col.updateMany(filtre6, update6);
            System.out.println("Exemple 6 - Afegir camp a estudiants de Medicina:");
            System.out.println("  Modificats: " + res6.getModifiedCount());
        }
    }
}
