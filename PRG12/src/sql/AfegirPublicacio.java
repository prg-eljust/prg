import java.sql.*;

/**
 * AfegirPublicacio.java
 *
 * Exemple d'inserció (CREATE) amb JDBC.
 * Afegeix una nova publicació al fòrum i recupera l'id generat.
 *
 * Conceptes il·lustrats:
 *   - INSERT amb PreparedStatement
 *   - executeUpdate() per a operacions que modifiquen dades
 *   - Statement.RETURN_GENERATED_KEYS per obtenir l'AUTO_INCREMENT generat
 *   - getGeneratedKeys() per llegir l'id nou
 */
public class AfegirPublicacio {

    static final String URL  = "jdbc:mysql://localhost:3306/forum?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {

        // La funció NOW() de MySQL insereix la data i hora actuals
        // Tenim 3 paràmetres marcats amb '?'
        String sql = "INSERT INTO publicacio (nick_que_publica, text, data_publicacio, paraules_clau) "
                   + "VALUES (?, ?, NOW(), ?)";

        // Statement.RETURN_GENERATED_KEYS indica a JDBC que volem
        // que ens retorni les claus generades (id AUTO_INCREMENT)
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Omplim els tres '?' en ordre (posicions 1, 2, 3)
            pstmt.setString(1, "nou_alumne");
            pstmt.setString(2, "Quina diferència hi ha entre ArrayList i LinkedList a Java?");
            pstmt.setString(3, "java,collections,llistes");

            // executeUpdate() executa INSERT/UPDATE/DELETE
            // Retorna el nombre de files afectades (hauria de ser 1)
            int filesInserides = pstmt.executeUpdate();
            System.out.println("Files inserides: " + filesInserides);

            // Recuperem l'id generat per MySQL (AUTO_INCREMENT)
            try (ResultSet claus = pstmt.getGeneratedKeys()) {
                if (claus.next()) {
                    // La primera columna (índex 1) conté el nou id
                    int idNou = claus.getInt(1);
                    System.out.println("Nova publicació creada amb id: " + idNou);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQL " + e.getErrorCode() + ": " + e.getMessage());
        }
    }
}
