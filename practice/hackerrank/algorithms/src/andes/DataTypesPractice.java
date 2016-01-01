package andes;

public class DataTypesPractice {

	private static void printType(int x) {
		System.out.println("Primitive : Integer");
	}

	private static void printType(double x) {
		System.out.println("Primitive : Double");
	}

	private static void printType(boolean x) {
		System.out.println("Primitive : Boolean");
	}

	private static void printType(char x) {
		System.out.println("Primitive : Character");
	}

	private static void printType(String x) {
		System.out.println("Primitive : String");
	}

	public static void main(String[] args) {
		printType(5.35);
		printType('a');
		printType(false);
		printType(100);
		printType("I am a code monkey");
		printType(true);
		printType(17.3);
		printType('c');
		printType("derp");
	}

}
