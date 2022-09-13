
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * 
 * @author
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		String[] p = { "3$AB4%#", "aB$1sss", "asd", "hello123" };
		passwords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long. This test should throw a
	 * LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("abcAB"));
			assertTrue(PasswordCheckerUtility.isValidPassword("abcdefg"));
			assertTrue("Did not throw lengthException", false);
		} catch (LengthException e) {
			assertTrue("Successfully threw a lengthExcepetion", true);
		}

		catch (Exception e) {
			assertTrue("Threw some other exception besides lengthException", false);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character This test
	 * should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("123456"));
			assertTrue(PasswordCheckerUtility.isValidPassword("12345A"));
			assertTrue("Did not throw NoUpperAlphaException", false);
		} catch (NoUpperAlphaException e) {
			assertTrue("Successfully threw a NoUpperAlphaExcepetion", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character This test
	 * should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("123456A"));
			assertTrue(PasswordCheckerUtility.isValidPassword("123456a"));
			assertTrue("Did not throw NoLowerAlphaException", false);
		} catch (NoLowerAlphaException e) {
			assertTrue("Successfully threw a NoLowerAlphaExcepetion", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides NoLowerAlphaException", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("1234@aA"));
			assertTrue(PasswordCheckerUtility.isWeakPassword("Aa1$"));
			assertTrue("Did not throw WeakPassword Exception", false);
		} catch (WeakPasswordException e) {
			assertTrue("Successfully threw a WeakPasswordException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Threw some incorrect exception", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Ab1$sGGG"));
			assertTrue(PasswordCheckerUtility.isValidPassword("Abc123%"));
			assertTrue("Did not throw InvalidSequence Exception", false);
		} catch (InvalidSequenceException e) {
			assertTrue("Successfully threw a InvalidSequenceException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides InvalidSequenceException", false);
		}
	}

	/**
	 * Test if the password has at least one digit One test should throw a
	 * NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Ab$sGG"));
			assertTrue(PasswordCheckerUtility.isValidPassword("Abc123%"));
			assertTrue("Did not throw NoDigit Exception", false);
		} catch (NoDigitException e) {
			assertTrue("Successfully threw a NoDigitException", true);
		} catch (Exception e) {
			assertTrue("Threw some other exception besides NoDigitException", false);
		}
	}

	/**
	 * Test correct passwords This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Ab$sGG"));
			assertTrue(PasswordCheckerUtility.isValidPassword("Abc123%"));
			assertTrue("Did not throw an Exception", false);
		} catch (Exception e) {
			assertTrue("Something Went Wrong", true);
		}
	}

	/**
	 * Test the invalidPasswords method Check the results of the ArrayList of
	 * Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {

		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);

		Scanner scan = new Scanner(results.get(0));
		assertEquals(scan.next(), "3$AB4%#");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));

		scan = new Scanner(results.get(1));
		assertEquals(scan.next(), "aB$1sss");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence"));

		scan = new Scanner(results.get(2));
		assertEquals(scan.next(), "asd");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));

		scan = new Scanner(results.get(3));
		assertEquals(scan.next(), "hello123");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
	}

}
