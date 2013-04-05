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

        String root, pattern = "";
        if (args.length < 3 ||
                (args[0].charAt(0) != 's' &&
                 args[0].charAt(0) != 'c') ||
                (args[0].charAt(0) != 's' &&
                 args[0].charAt(0) != 'c'))
        {
            System.out.println ("Usage:\nVirusScanner (s)can <root> <pattern file path>");
            System.out.println ("  Where <root> is the root of the directory tree to search");
            System.out.println ("  And <pattern file path> is the path to a file that contains the pattern to match");
            System.out.println ("Or:\nVirusScanner (c)onvert <inputfile> <outputfile>");
            System.out.println ("  Where <inputfile> is file containing an ascii hex representation of a string to convert to binary");
            System.out.println ("  And <outputfile> is the name of a target file to store the converted string in");
            return;
        }

        if (args[0].charAt(0) == 's') {
                root = args[1];
                File patternFile;
                try {
                    patternFile = new File (args[2]);
                } catch (Exception e) {
                    System.out.println ("Could not open patternfile " + args[2]);
                    System.out.println(e);
                    return;
                } try {
                    pattern = FileContentsToStringConverter.convert(patternFile);
                } catch (Exception e) {
                    System.out.println ("Could not read pattern file " + args[2]);
                    System.out.println (e);
                    return;
                }

                RecursivePatternScanner s = new RecursivePatternScanner();
                try {
                    ArrayList<File> matches = s.scan(root, pattern);
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
        
        else if (args[0].charAt(0) == 'c') {

            try {
                File input = new File (args[1]);
                File output = new File (args[2]);
                if (! output.createNewFile()) {
                    System.out.println ("warning: output file exists. attempt overwriting.");
                    output.delete();
                    output.createNewFile();
                }

                String ascii = FileContentsToStringConverter.convert(input);
                ascii = ascii.substring(0, ascii.length()-1);
                String binary = HexAsciiToBinaryConverter.convert(ascii);
                System.out.println("Conversion complete. Result:");
                System.out.println(binary.getBytes());
                DataOutputStream outputstream = new DataOutputStream (new FileOutputStream (output));
                outputstream.writeChars(binary);


            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
