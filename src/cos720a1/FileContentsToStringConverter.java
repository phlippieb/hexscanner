package cos720a1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Converts the conents of a file (excludinf eof) to a String.
 * For feeding patterns to Scanners from file
 * @author phlippie
 */
public class FileContentsToStringConverter {

    public static String convert (File f) throws FileNotFoundException, IOException {
        BufferedInputStream input = new BufferedInputStream(new FileInputStream (f));
        int readChar = input.read();
        StringBuilder builder = new StringBuilder ();
        while (readChar >= 0) {
            builder.append((char)readChar);
            readChar = input.read();
        }
        return builder.toString();
    }
}
