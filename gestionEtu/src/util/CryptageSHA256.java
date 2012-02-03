package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptageSHA256 {

	private MessageDigest sha256Digest;
	
	public CryptageSHA256() {
		try {
			// Type de cryptage, ici SHA256
			sha256Digest = MessageDigest.getInstance("SHA-256");
		}
		catch(NoSuchAlgorithmException e) {
			System.out.println(e);
		}
	}
	
	/*
	 * Fonction de cryptage qui convertit un tableau de byte en hexa
	 */
	public static String convertToHex(byte[] messageDigest) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++) {
			int moitieByte = messageDigest[i] >> 4 & 0x0F;
			for (int j = 0; j < 2; j++) {
				if(moitieByte >= 10) {
					buf.append((char) ('a' + moitieByte - 10));
				}
				else {
					buf.append((char) ('0' + moitieByte));
				}
				moitieByte = messageDigest[i] & 0x0F;
			}
		}
		return buf.toString();
	}
	
	/*
	 * Va crypter la chaine passée en parametre grace a la fct precedente
	 */
	public String crypter(String message) {
		return convertToHex(sha256Digest.digest(message.getBytes()));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CryptageSHA256 c = new CryptageSHA256();
		System.out.println(c.crypter("alex"));
	}
}
