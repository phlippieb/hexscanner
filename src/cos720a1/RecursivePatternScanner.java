package cos720a1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author phlippie
 */
public class RecursivePatternScanner {

    FileScanner scanner = new FileScanner();
    FileStepper stepper = new FileStepper();

    public RecursivePatternScanner() {

    }

    public ArrayList<File> scan(String root, String pattern) throws Exception {
        stepper.setRoot(root);
        scanner.setPattern(pattern);

        ArrayList<File> returnList = new ArrayList<File>();

        for (Iterator<File> i = stepper.getFileList().iterator();i.hasNext();) {
            File candidate = i.next();
            scanner.setFile(candidate);
            if (scanner.fileContainsPattern()) {
                System.out.println ("  FOUND PATTERN");
                returnList.add(candidate);
            }

        }
        return returnList;
    }
}
