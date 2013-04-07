package cos720a1;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author phlippie
 */

/**
 * @TODO
 * recursively scan all files down tree
 * choose and test signature
 */
public class VirusScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length < 2)
        {
            System.out.println ("Usage:\nVirusScanner <root> <pattern>");
            System.out.println ("  Where <root> is the root of the directory tree to search");
            System.out.println ("  And <pattern> is the pattern to match in hex-ascii");
            return;
        }

        String root = args[0];
        String asciiPattern = args[1];
        //String pattern = "";
        //try {
        //    pattern = HexAsciiToBinaryConverter.convert(asciiPattern);
        //} catch (Exception e) {
        //    System.out.println ("Invalid pattern:" + args[1]);
        //    System.out.println (e);
        //    return;
        //}

        RecursivePatternScanner s = new RecursivePatternScanner();
        try {
            ArrayList<File> matches = s.scan(root, asciiPattern);
            System.out.println ("[info]   scan complete.");
            System.out.println();
            System.out.println ("Matching files:");
            for (Iterator<File> i = matches.iterator(); i.hasNext();) {
                System.out.println(i.next().getAbsolutePath());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
}
