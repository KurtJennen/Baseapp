package be.luxuryoverdosis.framework.business.encryption;

import org.apache.commons.codec.binary.Base64;

public class Encryption {
	public static String encode(String decodedText) {
		return new String(Base64.encodeBase64(decodedText.getBytes()));
	}
	
	public static String decode(String encodedText) {
		return new String(Base64.decodeBase64(encodedText.getBytes()));
	}
}
