package cos720a1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
/**
 *
 * @author phlippie
 */
public class FileScanner {

    File file;
    String pattern;
    FileInputStream fileInputStream;
    BufferedInputStream fileInput;

    public FileScanner () {

    }

    public FileScanner (File f) {
        this.file = f;
    }

    public FileScanner (File f, String p) {
        this.file = f;
        this.pattern = p;
    }

    public void setFile (File f) {
        this.file = f;
    }

    public void setPattern (String p) {
        this.pattern = p;
    }

    public File getFile () {
        return this.file;
    }

    public String getPattern() {
        return this.pattern;
    }

    public boolean fileContainsPattern () throws Exception {
        //try {
            System.out.println("  scanning "+this.file.getAbsolutePath());

            char [] sig = this.pattern.toCharArray();

            try {
                fileInputStream.close();
            } catch (Exception e) {}
            try {
                fileInput.close();
            } catch (Exception e) {}

            fileInputStream = new FileInputStream(this.file);
            fileInput = new BufferedInputStream (fileInputStream);

            int b = fileInput.read();
            boolean found = false;
            while (b >= 0) {

                // 1 scan until a character matches
                while (b != sig[0]) {b = fileInput.read();
                    b = fileInput.read();
                    if (b < 0) {
                        return false;
                    }
                }

                // mark and check rest
                fileInput.mark(999999);
                found = true;
                for (int i = 0; i < this.pattern.length(); i++) {
                    b = fileInput.read();
                    if (b != sig[i]) {
                        found = false;
                        break;
                    }
                    if (b < 0) {
                        return false;
                    }
                    b = fileInput.read();
                }
                if (found) {
                    return true;
                }
            }


        /*} catch (Exception e) {
            //System.out.println("Exception caught:" + e);
        }*/
        return false;
    }

    public boolean fileContainsPattern (String pattern) throws Exception {
        System.out.println("  scanning " + this.file.getAbsolutePath());
        try {
            char [] sig = pattern.toCharArray();

            try {
                fileInputStream.close();
            } catch (Exception e) {}
            try {
                fileInput.close();
            } catch (Exception e) {}
            
            fileInputStream = new FileInputStream(this.file);
            fileInput = new BufferedInputStream (fileInputStream);

            int b = fileInput.read();
            boolean found = false;
            while (b >= 0) {

                // 1 scan until a character matches
                while (b != sig[0]) {
                    b = fileInput.read();
                    if (b < 0) {
                        fileInput.close();
                        fileInputStream.close();
                        return false;
                    }
                }

                // mark and check rest
                fileInput.mark(999999);
                found = true;
                for (int i = 0; i < pattern.length(); i++) {
                    b = fileInput.read();
                    if (b != sig[i]) {
                        found = false;
                        break;
                    }
                    if (b < 0) {
                        fileInput.close();
                        fileInputStream.close();
                        return false;
                    }
                    b = fileInput.read();
                }
                if (found) {
                    fileInput.close();
                    fileInputStream.close();
                    return true;
                }
            }


        } catch (Exception e) {
            //System.out.println("Exception caught:" + e);
        }
        
        return false;
    }
}