package br.tietjen.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncode implements PasswordEncoder {

	private final char[] KEY = "tietjen".toCharArray();
	private final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
			(byte) 0x10, (byte) 0x12, };

	@Override
	public String encode(CharSequence rawPassword) {
		try {
			return encrypt(rawPassword.toString());
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String decryptPass = null;

		try {
			decryptPass = decrypt(encodedPassword);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| NoSuchPaddingException | IOException e) {
			e.printStackTrace();
		}
		return rawPassword.toString().equals(decryptPass);
	}

	private String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return base64Encode(pbeCipher.doFinal(property.getBytes(StandardCharsets.UTF_8)));
	}

	private String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public String decrypt(String property) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, NoSuchPaddingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndTripleDES");
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(KEY));
		Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
		return new String(pbeCipher.doFinal(base64Decode(property)), StandardCharsets.UTF_8);
	}

	private byte[] base64Decode(String property) throws IOException {
		return Base64.getDecoder().decode(property);
	}

}
