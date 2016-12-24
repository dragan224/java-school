package Stateless;

import java.math.BigInteger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class CreditCardValidator
 */
@Stateless
@LocalBean
public class CreditCardValidator implements CreditCardValidatorRemote {

	public CreditCardValidator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean Validate(String number) {
		
		if (!isValidFormat(number)) return false;
		
		int sum = 0;
		int length = number.length();
		for (int i = 0; i < length; i++) {
		
		int digit = number.charAt(length - i - 1) - '0';
        if (digit < 0 || digit > 9) return false;
		
		if (i % 2 == 1) {
		     digit *= 2;
		 }
		 sum += digit > 9 ? digit - 9 : digit;
		}
		return sum % 10 == 0;
	}
	
	private boolean isValidFormat(String number) {
		try {
			new BigInteger(number);
		} catch (Exception e) {
			return false;
		}
		
		int length = number.length();
		if (length == 16 && isPrefix(number, new int[]{51, 52, 53, 54, 55}) ||
		    (length == 13 || length == 16) && isPrefix(number, new int[]{4}) ||
			length == 15 && isPrefix(number, new int[]{34, 37}) ||
			length == 14 && isPrefix(number, new int[]{36, 38, 300, 301, 302, 303, 304, 304}) ||
			length == 16 && isPrefix(number, new int[]{6011}) ||
			length == 16 && isPrefix(number, new int[]{3})) return true;
		return false;
	}
	
	private boolean isPrefix(String number, int[] valid_nums) {

		for (int num: valid_nums) {
			if (number.startsWith(Integer.toString(num))) return true;
		}
		return false;
	}

}
