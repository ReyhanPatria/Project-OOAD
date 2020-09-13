package example.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    
    // Create new random salt
    public static String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        
        return new String(returnValue);
    }
    
    // Hash password with salth based on PBE Encryption algorithm
    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        
        // Clear password's array
        Arrays.fill(password, Character.MIN_VALUE);
        
        try {
        	// Get encryption model
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            // Generate encrypted password
            return skf.generateSecret(spec).getEncoded();
        } 
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } 
        finally {
        	// Clear PBE object
            spec.clearPassword();
        }
    }
    
    // Generate secured password with salt
    public static String generateSecurePassword(String password, String salt) {
        // Encrypt password
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        
        // Convert password into string
        String returnValue = Base64.getEncoder().encodeToString(securePassword);
 
        return returnValue;
    }
    
    // Verify password
    public static boolean verifyUserPassword(String providedPassword,
            String securedPassword, String salt) {
        Boolean returnValue = false;
        
        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        
        // Check if two passwords are equal
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        
        return returnValue;
    }
}
