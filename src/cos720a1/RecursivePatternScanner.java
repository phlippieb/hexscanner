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

        System.out.println("[info]   building file list...");
        for (Iterator<File> i = stepper.getFileList().iterator();i.hasNext();) {
            File candidate = i.next();
            System.out.println("[info]   scanning file for matches: " + candidate.getName());
            scanner.setFile(candidate);
            if (scanner.fileContainsPattern()) {
                returnList.add(candidate);
            }

        }
        return returnList;
    }
}
