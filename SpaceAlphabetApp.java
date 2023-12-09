import java.util.Map;
import java.util.Scanner;

public class SpaceAlphabetApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the English string: ");
        String englishString = scanner.nextLine();

        Map<Character, String> spacePersonAlphabet = SpaceAlphabetCipher.createSpacePersonAlphabet();

        String spacePersonString = SpaceAlphabetCipher.convertToSpacePersonString(englishString, spacePersonAlphabet);
        System.out.println("Space Person String: " + spacePersonString);

        String hashValue = SpaceAlphabetCipher.calculateSHA256(spacePersonString);
        System.out.println("SHA256 Hash Value: " + hashValue);

        String caesarCipheredString = SpaceAlphabetCipher.caesarCipher(englishString, 5);
        System.out.println("Caesar Cipher (5-character shift): " + caesarCipheredString);

        System.out.println("Brute Force Caesar Cipher:");
        String caesarCipher = SpaceAlphabetCipher.bruteForceDecrypt(englishString);
    }
}


