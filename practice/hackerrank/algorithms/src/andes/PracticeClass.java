package andes;

import java.util.Scanner;

public class PracticeClass {
	
	private static class Person{
		public int age;
		public Person(int age) {
			if(age < 0){
				System.out.println("This person is not valid, setting age to 0.");
				this.age = 0;
			}else{
				this.age = age;
			}
		}
		
		public void yearPasses(){
			age ++;
		}
		
		public void amIOld(){
			if(age < 13)
				System.out.println("You are young.");
			else if(age >= 13 && age < 18)
				System.out.println("You are a teenager.");
			else 
				System.out.println("You are old.");				
		}
	}
	
	public static void createAndAge(int age){
		Person p = new Person(age);
		p.amIOld();
		for(int i=0; i<3; i++) p.yearPasses();
		p.amIOld();
		System.out.println();
	}

	public static void main(String[] args) {		
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		for(int i=0; i<T; i++){
			int age = in.nextInt();
			createAndAge(age);
		}
		
		in.close();
	}

}
