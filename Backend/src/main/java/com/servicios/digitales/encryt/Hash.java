/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.servicios.digitales.encryt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author SOPORTE-1
 */
public class Hash {

       private static final String SHA256 = "SHA-256";

    /**
     * *
     *
     */
    private Hash() {

    }

    /**
     * cifra una cadena de texto *
     *
     * @param password cadena de texto a cifrar
     * @return devuelve una cadena de texto cifrada
     */
    public static String encrypt(String password) {
        try {
            // Selecciona el algoritmo de hash SHA-256
            MessageDigest _md = MessageDigest.getInstance(SHA256);

            // Aplica el algoritmo de hash a la contraseña
            byte[] _hashedPassword = _md.digest(password.getBytes());

            // Convierte el hash en una cadena de caracteres
            StringBuilder _sb = new StringBuilder();
            for (byte _b : _hashedPassword) {
                _sb.append(String.format("%02x", _b));
            }
            return _sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Si el algoritmo no está disponible, lanza una excepción
            throw new RuntimeException("Error en la encriptación de la contraseña", e);
        }
    }
}
