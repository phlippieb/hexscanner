package cos720a1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author phlippie
 */
public class RecursivePatternScanner {

    //FileScanner scanner = new FileScanner();
    FileStepper stepper = new FileStepper();

    public RecursivePatternScanner() {

    }

    public ArrayList<File> scan(String root, String pattern) throws Exception {
        stepper.setRoot(root);
        //scanner.setPattern(pattern);

        ArrayList<File> returnList = new ArrayList<File>();
        ArrayList<File> scanList;

        System.out.println("[info]   building file list...");
        scanList = stepper.getFileList();
        System.out.println("[info]   " + scanList.size() + " files found");
        System.out.println("[info]   starting scan...");

        try {
            byte [] binaryPattern = ByteUtils.stringToByteArray(pattern);
            System.out.print ("[info]   Pattern: ");
            for (int a = 0; a < binaryPattern.length; a++) {System.out.print("["+(int)binaryPattern[a]+"] ");}
            System.out.println();


            for (Iterator<File> i = scanList.iterator();i.hasNext();) {
                File candidate = i.next();
                System.out.println("[info]   scanning: " + candidate.getAbsolutePath());
                //scanner.setFile(candidate);
                //try {
                    //if (scanner.fileContainsPattern()) {
                    //    returnList.add(candidate);
                    //}
                //} catch (FileNotFoundException e) {
                //    System.out.println ("[warning]   " +e);
                //}
                try {
                    byte [] binaryFilecontents = ByteUtils.fileToByteArray(candidate);

                    if (ByteUtils.bytesContainBytes(binaryFilecontents, binaryPattern)) {
                        returnList.add(candidate);
                    }
                } catch (FileNotFoundException fnfe) {
                    System.out.println ("[warning]   " +fnfe);
                    System.out.println (fnfe.getStackTrace());
                } catch (Exception e) {
                    System.out.println ("[error]   "+e);
                    System.out.println (e.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("[error]   Error with pattern: " + e);
        }


        
        return returnList;
    }
}
