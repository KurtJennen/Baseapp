package be.luxuryoverdosis.framework.business.encryption;

import org.apache.commons.codec.binary.Base64;

public final class Encryption {
	private Encryption() {
	}
	
	public static String encode(final String decodedText) {
		return new String(Base64.encodeBase64(decodedText.getBytes()));
	}
	
	public static String decode(final String encodedText) {
		return new String(Base64.decodeBase64(encodedText.getBytes()));
	}
}
