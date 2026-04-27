import java.sql.*;

/**
 * LlistarPublicacions.java
 *
 * Exemple bàsic de lectura (READ) amb JDBC.
 * Obté totes les publicacions del fòrum i les mostra per pantalla.
 *
 * Conceptes il·lustrats:
 *   - Connexió a MySQL amb DriverManager
 *   - Creació d'un Statement
 *   - Execució d'un SELECT amb executeQuery()
 *   - Iteració per un ResultSet
 *   - Try-with-resources per tancar recursos automàticament
 */
public class LlistarPublicacions {

    // --- Dades de connexió ---
    // Modifiqueu aquests valors segons la vostra instal·lació
    static final String URL  = "jdbc:mysql://localhost:3306/forum?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void main(String[] args) {

        // Consulta SQL: obtenim totes les publicacions ordenades per data
        String sql = "SELECT id, nick_que_publica, text, data_publicacio, paraules_clau "
                   + "FROM publicacio "
                   + "ORDER BY data_publicacio";

        // El try-with-resources declara la connexió, la sentència i el ResultSet.
        // Java garanteix que es tanquen en ordre invers al de declaració quan
        // s'acaba el bloc, fins i tot si hi ha una excepció.
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {

            System.out.println("=== PUBLICACIONS DEL FÒRUM ===");
            System.out.println();

            // rs.next() avança el cursor al següent registre.
            // Retorna true si hi ha fila, false si hem acabat.
            while (rs.next()) {
                // Llegim cada columna pel seu nom
                int    id      = rs.getInt("id");
                String nick    = rs.getString("nick_que_publica");
                String text    = rs.getString("text");
                String data    = rs.getString("data_publicacio");
                String tags    = rs.getString("paraules_clau");

                // Mostrem la informació formatada
                System.out.printf("[%2d] %s  (%s)%n", id, nick, data);
                System.out.println("     " + text);
                System.out.println("     Tags: " + tags);
                System.out.println();
            }

        } catch (SQLException e) {
            // SQLException és l'excepció base de JDBC.
            // errorCode és el codi d'error específic de MySQL.
            System.err.println("Error SQL " + e.getErrorCode() + ": " + e.getMessage());
        }
    }
}
