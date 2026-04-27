import java.sql.*;

/**
 * PublicacioAmbComentaris.java
 *
 * Exemple de consulta amb relació 1:M.
 * Mostra una publicació i tots els seus comentaris associats.
 *
 * Conceptes il·lustrats:
 *   - Reutilitzar una mateixa connexió per a múltiples consultes
 *   - Consultes separades sobre taules relacionades
 *   - Formatació de la sortida per pantalla
 *   - Ús de rs.getInt() i rs.getString() alternats
 */
public class PublicacioAmbComentaris {

    static final String URL  = "jdbc:mysql://localhost:3306/forum?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {

        // Id de la publicació que volem consultar
        int idPublicacio = 1;

        // SQL per obtenir la publicació
        String sqlPub = "SELECT * FROM publicacio WHERE id = ?";

        // SQL per obtenir tots els comentaris d'aquesta publicació,
        // ordenats del més antic al més nou
        String sqlCom = "SELECT * FROM comentari "
                      + "WHERE publicacio_id = ? "
                      + "ORDER BY data_comentari ASC";

        // Obrim una sola connexió i la reutilitzem per a les dues consultes
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // --- Primera consulta: la publicació ---
            try (PreparedStatement pstmt = conn.prepareStatement(sqlPub)) {
                pstmt.setInt(1, idPublicacio);

                try (ResultSet rs = pstmt.executeQuery()) {
                    // if (rs.next()) comprova si hi ha almenys una fila
                    if (rs.next()) {
                        System.out.println("╔══════════════════════════════════════════════╗");
                        System.out.println("  PUBLICACIÓ #" + rs.getInt("id"));
                        System.out.println("╚══════════════════════════════════════════════╝");
                        System.out.println("Nick : " + rs.getString("nick_que_publica"));
                        System.out.println("Data : " + rs.getString("data_publicacio"));
                        System.out.println("Tags : " + rs.getString("paraules_clau"));
                        System.out.println("Text : " + rs.getString("text"));
                    } else {
                        System.out.println("No s'ha trobat la publicació amb id " + idPublicacio);
                        return; // Sortim si no existeix la publicació
                    }
                }
            }

            System.out.println();
            System.out.println("--- COMENTARIS ---");

            // --- Segona consulta: els comentaris ---
            try (PreparedStatement pstmt = conn.prepareStatement(sqlCom)) {
                pstmt.setInt(1, idPublicacio);

                try (ResultSet rs = pstmt.executeQuery()) {
                    int numComentari = 0;

                    while (rs.next()) {
                        numComentari++;
                        String nick      = rs.getString("nick_comenta");
                        String text      = rs.getString("text");
                        int    valoracio = rs.getInt("valoracio");
                        String data      = rs.getString("data_comentari");

                        // Construïm una cadena de "★" per representar la valoració
                        String estrelles = "★".repeat(valoracio) + "☆".repeat(5 - valoracio);

                        System.out.printf("%d. %s [%s] - %s%n", numComentari, estrelles, nick, data);
                        System.out.println("   " + text);
                        System.out.println();
                    }

                    if (numComentari == 0) {
                        System.out.println("  (Aquesta publicació no té comentaris)");
                    } else {
                        System.out.println("Total: " + numComentari + " comentari/s");
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQL " + e.getErrorCode() + ": " + e.getMessage());
        }
    }
}
