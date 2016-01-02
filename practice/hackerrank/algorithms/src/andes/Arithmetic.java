package andes;

import java.util.Scanner;

public class Arithmetic {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		double p0 = in.nextDouble();
		double tip = in.nextDouble();
		double tax = in.nextDouble();
		
		in.close();
		
		long price = Math.round(p0 + p0 * tip / 100 + p0 * tax / 100);
		
		System.out.format( "The final price of the meal is $%d.\n", price ); 

	}
}
