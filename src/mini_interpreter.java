import java.util.ArrayList;
import java.util.Scanner;

/**
 * It's a mini-interpreter for a toy programming language that allows the
 * following: 
 * 1. The use of variables that consist of a single letter (e.g. A,a, ...) 
 * 2. The use of whole numbers: ( e.g. -1, -20, 0, 1, 200) 
 * 3. Assignment(=):(e.g.A=B,A=10,) 
 * 4. Addition of exactly two elements (variables
 * or constants) (+) : ( e.g. C = A + B, D = 1 + A, ... )
 * 
 * @version 2019/12/07
 */
public class mini_interpreter {
	static ArrayList<Integer> valList = new ArrayList<>();
	static ArrayList<String> symList = new ArrayList<>();

	/**
	 * A method to run the code. It will call other methods
	 * 
	 * @param program The program to run
	 */
	public static void run(String program) {
		// delete all the spaces
		String program2 = program.replaceAll(" ", "");
		String[] states = program2.split("\n");

		for (int i = 0; i < states.length; i++) {
			if (states[i].indexOf("+") != -1) {
				// addition
				addtion(states[i]);
			} else if (states[i].indexOf("=") != -1) {
				// assignment
				assignment(states[i]);
			} else {
				// return
				returnValue(states[i]);
			}
		}
	}

	/**
	 * Method to do addtion
	 * 
	 * @param state The addtion statement.
	 */
	public static void addtion(String state) {
		String[] Elments = state.split("=");
		String symbolic = Elments[0];
		String[] addent = Elments[1].split("\\+");
		int value = getValue(addent[0]) + getValue(addent[1]);
		if (symList.contains(symbolic)) {
			valList.set(symList.indexOf(symbolic), value);
		} else {
			symList.add(symbolic);
			valList.add(value);
		}
	}

	/**
	 * Method to do assignment
	 * 
	 * @param state The assignment statement.
	 */
	public static void assignment(String state) {
		String[] Elments = state.split("=");
		String symbolic = Elments[0];
		Integer value = getValue(Elments[1]);
		if (symList.contains(symbolic)) {
			valList.set(symList.indexOf(symbolic), value);
		} else {
			symList.add(symbolic);
			valList.add(value);
		}

	}

	/**
	 * Method to do return value
	 * 
	 * @param state The return statement.
	 */
	public static void returnValue(String state) {
		if (symList.contains(state)) {
			System.out.println(getValue(state));
		} else {
			System.out.println("The variable " + state + " is not defined");
		}
	}

	/**
	 * Method to get value. If it's a number, it will return the exact number. If
	 * it's a variable, it will return the value of this variable.
	 * 
	 * @param str The number or variable.
	 * @return
	 */
	public static int getValue(String str) {
		if (isNumeric(str))
			return Integer.parseInt(str);
		else if (symList.indexOf(str) != -1) {
			return valList.get(symList.indexOf(str));
		}
		System.out.println("The variable " + str + " is not defined");
		return -1;

	}

	/**
	 * A method to judge whether a string is a number
	 * 
	 * @param str The string to judge.
	 * @return If it's a number return true.
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * The main method to take the input.
	 */
	public static void main(String[] args) {
		System.out.println("Example: \n if you write these codes:");
		String program = "    A = 2\n    B = 8\n    C = A + B\n    C";
		System.out.println(program);
		System.out.print("You will get: ");
		run(program);

		System.out.println("\nNow you can enter your code. (Enter \"exit\" to exit.)");
		String programCode;
		Scanner s = new Scanner(System.in);
		while (s.hasNextLine()) {
			programCode = s.nextLine();
			if (programCode.equals("exit"))
				break;
			run(programCode);
		}
	}
}
