//this file contains the class that has the method that allows user to search for their 
//encrypted password in their locally stored Password.txt file, by entering their simple password

import java.util.Scanner;

public class ScanFile{

	/*this main method will prompt user for their simple password, and then search for its
	 *encrypted password in the user's Password.txt file
	 */
 	public static void main(String[] args) throws Exception{

		java.io.File file = new java.io.File("Password.txt");

		Scanner pass = new Scanner(System.in);
		Scanner input = new Scanner(file);

		//prompt user for simple password
		System.out.print("Enter your simple password: ");
		String simplePassword = pass.next();

		//search Password.txt file for the encrypted password
		while(input.hasNext()){
			String searchFile = input.next();
			if(searchFile.contains(simplePassword)){
				System.out.print(input.next());
				break;
			}
			else{
				System.out.print("The password entered does not exist"); 
				break;
			}
		}
	
	}
}



		


