/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cos720a1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author phlippie
 */
public class ByteUtils {


    public static byte [] stringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16)) *16
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static byte [] fileToByteArray(File file) throws FileNotFoundException, IOException {
        RandomAccessFile f = new RandomAccessFile(file.getAbsolutePath(), "r");
        byte[] b = new byte[(int)f.length()];
        f.read(b);
        f.close();
        return b;
    }

    public static boolean bytesContainBytes (byte [] haystack, byte [] needle) {
        return ByteUtils.indexOf(haystack, needle) != -1;
    }

    private static int indexOf(byte[] data, byte[] pattern) {
        int[] failure = computeFailure(pattern);

        int j = 0;
        if (data.length == 0) return -1;

        for (int i = 0; i < data.length; i++) {
            while (j > 0 && pattern[j] != data[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == data[i]) { j++; }
            if (j == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    /**
     * Computes the failure function using a boot-strapping process,
     * where the pattern is matched against itself.
     */
    private static int[] computeFailure(byte[] pattern) {
        int[] failure = new int[pattern.length];

        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[j] != pattern[i]) {
                j = failure[j - 1];
            }
            if (pattern[j] == pattern[i]) {
                j++;
            }
            failure[i] = j;
        }

        return failure;
    }


}
