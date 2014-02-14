/*
* SecureStorage.java
* Copyright (C) 2013 SINTEF (http://www.sintef.no)
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
* The MIT License (MIT)
* http://opensource.org/licenses/mit-license.php
*
*/
package eu.aniketos.wp1.ststool.threats.preferences;

import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * SecureStorage.java
 * 
 * Utility class used to encrypt and decrypt a String without using a password
 * 
 * @author Mauro Poggianella
 * 
 */
final class SecureStorage {

	/**
	 * The initializations vector for the encryption
	 */
	private final IvParameterSpec ivSpec;

	/**
	 * The initializations vector for the encryption
	 */
	private final byte[] STATIC_PSW;

	/**
	 * The password insert sequence <br>
	 * Note: must be the <b>same length</b> as {@link #PSW_LENGHT}
	 */
	private final int[] sequenceSteps;

	/**
	 * Length of the generated password <br>
	 * Note: using DES encryption it must be long exactly 8 bytes
	 */
	private final static int PSW_LENGHT = 8;

	SecureStorage(long seed) {
		Random random = new Random(seed);

		byte[] ivSpec = new byte[8];
		random.nextBytes(ivSpec);
		this.ivSpec = new IvParameterSpec(ivSpec);

		byte[] staticPsw = new byte[8];
		random.nextBytes(staticPsw);
		this.STATIC_PSW = staticPsw;

		int[] sequenceSteps = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		for (int i = 0; i < sequenceSteps.length; i++) {
			int randomPosition = random.nextInt(sequenceSteps.length);
			int temp = sequenceSteps[i];
			sequenceSteps[i] = sequenceSteps[randomPosition];
			sequenceSteps[randomPosition] = temp;
		}
		this.sequenceSteps = sequenceSteps;
	}

	public static SecureStorage getDefault() {
		return new SecureStorage(1234567890);
	}

	/**
	 * Method that return a configured {@link Cipher}.<br>
	 * It set the {@link #ivSpec initialization vector} and the encriptyion to
	 * DES
	 * 
	 * @param psw
	 *            the password used to encrypt<br>
	 *            Must be 8 bytes length.
	 * @param mode
	 *            The mode that the {@link Cipher} should operate.<br>
	 *            possible values are:<br>
	 *            {@link Cipher#ENCRYPT_MODE} or {@link Cipher#DECRYPT_MODE}
	 * @return the configured {@link Cipher}.
	 */
	private final Cipher getChiper(byte[] psw, int mode) {
		try {
			Cipher chiper = Cipher.getInstance("DES/CBC/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(psw, "DES");
			chiper.init(mode, key, ivSpec);
			return chiper;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Encrypt a String that can be decrypted with
	 * {@link SecureStorage#decryptString(String)}
	 * 
	 * @param string
	 *            the string that have to be encrypted;
	 * @return the encrypted String
	 */
	public String encryptString(String string, boolean staticPassword) {
		byte[] psw = staticPassword ? STATIC_PSW : genPassword();
		byte[] encriptedDes = performEncoding(string.getBytes(), psw, true);
		byte[] encByte = magicInsert(encriptedDes, psw);
		return byteArrayToHexString(encByte);
	}

	/**
	 * Encrypt a String that can be decrypted with
	 * {@link SecureStorage#decryptString(String)}
	 * 
	 * @param string
	 *            the string that have to be encrypted;
	 * @return the encrypted String
	 */
	public String encryptString(String string) {
		return encryptString(string, false);
	}

	/**
	 * Decrypt a String that must has been encrypted with
	 * {@link SecureStorage#encryptString(String)}
	 * 
	 * @param string
	 *            the string that have to be encrypted;
	 * @return the decrypted String or null if the string passed as input is
	 *         invalid;
	 */
	public String decryptString(String string) {
		try {
			byte[] encByte = hexStringToByteArray(string);
			byte[][] extracted = magicExtract(encByte);
			byte[] output = performEncoding(extracted[0], extracted[1], false);
			return new String(output);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method encrypt or decrypt a byte[] input String using a DES
	 * algorithm and a password
	 * 
	 * @param input
	 *            the <code>byte[]</code> that need to be encrypted/decrypted
	 * @param psw
	 *            the <code>byte[]</code> used as password for the operation
	 * @param encrypt
	 *            - <code>true</code> if the method must encrypt<br>
	 *            <code>false</code> if the method must decrypt<br>
	 * @return
	 */
	private byte[] performEncoding(byte[] input, byte[] psw, boolean encrypt) {
		try {
			int mode = encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;
			Cipher cipher = getChiper(psw, mode);
			byte[] converted = new byte[cipher.getOutputSize(input.length)];
			int conv_len = cipher.update(input, 0, input.length, converted, 0);
			conv_len += cipher.doFinal(converted, conv_len);

			byte[] result = new byte[conv_len];
			for (int i = 0; i < result.length; i++)
				result[i] = converted[i];
			return result;
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}

	/**
	 * Insert a <code>byte[]</code> into another <code>byte[]</code> using the
	 * {@link #sequenceSteps}
	 * 
	 * @param input
	 *            the <code>byte[]</code> input
	 * @param arrayToInsert
	 *            the <code>byte[]</code> to insert
	 * @return a new <code>byte[]</code> containing the 2 merged array
	 */
	private byte[] magicInsert(byte[] input, byte[] arrayToInsert) {
		byte[] result = new byte[input.length];
		for (int i = 0; i < result.length; i++)
			result[i] = input[i];
		for (int i = 0; i < PSW_LENGHT; i++) {
			result = insertbyte(result, arrayToInsert[i], sequenceSteps[i]);
		}
		return result;
	}

	/**
	 * Insert a <code>byte</code> into an <code>byte[]</code> at a specific
	 * position.
	 * 
	 * @param input
	 *            the <code>byte[]</code> used as input
	 * @param b
	 *            the <code>byte</code> to insert
	 * @param pos
	 *            the position where insert the <code>byte</code>
	 * @return a new array with the inserted <code>byte</code>
	 */
	private byte[] insertbyte(byte[] input, byte b, int pos) {
		byte[] result = new byte[input.length + 1];
		if (pos < 0)
			pos = 0;
		if (pos > input.length)
			pos = input.length;
		for (int i = 0; i < input.length; i++) {
			if (i >= pos) {
				result[i + 1] = input[i];
			} else {
				result[i] = input[i];
			}
		}
		result[pos] = b;
		return result;
	}

	/**
	 * Extract a <code>byte[]</code> from another <code>byte[]</code> using the
	 * {@link #sequenceSteps}
	 * 
	 * @param input
	 *            the <code>byte[]</code> input
	 * @return a new <code>byte[2][]</code> <br>
	 *         at position<code>[0][]</code> the <code>byte[]</code> containing
	 *         the original array<br>
	 *         at position<code>[1][]</code> the <code>byte[]</code> containing
	 *         the previously inserted array
	 */
	private byte[][] magicExtract(byte[] input) {
		byte[][] result = new byte[2][];

		byte[] array = new byte[input.length];
		for (int i = 0; i < array.length; i++)
			array[i] = input[i];

		byte[] psw = new byte[PSW_LENGHT];

		for (int i = PSW_LENGHT - 1; i >= 0; i--) {
			int pos = sequenceSteps[i];
			psw[i] = array[pos];
			array = removebyte(array, pos);
		}
		result[0] = array;
		result[1] = psw;
		return result;
	}

	/**
	 * Remove a <code>byte</code> from an <code>byte[]</code> at a specific
	 * position.
	 * 
	 * @param input
	 *            the <code>byte[]</code> used as input
	 * @param pos
	 *            the position of the <code>byte</code> to remove
	 * @return a new array with the removed <code>byte</code>
	 */
	private byte[] removebyte(byte[] array, int pos) {
		byte[] result = new byte[array.length - 1];
		if (pos < 0)
			pos = 0;
		for (int i = 0; i < result.length; i++) {
			if (i >= pos) {
				result[i] = array[i + 1];
			} else {
				result[i] = array[i];
			}
		}
		return result;
	}

	/**
	 * Generate a random password of 8 bytes
	 * 
	 * @return a a random password of 8 bytes
	 */
	private static byte[] genPassword() {
		byte[] pswB = new byte[PSW_LENGHT];
		new Random().nextBytes(pswB);
		return pswB;
	}

	/**
	 * Convert a string of hexadecimal values in a <code>byte[]</code>.
	 * 
	 * @param s
	 *            the String containing the hexadecimal values
	 * @return the converted <code>byte[]</code>
	 */
	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * Convert a <code>byte[]</code> in a string of hexadecimal values.
	 * 
	 * @param data
	 *            the <code>byte[]</code> that have to be converted
	 * @return a String of hexadecimal values representing the input
	 */
	private static String byteArrayToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append(String.format("%02X", data[i]));
		}
		return sb.toString();
	}

}
