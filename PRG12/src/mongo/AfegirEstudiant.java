import com.mongodb.client.*;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * AfegirEstudiant.java
 *
 * Exemple d'inserció (CREATE) amb el driver de MongoDB.
 * Insereix un estudiant nou amb dades de contacte i mòduls.
 *
 * Conceptes il·lustrats:
 *   - Camps en valencià: "nom", "edat", "cicle", "contacte", "moduls"
 *   - Notes com a double (0.0 - 10.0): append("nota", 7.8)
 *   - Documents imbricats (contacte) i arrays de documents (moduls)
 *   - insertOne() i insertMany()
 *   - Recuperar l'_id generat automàticament
 */
public class AfegirEstudiant {

    static final String URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create(URI)) {

            MongoCollection<Document> col = client.getDatabase("escola")
                                                  .getCollection("estudiants");

            // --- Inserció d'un document complet ---
            // Notar que els noms dels camps estan en valencià
            // i les notes són doubles entre 0.0 i 10.0
            Document nouEstudiant = new Document("nom", "Pau Martí")
                .append("edat", 19)
                .append("cicle", "DAM")
                // Document imbricat per a les dades de contacte
                .append("contacte", new Document("email", "pau.marti@institut.cat")
                                        .append("telefon", "666-111-222"))
                // Array de mòduls: cada mòdul té nom, credits i nota (double)
                .append("moduls", Arrays.asList(
                    new Document("nom", "Programació")
                        .append("credits", 5)
                        .append("nota", 7.8),       // double: 0.0 - 10.0
                    new Document("nom", "Bases de Dades")
                        .append("credits", 4)
                        .append("nota", 8.2),
                    new Document("nom", "Sistemes")
                        .append("credits", 4)
                        .append("nota", 4.5)        // suspès: nota < 5.0
                ));

            // insertOne() insereix el document a la col·lecció
            col.insertOne(nouEstudiant);

            System.out.println("Estudiant inserit!");
            System.out.println("_id generat: " + nouEstudiant.getObjectId("_id"));

            System.out.println();

            // --- Inserció de múltiples documents ---
            List<Document> mes = Arrays.asList(
                new Document("nom", "Clara Vidal")
                    .append("edat", 20)
                    .append("cicle", "DAW")
                    .append("moduls", Arrays.asList(
                        new Document("nom", "Disseny Web").append("credits", 4).append("nota", 8.5),
                        new Document("nom", "Programació Web").append("credits", 5).append("nota", 7.2)
                    )),
                new Document("nom", "Marc Soler")
                    .append("edat", 22)
                    .append("cicle", "ASIX")
                    .append("moduls", Arrays.asList(
                        new Document("nom", "Xarxes").append("credits", 4).append("nota", 6.8),
                        new Document("nom", "Sistemes Operatius").append("credits", 5).append("nota", 3.9)
                    ))
            );

            // insertMany() insereix tots els documents de la llista d'una vegada
            col.insertMany(mes);
            System.out.println("S'han inserit " + mes.size() + " estudiants addicionals.");
            System.out.println("Total estudiants: " + col.countDocuments());
        }
    }
}
