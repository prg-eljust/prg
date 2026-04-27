import java.sql.*;

/**
 * BuscarPerParaulaClau.java
 *
 * Exemple de lectura amb filtre (READ) usant PreparedStatement.
 * Cerca publicacions que continguin una paraula clau determinada.
 *
 * Conceptes il·lustrats:
 *   - PreparedStatement per a consultes amb paràmetres
 *   - Evitar SQL Injection usant '?' en lloc de concatenació
 *   - Operador LIKE amb % per a cerques parcials
 *   - setString() per establir paràmetres
 */
public class BuscarPerParaulaClau {

    static final String URL  = "jdbc:mysql://localhost:3306/forum?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {

        // Paraula clau a buscar (podria venir de l'usuari per Scanner)
        String paraulaClau = "spring";

        // Usem '?' com a marcador de posició per al paràmetre.
        // IMPORTANT: mai concatenar strings d'entrada de l'usuari directament al SQL.
        // Exemple INCORRECTE (vulnerable): "WHERE paraules_clau LIKE '%" + paraulaClau + "%'"
        String sql = "SELECT id, nick_que_publica, text, paraules_clau "
                   + "FROM publicacio "
                   + "WHERE paraules_clau LIKE ? "
                   + "ORDER BY data_publicacio";

        // prepareStatement() precompila la consulta al servidor MySQL
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establim el valor del primer '?' (posició 1, comença per 1 no per 0)
            // Els % indiquen "qualsevol text" a la dreta i l'esquerra de la paraula
            pstmt.setString(1, "%" + paraulaClau + "%");

            // executeQuery() en un PreparedStatement també retorna un ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {

                System.out.println("Publicacions amb paraula clau: '" + paraulaClau + "'");
                System.out.println("=".repeat(50));

                int comptador = 0;
                while (rs.next()) {
                    comptador++;
                    System.out.printf("[%d] %s%n", rs.getInt("id"), rs.getString("nick_que_publica"));
                    System.out.println("    " + rs.getString("text"));
                    System.out.println("    Tags: " + rs.getString("paraules_clau"));
                    System.out.println();
                }

                if (comptador == 0) {
                    System.out.println("No s'han trobat publicacions amb aquesta paraula clau.");
                } else {
                    System.out.println("Total: " + comptador + " publicació/ns trobada/es.");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQL " + e.getErrorCode() + ": " + e.getMessage());
        }
    }
}
