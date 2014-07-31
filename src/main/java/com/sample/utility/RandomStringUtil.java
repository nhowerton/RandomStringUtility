/**
 * 
 */
package com.sample.utility;

import java.security.SecureRandom;

import com.sample.functional.CharTester;

/**
 * @author neil.howerton
 *
 */
public class RandomStringUtil {
	private SecureRandom rand = new SecureRandom();
	private static final int MAX_ASCII_CHAR = 127;
	private static final int ASCII_NUMBER_START_INDEX = 48;
	private static final int ASCII_NUMBER_END_INDEX = 57;
	private static final int ASCII_UPPER_ALPHA_START_INDEX = 65;
	private static final int ASCII_UPPER_ALPHA_END_INDEX = 90;
	private static final int ASCII_LOWER_ALPHA_START_INDEX = 97;
	private static final int ASCII_LOWER_ALPHA_END_INDEX = 122;
	
	public String getRandomString(int length, CharTester tester) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = length; i > 0; i--) {
            int temp = rand.nextInt(MAX_ASCII_CHAR);

            while (!tester.test(temp)) {
                temp = rand.nextInt(MAX_ASCII_CHAR);
            }

            stringBuilder.append((char)temp);
        }

        return stringBuilder.toString();
    }

    public String getRandomNumericString(int length) {
        return getRandomString(length, (i) -> isNumeric(i));
    }

    public String getRandomAlphaString(int length) {
        return getRandomString(length, (i) -> isAlpha(i));
    }

    public String getRandomAlphaNumericString(int length) {
        return getRandomString(length, (i) -> isAlpha(i) || isNumeric(i));
    }

    public String getRandomNonAlphaNumericString(int length) {
        return getRandomString(length, (i) -> isSpecialCharacter(i));
    }

    private boolean isAlpha(int i) {
        return (i >= ASCII_UPPER_ALPHA_START_INDEX && i <= ASCII_UPPER_ALPHA_END_INDEX) 
        		|| (i >= ASCII_LOWER_ALPHA_START_INDEX && i <= ASCII_LOWER_ALPHA_END_INDEX);
    }

    private boolean isNumeric(int i) {
        return (i >= ASCII_NUMBER_START_INDEX && i <= ASCII_NUMBER_END_INDEX);
    }

    private boolean isSpecialCharacter(int i) {
        return !(isAlpha(i) || isNumeric(i));
    }
}
