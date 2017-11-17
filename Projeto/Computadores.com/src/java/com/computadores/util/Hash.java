package com.computadores.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.mindrot.jbcrypt.BCrypt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eduardo
 */
public class Hash {

    public static final int MD5 = 1;
    public static final int BCRYPT = 2;

    private static final int WORKLOAD = 12;

    public static String getHash(String valor, int metodo) {
        if (metodo == MD5) {
            try {
                MessageDigest m = MessageDigest.getInstance("MD5");
                m.update(valor.getBytes(), 0, valor.length());
                return new BigInteger(1, m.digest()).toString(16);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (metodo == BCRYPT) {
            return BCrypt.hashpw(valor, BCrypt.gensalt(WORKLOAD));
        }
        return null;
    }

    public static boolean validar(String valor, String hash, int metodo) {
        if (metodo == MD5) {
            try {
                MessageDigest m = MessageDigest.getInstance("MD5");
                m.update(valor.getBytes(), 0, valor.length());
                String thash = new BigInteger(1, m.digest()).toString(16);
                return hash.equalsIgnoreCase(thash);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (metodo == BCRYPT) {
            if (!hash.startsWith("$2a$")) {
                return false;
            } else {
                return BCrypt.checkpw(valor, hash);
            }
        }
        return false;
    }
}
