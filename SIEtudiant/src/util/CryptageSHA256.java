package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptageSHA256 {

	private static MessageDigest sha256Digest;
	
	
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
	public static String crypter(String message) {
		try {
			sha256Digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertToHex(sha256Digest.digest(message.getBytes()));
	}
	
	public static boolean testPassword(String clearTextTestPassword,String encodedActualPassword) throws NoSuchAlgorithmException
    {
		String encodedTestPassword = crypter(
				clearTextTestPassword);

			return (encodedTestPassword.equals(encodedActualPassword));
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		crypter("alex");
		
		try {
			if(testPassword("alex","4135aa9dc1b842a653dea846903ddb95bfb8c5a10c504a7fa16e10bc31d1fdf0"))
			{
					System.out.println("Hello!!!!!!!!!!");
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
