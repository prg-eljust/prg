import java.sql.*;

/**
 * EliminarComentari.java
 *
 * Exemple d'eliminació (DELETE) amb JDBC.
 * Elimina un comentari identificat pel seu id.
 *
 * Conceptes il·lustrats:
 *   - DELETE amb PreparedStatement
 *   - Verificar que l'eliminació ha afectat algun registre
 *   - Bones pràctiques: confirmar l'operació abans d'executar-la (en una app real)
 */
public class EliminarComentari {

    static final String URL  = "jdbc:mysql://localhost:3306/forum?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {

        // Id del comentari que volem eliminar
        int idComentari = 21;

        String sql = "DELETE FROM comentari WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establim quin comentari volem eliminar
            pstmt.setInt(1, idComentari);

            // executeUpdate() també s'usa per a DELETE
            int filesEliminades = pstmt.executeUpdate();

            if (filesEliminades > 0) {
                System.out.println("Comentari " + idComentari + " eliminat correctament.");
            } else {
                // Si cap fila s'ha eliminat, l'id no existia
                System.out.println("No s'ha trobat cap comentari amb id " + idComentari);
            }

        } catch (SQLException e) {
            // Codi 1451: error de clau forana (foreign key constraint)
            // Passa si intentem esborrar una publicació que té comentaris associats
            if (e.getErrorCode() == 1451) {
                System.err.println("No es pot eliminar: hi ha registres relacionats.");
            } else {
                System.err.println("Error SQL " + e.getErrorCode() + ": " + e.getMessage());
            }
        }
    }
}
