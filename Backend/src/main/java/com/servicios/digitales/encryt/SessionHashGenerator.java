/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.servicios.digitales.encryt;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author SOPORTE-1
 */
public class SessionHashGenerator {
    
    private static final int CODE_LENGTH = 16;

    /**
     * crea un codigo unico de session por cada vez que inicia sesion un usuario
     * *
     *
     * @return devuelve codigo unico alfanumerico
     */
    public static String generateUniqueCustomAlphanumericCode() {
        String prefix = "CODE-" + new java.util.Date().getTime() + "-";
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[CODE_LENGTH];
        secureRandom.nextBytes(randomBytes);
        String code = encodeToAlphanumeric(randomBytes);
        return prefix + code;
    }

    /**
     * *
     *
     * @param bytes lista de bytes
     * @return devuelve una cade de texto
     */
    public static String encodeToAlphanumeric(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

}
