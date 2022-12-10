package com.example.carpoolas.model;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * A class to represent an authentication key.
 */
public class AuthKey {

    private String salt; // the random salt added to the password
    private String key; // the cryptographic

    /**
     * Empty constructor, needed by Firestore's built-in serialization.
     */
    public AuthKey() { }

    /**
     * Create a new authentication key from a plaintext password, using a randomly-generated salt.
     *
     * @param password the plaintext password.
     */
    public AuthKey(@NonNull String password) {
        this(AuthKey.generateSalt(), password);
    }

    /**
     * Create a new authentication key from a plaintext password, using the specified salt.
     *
     * @param salt the salt to use in the key generation.
     * @param password the plaintext password to use in the key generation.
     */
    private AuthKey(@NonNull String salt, @NonNull String password) {
        this.salt = salt;
        this.key = generateKey(salt, password);
    }

    /**
     * Returns the auth key's salt.
     * @return the salt.
     */
    public String getSalt() {
        return this.salt;
    }

    /**
     * Returns the auth key's cryptographic key.
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Determines whether a password matches the one used to generate the auth key the method
     * is called on.
     *
     * @param password the password to validate against the key.
     * @return true if keys match, false otherwise.
     */
    public boolean validatePassword(String password) {
        AuthKey oauthKey = new AuthKey(this.salt, password);
        return this.key.equals(oauthKey.key);
    }

    /**
     * Returns a textual representation of the key.
     * @return a textual representation of the key.
     */
    @Override
    @NonNull
    public String toString() {
        return String.format("salt: %s, key: %s", this.salt, this.key);
    }

    private static final int SALT_LEN = 20;
    private static final int KEY_LEN = 40;
    private static final int NITERS = 64000;

    /**
     * Generates a random salt string.
     *
     * @return a random salt string.
     */
    @NonNull
    private static String generateSalt() {
        byte[] salt = new byte[SALT_LEN];
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            Log.e("NextGenPos", "Error generating authentication salt", e);
        }
        String saltStr = Base64.getEncoder().encodeToString(salt);
        return saltStr;
    }

    /**
     * Generate a cryptographic key from a salt, plaintext combination.
     *
     * @param salt the salt to the added to the password.
     * @param password the plaintext password.
     * @return the resulting cryptographic key.
     */
    private static String generateKey(String salt, String password) {
        String hashStr = null;
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] saltBytes = Base64.getDecoder().decode(salt);
            char[] chars = password.toCharArray();
            PBEKeySpec spec = new PBEKeySpec(chars, saltBytes, NITERS, KEY_LEN * Byte.SIZE);

            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            hashStr = Base64.getEncoder().encodeToString(hashBytes);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            Log.e("NextGenPos", "Error generating authentication key", e);
        }
        return hashStr;
    }
}
