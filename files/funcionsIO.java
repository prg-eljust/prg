import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Llibreria de funcions d'ajuda per a la gestió d'entrada i sortida
 * @author Joan Gerard
 * @author Paco Galera
 */
public class funcionsIO {

    private final static BufferedReader entradaConsola = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

    /**
     * Imprimeix un missatge per consola
     * @param missatge Missatge a mostrar per consola
     */
    public static void printMC(String missatge) {
        System.out.println(missatge);
    }

    /**
     * Imprimeix un missatge en una finestra emergent
     * @param missatge Missatge a mostrar en la finestra
     */
    public static void printMG(String missatge) {
        JOptionPane.showMessageDialog(null, missatge);
    }

    /**
     * Recull un número sencer per consola
     * @param missatge Missatge que acompanya l'entrada del número
     * @return Número sencer recollit
     */
    public static int readIntC(String missatge) {
        return Integer.parseInt(readTextC(missatge));
    }

    /**
     * Recull un número sencer per finestra emergent
     * @param missatge Missatge que es mostra a la finestra emergent
     * @return Número sencer recollit
     */
    public static int readIntG(String missatge) {
        return Integer.parseInt(readTextG(missatge));
    }

    /**
     * Recull un número amb decimals per consola
     * @param missatge Missatge que acompanya l'entrada del número
     * @return Número amb decimals recollit
     */
    public static double readDoubleC(String missatge) {
        return Double.parseDouble(readTextC(missatge));
    }

    /**
     * Recull un número amb decimals per finestra emergent
     * @param missatge Missatge que es mostra a la finestra emergent
     * @return Número amb decimals recollit
     */
    public static double readDoubleG(String missatge) {
        return Double.parseDouble(readTextG(missatge));
    }

    /**
     * Recull un text per consola
     * @param missatge Missatge que acompanya l'entrada del text
     * @return Text recollit
     */
    public static String readTextC(String missatge) {
        try {
            System.out.print(missatge);
            return entradaConsola.readLine();
        } // ()
        catch (IOException ex) {
            return "";
        }
    }

    /**
     * Recull un text per finestra emergent
     * @param missatge Missatge que es mostra a la finestra emergent
     * @return Text recollit
     */
    public static String readTextG(String missatge) {
        String llegit = JOptionPane.showInputDialog(missatge);
        if (llegit == null) {
            return "";
        }
        return llegit;
    }

} // class
