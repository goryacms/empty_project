package ru.bellintegrator.practice.register.service.impl;

import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

@Service
public class HashService {

    private String hashCode;


    /*
    public static void main(String[] args) {
        HashService hs = new HashService();
        try {
            String str = hs.generateShortCode();
            String myVar = hs.generatePasswordHash(str);
         } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    */


    public String generatePasswordHash(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest encoder = MessageDigest.getInstance("SHA-256");
        byte[] digest = encoder.digest(source.getBytes("UTF-8"));

        return new String(Base64.getEncoder().encode(digest));
    }

    public String generateShortCode() {
        String uuid = RandomStringUtils.randomAlphanumeric(8);

        return uuid;
    }

}