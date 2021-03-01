package com.loyalty.auth0.proxy.components.utilimport com.loyalty.auth0.proxy.components.constant.Constantsimport org.apache.commons.codec.binary.Base64import javax.crypto.BadPaddingExceptionimport javax.crypto.Cipherimport javax.crypto.IllegalBlockSizeExceptionimport javax.crypto.NoSuchPaddingExceptionimport java.nio.charset.StandardCharsetsimport java.nio.file.Filesimport java.nio.file.Pathsimport java.security.*import java.security.spec.InvalidKeySpecExceptionimport java.security.spec.PKCS8EncodedKeySpecimport java.security.spec.X509EncodedKeySpecclass Encryptor {    final static String PRIVATE_KEY_FILE_NAME = "private_key_dev.der"    final static String PUBLIC_KEY_FILE_NAME = "public_key_dev.der"//    final static String PRIVATE_KEY_FILE_NAME = "private_key.der"//    final static String PUBLIC_KEY_FILE_NAME = "public_key.der"    final static String KEY_PATH = "src/main/resources/key/"    static byte[] readFileBytes(String filename) throws IOException    {        Files.readAllBytes(Paths.get(filename))    }    static PublicKey readPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException    {        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(readFileBytes(KEY_PATH + PUBLIC_KEY_FILE_NAME))        KeyFactory.getInstance(Constants.RSA).generatePublic(publicSpec)    }    static PrivateKey readPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException    {        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(KEY_PATH + PRIVATE_KEY_FILE_NAME))        KeyFactory.getInstance(Constants.RSA).generatePrivate(keySpec)    }    static String encrypt(String plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException    {        Cipher cipher = Cipher.getInstance(Constants.RSA)        cipher.init(Cipher.ENCRYPT_MODE, readPublicKey())        Base64.encodeBase64String(cipher.doFinal(plaintext.getBytes(Constants.UTF_8)))    }    static String decrypt(String text) throws IOException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException {        byte[] dectyptedText        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")        cipher.init(Cipher.DECRYPT_MODE, readPrivateKey())        dectyptedText = cipher.doFinal(decodeBASE64(text))        new String(dectyptedText, Constants.UTF_8)    }    private static byte[] decodeBASE64(String text) throws IOException {        Base64.decodeBase64(text)    }    static String encodeValue(String value) {        try {            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());        } catch (UnsupportedEncodingException ex) {            throw new RuntimeException(ex.getCause());        }    }}