import java.util.ArrayList;

public class PasswordCheckerUtility {
	private final static int MIN_PASSWORD_LENGTH = 6;

	/**
	 * Compares two passwords to make sure they are the same (case sensitive)
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @throws UnmatchedException
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (password != passwordConfirm) {
			throw new UnmatchedException();
		}
	}

	/**
	 * Compares two passwords to make sure they are the same (case sensitive)
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @return boolean
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the password passed in is between 6-9 characters
	 * 
	 * @param password
	 * @return boolean
	 */
	public static boolean hasBetweenSixandNineChars(String password) {
		if (password.length() >= MIN_PASSWORD_LENGTH && password.length() <= 9) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks if the password passed in has a number
	 * 
	 * @param password
	 * @return boolean
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
				return true;
			}
		}
		throw new NoDigitException();
	}

	/**
	 * checks if the password has at least one lower case letter
	 * 
	 * @param password
	 * @return boolean
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}

	/**
	 * checks if the password has at least one special character
	 * 
	 * @param password
	 * @return boolean
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		for (int i = 0; i < password.length(); i++) {
			if ((password.charAt(i) >= 32 && password.charAt(i) <= 47) || (password.charAt(i) >= 58 && password.charAt(i) <= 64) || (password.charAt(i) >= 91 && password.charAt(i) <= 96) || password.charAt(i) >= 123 && password.charAt(i) <= 126) {
				return true;
			}
		}
		throw new NoSpecialCharacterException();
	}

	/**
	 * checks if there is at least one upper case letter in the password
	 * 
	 * @param password
	 * @return boolean
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
				return true;
			}
		}
		throw new NoUpperAlphaException();

	}

	/**
	 * checks if the password is at least 6 characters long
	 * 
	 * @param password
	 * @return boolean
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException {
		if (password.length() >= MIN_PASSWORD_LENGTH) {
			return true;
		} else {
			throw new LengthException();
		}

	}

	/**
	 * passes in a password and makes sure its long enough, has an upper case and
	 * lower case letter, has a number and a special character, and the sequence is
	 * valid
	 * 
	 * @param password
	 * @return boolean
	 * @throws LengthException
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		if (isValidLength(password) && hasDigit(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasSpecialChar(password) && noSameCharInSequence(password)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks if the password is valid and then states it is weak if the password is
	 * between 6 and 9 characters
	 * 
	 * @param password
	 * @return boolean
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		try {
			isValidPassword(password);
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException | NoSpecialCharacterException | InvalidSequenceException e) {
			System.out.println(e.getMessage());
		}
		if (hasBetweenSixandNineChars(password)) {
			throw new WeakPasswordException();
		} else {
			return false;
		}
	}

	/**
	 * makes sure the password has at least one special character
	 * 
	 * @param password
	 * @return boolean
	 * @throws InvalidSequenceException
	 */
	public static boolean noSameCharInSequence(String password) throws InvalidSequenceException {
		for (int i = 0; i < password.length(); i++) {
			if (i > 1) {
				if (password.charAt(i) == password.charAt(i - 1) && password.charAt(i) == password.charAt(i - 2)) {
					throw new InvalidSequenceException();
				}
			}
		}
		return true;
	}

	/**
	 * accepts an array list of passwords and return an array list of only invalid
	 * passwords in the passed in array list
	 * 
	 * @param passwords
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> failedPasswords = new ArrayList<String>();
		for (int i = 0; i < passwords.size(); i++) {
			try {
				if (!isValidLength(passwords.get(i))) {
					throw new LengthException();
				} else if (!hasUpperAlpha(passwords.get(i))) {
					throw new NoUpperAlphaException();
				} else if (!hasLowerAlpha(passwords.get(i))) {
					throw new NoLowerAlphaException();
				} else if (!hasDigit(passwords.get(i))) {
					throw new NoDigitException();
				} else if (!hasSpecialChar(passwords.get(i))) {
					throw new NoSpecialCharacterException();
				} else if (!noSameCharInSequence(passwords.get(i))) {
					throw new InvalidSequenceException();
				}
			} catch (LengthException e) {
				failedPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			} catch (NoUpperAlphaException e) {
				failedPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			} catch (NoLowerAlphaException e) {
				failedPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			} catch (NoDigitException e) {
				failedPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			} catch (NoSpecialCharacterException e) {
				failedPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			} catch (InvalidSequenceException e) {
				failedPasswords.add(passwords.get(i) + " -> " + e.getMessage());
			}
		}
		return failedPasswords;
	}
}
