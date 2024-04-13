package server.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Generate authorization tokens of 256 bits for domain query authentication
 */
public class TokenGenerator {
    private static final int TOKEN_LENGTH_BYTES = 32;

    public static String generateAuthorizationToken() {
        SecureRandom random = new SecureRandom();
        byte[] token_bytes = new byte[TOKEN_LENGTH_BYTES];
        random.nextBytes(token_bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token_bytes);
    }
}
