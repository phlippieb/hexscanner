package cos720a1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Builds a list of all files in a dir tree starting at a given root.
 * @author phlippie
 */
public class FileStepper {

    protected String root;

    public FileStepper () {
    }

    public FileStepper (String root) {
        this.root = root;
    }

    public void setRoot (String root) {
        this.root = root;
    }

    public String getRoot () {
        return this.root;
    }

    public ArrayList<File> getFileList () throws RuntimeException {

        //try {
            File r = new File (root);
            if (! r.isDirectory()) {
                throw new RuntimeException ("Given root is not a directory");
            }

            return recursiveGetFileList(r);
            

        /*} catch (Exception e) {
            System.out.println("Exception in " + this.getClass().toString() + ".getFileList: ");
            System.out.println (e);
            return new ArrayList<File>();
        }*/

        
    }

    protected ArrayList<File> arrayToArrayList (File [] a) {
        ArrayList<File> al = new ArrayList<File>();
        for (int i = 0; i < a.length; i++) {
            al.add(a[i]);
        }
        return al;
    }

    protected synchronized ArrayList<File> recursiveGetFileList (File f) throws RuntimeException {

        if (f.isDirectory()) {

            ArrayList<File> list = arrayToArrayList (f.listFiles());
            ArrayList<File> returnList = new ArrayList<File>();

            File subFile;
            for (Iterator<File> i = list.iterator(); i.hasNext();) {

                subFile = i.next();
                /*if (subFile.getAbsolutePath().length() <= f.getAbsolutePath().length()) {
                    continue;
                }*/
                if (subFile.isFile()) {
                    returnList.add(subFile);
                } else {
                    try {
                        returnList.addAll(recursiveGetFileList(subFile));
                    } catch (NullPointerException e) {
                        // ignore...
                    } catch (Exception e) {
                        //System.out.println ("Exception in FileStepper.recursiveGetFileList:");
                        //System.out.println (e);
                        throw new RuntimeException (e);
                    }
                }
            }
            return returnList;
        } else {
            return new ArrayList<File>();
            //throw new RuntimeException (f.getAbsolutePath() + " is not a directory");
        }
    }
}


