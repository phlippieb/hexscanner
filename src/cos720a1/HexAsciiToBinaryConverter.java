package cos720a1;

/**
 *  Converts Ascii strings, which contain hex-pairs representing bytes, to binary strings.
 * @author phlippie
 */
public class HexAsciiToBinaryConverter {

    public static String convert (String ascii) throws RuntimeException {

        //string must have even entries (two chars make one byte)
        if (ascii.length() % 2 != 0) {
            throw new RuntimeException ("String contains odd number of characters. Must be even.");
        }

        // first, check if string is valid:
        for (int i = 0; i < ascii.length(); i++) {
            if (!isValidHexChar (ascii.charAt(i))) {
                throw new RuntimeException ("String contains invalid data at position " + i);
            }
        }

        // next, process chars two by two into result.
        StringBuilder result = new StringBuilder ();
        int binaryValue = 0;
        for (int i = 0; i < ascii.length(); i += 2) {
            binaryValue = twoHexAsciiToInt(ascii.charAt(i), ascii.charAt(i + 1));
            result.append((char)binaryValue);
        }

        return result.toString();
    }

    protected static boolean isValidHexChar (char c) {
        return (47 < c && c < 58) || (64 < c && c < 71) || (96 < c && c < 103);
    }

    protected static int twoHexAsciiToInt (char high, char low) {
        return (10 * singleHexAsciiToInt(high) + singleHexAsciiToInt(low));
    }

    protected static int singleHexAsciiToInt (char hex) {
        if (47 < hex && hex < 58) {
            return (hex - 48);
        } else if (64 < hex && hex < 91) {
            return (hex - 65);
        } else if (96 < hex && hex < 123) {
            return (hex - 97);
        } else {
            return -1;
        }
    }
}
