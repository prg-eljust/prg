/**
 * Classe que conté funcions auxiliars per a l'entrada i eixida de dades.
 *
 * @author Abdó Garcia
 * @author Joan Gerard
 * @author Paco Galera
 *
 * @version 4.0
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class funIO {

    // ================== FUNCIONS D'EIXIDA ==================

    /**
     * Mostra el text passat com a paràmetre en un format especial. Li posa un
     * Quadradet al voltant, segons la grandària del text.
     *
     * @param text Text del títol
     * @param espais Espais a l'esquerra del títol
     */
    public static void titol(String text, String espais) {
        if (text == null) {
            text = "";
        }
        System.out.println();
        System.out.print(espais);
        for (int i = 0; i < text.length() + 4; i++) {
            System.out.print("*");
        }
        System.out.println(" " + espais + "* " + text + " *");
                System.out.print(espais);
        for (int i = 0; i < text.length() + 4; i++) {
            System.out.print("*");
        }
        System.out.println(" ");
    }

    /**
     * Mostra el text passat com a paràmetre en un format especial. Li posa un
     * Quadradet al voltant, segons la grandària del text.
     *
     * @param text Text del títol
     */
    public static void titol(String text) {
        titol(text, "");
    }

    /**
     * Mostra el text passat com a paràmetre en un format especial
     * i un poc centrat en la pantalla.
     * Li posa un Quadradet al voltant, segons la grandària del text.
     *
     * @param text Text del títol
     */
    public static void titolCentrat2(String text) {
        titol(text, "\t");
    }

    /**
     * Escriu un títol amb una línia baix d'igual grandària.
     * @param text El text del títol a mostrar
     */
    public static void titolet(String text) {
        System.out.println(text);
        linia(text.length());
    }


    /**
     * Dibuixa una línia de ratlletes de la grandària especificada.
     * @param n Grandària de la línia (en quantitat de caràcters)
     */
    public static void linia(int n) {
        System.out.println(getLinia(n));
    }

    /**
     * Retorna una línia de ratlletes de la grandària especificada.
     * És invocada (entre altres) pel toString de la classe Sèrie.
     * @param n Grandària de la línia (en quantitat de caràcters)
     * @return la línia de ratlletes.
     */
    public static String getLinia(int n) {
        return "-".repeat(Math.max(0, n));
    }

    /**
     * Retorna un text que càpiga en un tros, alineat a esquerra.
     * Pensat per a ser usat en el toString.
     * @param text Text del qual volem obtindre un tros
     * @param espais Quantitat d'espais que ha d'ocupar el text
     * @return El text amb eixos espais (sempre retorna un blanc al final)
     */
    public static String tros(String text, int espais) {
        int l = text.length();

        if (l >= espais) {
            text = text.substring(0, espais - 1) + ' '; // un espai al final
        } else {
            int falten = espais - l;
            text = text + " ".repeat(falten);
        }
        return text;
    }

    /**
     * Retorna un número que càpiga en un tros, alineat a dreta.
     * Pensat per a ser usat en el toString
     *
     * @param numero Número del qual volem posar en un tros
     * @param espais Quantitat d'espais que ha d'ocupar el número
     * @return El text amb eixos espais (sempre retorna un blanc al final)
     */
    public static String tros(int numero, int espais) {
        StringBuilder text = new StringBuilder(numero + "");

        while (text.length() < espais - 1) {
            text.insert(0, ' ');
        }
        return text.toString() + ' '; // Un espai al final
    }

    /**
     * Retorna un caràcter repetit (cridat per funció lligOpcio)
     *
     * @param c       Caràcter a repetir
     * @param vegades Quantitat de vegades a repetir el caràcter
     * @return cadena amb el caràcter repetit
     */
    private static String repetix(char c, int vegades) {
        String cadena = "";
        for (int i = 0; i < vegades; i++) {
            cadena += c;
        }
        return cadena;
    }


    /**
     * Retorna el toString d'un ArrayList de Strings però en altre format.
     *
     * @param strings llista de cadenes
     * @return cadena de cadenes separades per coma
     */
    public static String llistaStrings(ArrayList<String> strings) {
        StringBuilder cadena = new StringBuilder();
        if (strings == null || strings.isEmpty()){
            return "";
        }
        for (int i = 0; i < strings.size() - 1; i++) {
            cadena.append(strings.get(i)).append(", ");
        }
        cadena.append(strings.getLast());
        return cadena.toString();
    }

    /**
     * Mostra un text en una quantitat d'espais.
     * @param text Text que volem mostrar
     * @param espais Quantitat d'espais destinada a mostrar el text.
     */
    public static void print(String text, int espais) {
        System.out.print(tros(text, espais));
    }

    /**
     * Imprimeix un missatge en una finestra emergent
     * @param missatge Missatge a mostrar en la finestra
     */
    public static void printMG(String missatge) {
        JOptionPane.showMessageDialog(null, missatge);
    }

    // ================== FUNCIONS D'ENTRADA ==================

    static Scanner sc = new Scanner(System.in);

    /**
     * Aconsegueix un caràcter de teclat.
     *
     * @param missatge Text per a fer la pregunta per teclat.
     * @return El caràcter introduït
     */
    public static char lligChar(String missatge) {
        String resposta;

        do {
            demana(missatge);
            resposta = sc.nextLine();
        } while (resposta.isEmpty());

        return resposta.charAt(0);
    }

    /**
     * Aconsegueix un caràcter de teclat entre uns possibles valors. Nota:
     * retorna el caràcter en majúscula.
     *
     * @param missatge Text per a fer la pregunta per teclat.
     * @param possibles Cadena amb els possibles caràcters de resposta.
     * @return El caràcter introduït per teclat entre els possibles.
     */
    public static char lligChar(String missatge, String possibles) {
        char c;
        String resposta;
        possibles = possibles.toUpperCase();

        do {
            do {
                demana(missatge + " (" + separar(possibles, ", ") + ")");
                resposta = sc.nextLine();
            } while (resposta.isEmpty());
            c = resposta.toUpperCase().charAt(0);
        } while (!possibles.contains(c + ""));

        return c;
    }

    /**
     * Separa les lletres d'una cadena (posant comes, etc.). És usada, entre
     * altres, per lligChar, per a mostrar els possibles valors.
     *
     * @param cadena Cadena que conté les lletres a separar.
     * @param separacio Els caràcters que volem de separació
     * @return Cadena amb els caràcters separats.
     */
    public static String separar(String cadena, String separacio) {
        int l = cadena.length();
        if (l == 0) {
            return "";
        }
        StringBuilder cadenaSep = new StringBuilder();
        for (int i = 0; i < l - 1; i++) {
            cadenaSep.append(cadena.charAt(i)).append(separacio);
        }
        cadenaSep.append(cadena.charAt(l - 1));
        return cadenaSep.toString();
    }

    /**
     * Demana una cadena de teclat.
     *
     * @return la cadena llegida.
     */
    public static String lligString() {
        return sc.nextLine();
    }

    /**
     * Demana una cadena de teclat mostrant primer què es demana.
     *
     * @param missatge el text a mostrar.
     * @return la cadena llegida.
     */
    public static String lligString(String missatge) {
        demana(missatge);
        return sc.nextLine();
    }

    /**
     * Demana una cadena de teclat que tinga una grandària concreta.
     *
     * @param qLletres Grandària que cal que tinga la cadena a llegir.
     * @return la cadena llegida.
     */
    public static String lligString(int qLletres) {
        String cadena;
        cadena = sc.nextLine();
        while (cadena.length() != qLletres) {
            cadena = lligString("Ha de tindre " + qLletres + " caràcters");
        }
        return cadena;
    }

    /**
     * Demana una cadena de teclat que tinga una grandària concreta, mostrant
     * primer què es demana.
     *
     * @param missatge el text a mostrar.
     * @param qLletres Grandària que cal que tinga la cadena a llegir.
     * @return la cadena llegida.
     */
    public static String lligString(String missatge, int qLletres) {
        String cadena;
        cadena = lligString(missatge);
        while (cadena.length() != qLletres) {
            cadena = lligString(missatge + " (" + qLletres + " caràcters)");
        }
        return cadena;
    }

    /**
     * Demana per teclat una llista de cadenes Sol ser invocat per a demanar
     * noms d'actors, directors, artistes...
     *
     * @param missatge Missatge a mostrar per a demanar cada cadena.
     * @return llista de cadenes.
     */
    public static ArrayList<String> lligStrings(String missatge) {
        ArrayList<String> strings = new ArrayList<>();
        String element;
        element = funIO.lligString(missatge);
        while (!element.isEmpty()) {
            strings.add(element);
            element = funIO.lligString(missatge);
        }
        return strings;
    }

    /**
     * Mostra un missatge per a demanar dades per teclat. Si no acaba en ':', '=' o
     * en '?', posa ':'al final del missatge.
     *
     * @param missatge Missatge que volem preparar.
     */
    public static void demana(String missatge) {
        if (!missatge.isEmpty()) {
            if (!missatge.endsWith(":") && !missatge.endsWith(": ")
                    && !missatge.endsWith("=") && !missatge.endsWith("= ")
                    && !missatge.endsWith("?") && !missatge.endsWith("? ")) {

                missatge = missatge + ": ";
            }
            System.out.print(missatge);
        }
    }

    /**
     * Demana un booleà per teclat, mostrant primer què es demana. Es fa la
     * pregunta i no para fins a respondre s/S/n/N
     *
     * @param missatge el text a mostrar.
     * @return true o false
     */
    public static boolean lligBoolean(String missatge) {
        String resposta;
        char c;
        do {
            do {
                demana(missatge + " (s/n)? ");
                resposta = sc.nextLine();
            } while (resposta.isEmpty());
            c = resposta.toLowerCase().charAt(0);
        } while (c != 's' && c != 'n');
        return (c == 's');
    }

    /**
     * Demana un enter per teclat. S'assegura que no s'han introduït lletres,
     * etc.
     *
     * @return l'enter llegit.
     */
    public static int lligInt() {
        int numero = -1;
        boolean error;

        do {
            try {
                numero = sc.nextInt();
                error = false;
            } catch (Exception e) {
                System.out.print("Ha de ser un número enter:");
                sc.nextLine(); // buida buffer de lletres, etc
                error = true;
            }
        } while (error);

        sc.nextLine(); // buida "intro" per a poder llegir cadenes després
        return numero;
    }

    /**
     * Mostra un missatge per pantalla per a demanar un enter per teclat
     *
     * @param missatge Text a mostrar.
     * @return el número introduït
     */
    public static int lligInt(String missatge) {
        int numero;
        demana(missatge);
        numero = funIO.lligInt();

        return numero;
    }

    /**
     * Mostra un missatge per pantalla per a demanar un enter per teclat que
     * estiga en un rang de possibles valors.
     *
     * @param missatge Text a mostrar.
     * @param min Valor mínim (inclòs).
     * @param max Valor màxim (inclòs).
     * @return el número introduït
     */
    public static int lligInt(String missatge, int min, int max) {
        int numero;
        do {
            demana(missatge + " (" + min + " a " + max + ")");
            numero = funIO.lligInt();

        } while (numero < min || numero > max);

        return numero;
    }

    /**
     * Mostra un missatge per pantalla per a demanar un enter per teclat que
     * tinga un límit inferior.
     *
     * @param missatge Text a mostrar.
     * @param min Valor mínim (inclòs).
     * @return el número introduït
     */
    public static int lligInt(String missatge, int min) {
        int numero;
        demana(missatge);
        numero = funIO.lligInt();
        while (numero < min) {
            demana(missatge + " (mínim " + min + ")");
            numero = funIO.lligInt();
        }
        return numero;
    }

    /**
     * Demana un float per teclat. S'assegura que no s'han introduït lletres,
     * etc
     *
     * @return el float llegit.
     */
    public static float lligFloat() {
        float numero = -1;
        boolean error;

        do {
            try {
                numero = sc.nextFloat();
                error = false;
            } catch (Exception e) {
                System.out.print("Ha de ser un número:");
                sc.nextLine(); // buida buffer de lletres, etc
                error = true;
            }
        } while (error);

        sc.nextLine(); // buida "intro" per a poder llegir cadenes després
        return numero;
    }

    /**
     * Mostra un missatge per pantalla per a demanar un float per teclat que
     * tinga un límit inferior.
     *
     * @param missatge Text a mostrar.
     * @param min Valor mínim (inclòs).
     * @return el número introduït
     */
    public static float lligFloat(String missatge, float min) {
        float numero;
        demana(missatge);
        numero = funIO.lligFloat();
        while (numero < min) {
            demana(missatge + " (mínim " + min + ")");
            numero = funIO.lligFloat();
        }
        return numero;
    }

    /**
     * Mostra menú d'opcions i torna el número de l'opció triada per Teclat.
     *
     * @param titol   Títol del menú
     * @param opcions Textos de les distintes opcions (en format d'arguments
     *                variables). No es passarà opció d'eixir, ja que s'afegirà l'opció 0.
     * @return El número de l'opció triada (1, 2, 3... o 0 per a eixir)
     */
    public static int lligOpcio(String titol, String... opcions) {
        // Càlcul amplària del menú (per a les ratlletes)
        int lonMax = titol.length();
        for (String opcio : opcions) {
            if (opcio.length() > lonMax) {
                lonMax = opcio.length();
            }
        }
        lonMax += 5;

        // Títol del menú
        String trosset = repetix('=', (lonMax - (titol.length() + 2)) / 2);
        System.out.println(trosset + " " + titol + " " + trosset);

        // Mostrem les opcions
        for (int i = 0; i < opcions.length; i++) {
            System.out.println(" " + (i + 1) + ". " + opcions[i]);
        }
        System.out.println(repetix('-', lonMax));

        System.out.println(" 0. Eixir");
        System.out.println(repetix('=', lonMax));

        // Demanem opció correcta per Teclat i la retornem
        int opcio = funIO.lligInt("Opció", 0, opcions.length);
        System.out.println();
        return opcio;
    }

    /**
     * Mostra missatge de polsar <intro> per a seguir i neteja pantalla.
     */
    public static void intro() {
        titol("Polsa <intro> per a seguir");
        sc.nextLine();
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    /**
     * Recull un text per finestra emergent
     * @param missatge Missatge que es mostra a la finestra emergent
     * @return Text recollit
     */
    public static String lligTextG(String missatge) {
        String llegit = JOptionPane.showInputDialog(missatge);
        if (llegit == null) {
            return "";
        }
        return llegit;
    }

    /**
     * Recull un número amb decimals per finestra emergent
     * @param missatge Missatge que es mostra a la finestra emergent
     * @return Número amb decimals recollit
     */
    public static double lligDoubleG(String missatge) {
        return Double.parseDouble(lligTextG(missatge));
    }

    /**
     * Recull un nombre sencer per finestra emergent
     * @param missatge Missatge que es mostra a la finestra emergent
     * @return Número sencer recollit
     */
    public static int lligIntG(String missatge) {
        return Integer.parseInt(lligTextG(missatge));
    }

}