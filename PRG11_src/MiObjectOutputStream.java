import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * ObjectOutputStream personalitzat que no escriu la capçalera del stream.
 * S'utilitza per AFEGIR objectes a un fitxer ja existent sense duplicar
 * la capçalera (que causaria errors en la lectura posterior).
 *
 * Ús:
 *   - Crear el fitxer per primera vegada → ObjectOutputStream normal
 *   - Afegir objectes a un fitxer existent → MiObjectOutputStream
 */
public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // No escrivim capçalera, per evitar capçaleres duplicades al fitxer
    }
}
