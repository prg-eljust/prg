import java.sql.*;

/**
 * ActualitzarParaulesClau.java
 *
 * Exemple de modificació (UPDATE) amb JDBC.
 * Actualitza les paraules clau d'una publicació identificada per id.
 *
 * Conceptes il·lustrats:
 *   - UPDATE amb PreparedStatement
 *   - executeUpdate() retorna les files afectades
 *   - Comprovar si l'update ha afectat algun registre (id inexistent)
 */
public class ActualitzarParaulesClau {

    static final String URL  = "jdbc:mysql://localhost:3306/forum?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {

        // Valors a modificar
        int    idPublicacio    = 1;
        String novaParaulaClau = "spring,java,backend,tutorial,aprenentatge";

        // SET és el nou valor, WHERE identifica quin registre modificar
        String sql = "UPDATE publicacio SET paraules_clau = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Paràmetre 1: el nou valor per a paraules_clau
            pstmt.setString(1, novaParaulaClau);
            // Paràmetre 2: l'id del registre a modificar
            pstmt.setInt(2, idPublicacio);

            // executeUpdate() retorna les files que s'han modificat
            int filesAfectades = pstmt.executeUpdate();

            if (filesAfectades > 0) {
                // Si és > 0, vol dir que s'ha trobat i modificat el registre
                System.out.println("Publicació " + idPublicacio + " actualitzada correctament.");
                System.out.println("Noves paraules clau: " + novaParaulaClau);
            } else {
                // Si és 0, no hi ha cap publicació amb aquest id
                System.out.println("No s'ha trobat cap publicació amb id " + idPublicacio);
            }

        } catch (SQLException e) {
            System.err.println("Error SQL " + e.getErrorCode() + ": " + e.getMessage());
        }
    }
}
