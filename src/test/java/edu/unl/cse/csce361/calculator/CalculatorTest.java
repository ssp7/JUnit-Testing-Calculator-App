package edu.unl.cse.csce361.calculator;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import javax.sql.rowset.CachedRowSet;

import static org.junit.Assert.*;

public class CalculatorTest {
	private InputStream in;
	private PrintStream out;
	private PrintStream err;
	public static final int MAXIMUM_TOKEN_LENGTH = 20;

	private Calculator calculator;
	private ByteArrayOutputStream fakeOut;
	private String lineSeparator;

	private static void setFakeIn(String input) {
		if (input == null) {
			input = "";
		}
		System.setIn(new ByteArrayInputStream(input.getBytes()));
	}


	@Before
	public void setUp() {
		if (System.getProperty("os.name").contains("Windows"))
			lineSeparator = "\r\n";
		else
			lineSeparator = "\n";
		calculator = new Calculator();      // ensures each test case has a fresh Calculator object to work with
		in = System.in;
		out = System.out;
		err = System.err;
		fakeOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(fakeOut));
	}

	@After
	public void tearDown() {
		System.setIn(in);
		System.setOut(out);
		System.setErr(err);
	}

/*	
    @Test
    public void fakeInputOutputDemonstration() {
        setFakeIn("this is a test.");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println(line);
        assertEquals("this is a test.\n", fakeOut.toString());  // note the linefeed!
    }
	 
*/
	//Testing if it shows the error message when we try to pop an empty stack
	@Test
	public void testPopEmptyStack() {
		int type = '=';
		char[] token = "=".toCharArray();
		String message = "\t0.0";
		String output = "error: stack empty" + lineSeparator;
		assertEquals(message, calculator.calculate(type, token));
		assertEquals(output, fakeOut.toString());
	}
	//Testing with dividing any number with 0
	@Test
	public void testZeroDividing() {
		int type = Calculator.NUMBER;
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);
		token = "0".toCharArray();
		calculator.calculate(type, token);
		type = '/';

		assertEquals("zero divisor popped", calculator.calculate(type, token));
		

	}

	//Testing with character of length 20
	@Test
	public void charTwentyPlusTest(){
		int type = Calculator.OPERAND_TOO_LONG;
		char[] token = "111111111111111111111".toCharArray(); 

		assertEquals("1111111111111111111... is too long", calculator.calculate(type, token));

	}
	//checking if integer char length is 20
	@Test
	public void charTwenty(){
		int type = Calculator.OPERAND_TOO_LONG;
				
		char[] token = "12345678901234567890".toCharArray();
	
		assertEquals("1234567890123456789... is too long", calculator.calculate(type, token));

	}
	//Testing if the function will wait for input until proper command is passed like " 1 1 + = "
	@Test
	public void waitingInputCheck() {
		int type = Calculator.NUMBER;
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);

		assertEquals(null, calculator.calculate(type, token));

	}
	//Testing with type '='

	@Test
	public void equalTypeTest() {
		int type = Calculator.NUMBER;
		char[] token = "2".toCharArray();
		calculator.calculate(type, token);
		token = "1".toCharArray();
		calculator.calculate(type, token);
		type = '=';
	String output = "\t1.0";
	 assertEquals(output, calculator.calculate(type, token));
		
	}

	//checking if addition works
	@Test
	public void checkAddition() {
		int type = Calculator.NUMBER;
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);
	 token = "1".toCharArray();
		calculator.calculate(type, token);
		type = '+';
		calculator.calculate(type, token);
		type = '=';
		assertEquals("\t2.0", calculator.calculate(type, token));
		
	}
	//checking if substraction works
	@Test
	public void checkSubstraction() {
		int type = Calculator.NUMBER;
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);
	 token = "1".toCharArray();
		calculator.calculate(type, token);
		type = '-';
		calculator.calculate(type, token);
		type = '=';
		assertEquals("\t0.0", calculator.calculate(type, token));
		
	}
	//checking if the multiplication works
	@Test
	public void checkMultiplication() {
		int type = Calculator.NUMBER;
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);
	 token = "1".toCharArray();
		calculator.calculate(type, token);
		type = '*';
		calculator.calculate(type, token);
		type = '=';
		assertEquals("\t1.0", calculator.calculate(type, token));
		
	}
	//checking if division works
	@Test
	public void checkDivision() {
		int type = Calculator.NUMBER;
		char[] token = "4".toCharArray();
		calculator.calculate(type, token);
	 token = "2".toCharArray();
		calculator.calculate(type, token);
		type = '/';
		calculator.calculate(type, token);
		type = '=';
		assertEquals("\t2.0", calculator.calculate(type, token));
		
	}
		//Testing with type 'more than 100 stacks' for Equivalance Partition
	@Test
	public void stackFullTest() {
		int type = Calculator.NUMBER;
		for(int i =1 ; i <= 100; i++) {
			int num = calculator.NUMBER;
			char[] adding = "2".toCharArray();
			calculator.calculate(num, adding);
		}   
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);
		
		assertEquals("error: stack full" + lineSeparator, fakeOut.toString());
		
	}
	//Test Case for 100 stacks for Equivalance Partition
	@Test
	public void stackHundred() {
		int type = Calculator.NUMBER;
		for(int i =1 ; i < 100; i++) {
			int num = calculator.NUMBER;
			char[] adding = "2".toCharArray();
			calculator.calculate(num, adding);
		}   
		char[] token = "1".toCharArray();
		calculator.calculate(type, token);
		
		assertEquals("", fakeOut.toString());
	}
	
	
	//Testing with c as a type that it will clear the stack
	@Test
	public void checkCType() {
		int type = Calculator.NUMBER;
		char[] token = "4".toCharArray();
		calculator.calculate(type, token);
	 token = "2".toCharArray();
		calculator.calculate(type, token);
		type = '/';
		calculator.calculate(type, token);
		type = '=';
		calculator.calculate(type, token);
		
		type = 'c';
		assertEquals(null, calculator.calculate(type, token));
		
	}
	
	// this function will check if type q terminates the command 
	@Test
	public void checkQType() {
		int type = Calculator.NUMBER;
		char[] token = "4".toCharArray();
		calculator.calculate(type, token);
	 token = "2".toCharArray();
		calculator.calculate(type, token);
		type = '/';
		calculator.calculate(type, token);
		type = '=';
		calculator.calculate(type, token);
		
		type = 'q';
		assertEquals("unknown commandq",calculator.calculate(type, token) );
		
	}
	//checking if the unknown inputs shows the error message
	@Test
	public void checkUnkownType() {
		int type = Calculator.NUMBER;
		char[] token = "4".toCharArray();
		calculator.calculate(type, token);
	 token = "2".toCharArray();
		calculator.calculate(type, token);
		type = '/';
		calculator.calculate(type, token);
		type = '=';
		calculator.calculate(type, token);
		
		type = 'h';
		assertEquals("unknown commandh", calculator.calculate(type, token));
		
	}
	//checking if / and entering wont print anything only = will pop the number
	@Test
	public void dividing() {
		
		int type = calculator.NUMBER;
		char[]token = "8".toCharArray();
		calculator.calculate(type, token);
		token = "4".toCharArray();
		calculator.calculate(type, token);
		type = '/';
		assertEquals(null,calculator.calculate(type, token));
		type = '=';
		assertEquals("\t2.0", calculator.calculate(type, token))
		;
		
	}
	@Test
	public void enteringString() {
		
		int type = 'a';
		String input = " ";
		char[]token = input.toCharArray();
		calculator.calculate(type, token);
		
		assertEquals("unknown commanda", calculator.calculate(type, token));
		
	}
	@Test
	public void checkingNegative() {
		int type = calculator.NUMBER;
		char[] token = "-1".toCharArray();
		calculator.calculate(type, token);
        token = "1".toCharArray();
        calculator.calculate(type, token);
          type = '+';
		assertEquals("0",calculator.calculate(type, token) );
	}
	
	@Test
	public void typeSpace(){
		int type = Calculator.NUMBER;
		char[] token = "\n".toCharArray();
		token =  "\n".toCharArray();
        
		//calculator.run();
		setFakeIn(" ");
		assertEquals("",calculator.calculate(type, token));
	}
	
	@Test 
	public void mainFucntion() throws IOException {
  setFakeIn("9 3 - = q");
	
		Calculator.main(null);
		assertEquals("\t6.0" + lineSeparator,fakeOut.toString());
		
		
	}
	
	
	/*
	 * 
	 
	public void CasSpace() throws IOException {
		
	   setFakeIn("1 1 1=q");
		 Calculator.main(null);
		assertEquals("",fakeOut.toString());
		
	}
	*/
	@Test(timeout = 1000)
	public void large() throws IOException {
		
	   setFakeIn("1234567898765432345678765432 =q");
		 calculator.run();
		assertEquals("",fakeOut.toString());
		
	}
	
	@Test(timeout = 1000)
	public void Decimal() throws IOException {
	setFakeIn("2.9 3.0 + =q");
    calculator.run();
   assertEquals("",fakeOut.toString());
	
	}
}