import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SpaceAlphabetCipher {
    public static Map<Character, String> createSpacePersonAlphabet() {
        Map<Character, String> spacePersonAlphabet = new HashMap<>();
        String englishAlphabet = "AXYZBCDEFGHIJKLMNOPQRSTUVW";

        String[] morseCode = {
                "@", "#", "@@", "@#", "#@", "##", "@@@", "@@#", "@#@", "@##", "#@@", "###", "@#@",
                "#@#", "##@", "@@##", "@###", "#@@", "#@#", "###@", "@@@@", "@@##", "@#@", "##@",
                "###@", "####"
        };

        for (int i = 0; i < englishAlphabet.length(); i++) {
            spacePersonAlphabet.put(englishAlphabet.charAt(i), morseCode[i]);
        }

        return spacePersonAlphabet;
    }

    public static String convertToSpacePersonString(String englishString, Map<Character, String> spacePersonAlphabet) {
        StringBuilder spacePersonString = new StringBuilder();

        for (char c : englishString.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                spacePersonString.append(spacePersonAlphabet.getOrDefault(c, ""));
            } else {
                spacePersonString.append(c);
            }
        }

        return spacePersonString.toString();
    }

    public static String calculateSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String caesarCipher(String input, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + shift) % 26 + base));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static String bruteForceDecrypt(String encryptedText) {
        for (int shift = 0; shift < 26; shift++) {
            StringBuilder decryptedText = new StringBuilder();

            for (char character : encryptedText.toCharArray()) {
                if (character != ' ' && character != '!' && character != '.' && character != ',') { // Check if character is not a space or punctuation
                    int originalPosition = character - 'A';
                    int newPosition = (originalPosition - shift) % 26;
                    newPosition = newPosition < 0 ? newPosition + 26 : newPosition;
                    char newCharacter = (char) ('A' + newPosition);
                    decryptedText.append(newCharacter);
                } else {
                    decryptedText.append(character);
                }
            }

            System.out.println(shift + ": " + decryptedText);
        }
        return encryptedText;
    }
}