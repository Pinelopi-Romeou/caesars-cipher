import java.util.ArrayList;
/**
 * This app encrypts and decrypts a string using a key
 * value to shift the characters.
 */
public class Caesars {
    public static void main(String[] args) {
        final int KEY = 300;
        String s = "Hello Coding Factory#";
        String encrypted = encrypt(s, KEY).toString();
        System.out.println(encrypted);

        String decrypted = decrypt(encrypt(s, KEY), KEY).toString();
        System.out.println(decrypted);
    }

    /**
     * Encrypts a string according to a given key value
     * @param s     the string
     * @param key   the given key value
     * @return      an ArrayList of integers that represent the encrypted string
     */
    public static ArrayList<Integer> encrypt(String s, int key) {
        ArrayList<Integer> encrypted = new ArrayList<>();
        char ch;
        int i;

        int prev = cipher(s.charAt(0), -1, key);
        encrypted.add(prev);

        i = 1;
        while ((ch = s.charAt(i)) != '#') {
            encrypted.add(cipher(ch, prev, key));
            prev = cipher(ch, prev, key);
            i++;
        }
        encrypted.add(-1);
        return encrypted;
    }

    /**
     * Decrypts an ArrayList of integers that represent
     * the encrypted string, according to a given key value
     * @param encrypted    the encrypted ArrayList of integers
     * @param key          the given key value
     * @return             the decrypted ArrayList of chars
     */
    public static ArrayList<Character> decrypt(ArrayList<Integer> encrypted, int key) {
        ArrayList<Character> decrypted = new ArrayList<>();
        int token;
        int i;

        int prevToken = decipher(encrypted.get(0), -1, key);
        decrypted.add((char) prevToken);

        i = 1;
        while ((token = encrypted.get(i)) != -1) {
            decrypted.add(decipher(token, prevToken, key));
            prevToken = token;
            i++;
        }
        return decrypted;
    }

    /**
     * Encrypts one char according to the given key.
     * If prev is the first character, the char is returned
     * without encryption.
     * @param ch      the char to be encrypted
     * @param prev    the previously encrypted char
     * @param key     the given key value
     * @return        the encrypted char as an int
     */
    public static int cipher(char ch, int prev, int key) {
        if (prev == -1) return ch;
        return (ch + prev) % key;
    }

    /**
     * Decrypts one integer according to the given key.
     * If prev is the first character, the char is returned
     * without decryption.
     * @param cipher    the int to be decrypted
     * @param prev      the previously encrypted char
     * @param key       the given key value
     * @return          the decrypted char
     */
    public static char decipher(int cipher, int prev, int key) {
        int decipher;
        if (prev == -1) return (char) cipher;

        decipher = (cipher - prev + key) % key;
        return (char) decipher;
    }
}
