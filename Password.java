//this file contains the class that has the methods that store the user's password in a txt file

import java.io.FileOutputStream;
import java.io.*;

public class Password{
	
	/* if user doesn't have Password.txt file, this method will create a new file
	 * named Password.txt, and stores the simple password followed by its encrypted
	 * version in the next line.
	 */	
	public static void addPassword(String simplePassword, String encrypted){

		try{
			File file = new File("Password.txt"); 

			PrintWriter output = new PrintWriter(file);

			output.println(simplePassword);
			output.println(encrypted);

			//close the file
			output.close();
		}

		catch (IOException ex){
			System.out.print("Input error");
		}
	}


	/* this methos will be invoked when the user already has a Password.txt file. It will
	 * store the new password the user just created in the file on the next line after the 
	 * previous passowrds.
	 *
	 * The file will have the following format:
	 * previously-stored-simple-password
	 * previously-stored-encrypted-password
	 * *empty line*
	 * currently-added-simple-password
	 * currently-stored-encrypted-password
	 */  
	public static void addMorePassword(String simplePassword, String encrypted){
		try {
		java.io.PrintWriter output = new java.io.PrintWriter(new FileOutputStream(new java.io.File("Password.txt"), true));

		output.println("");
		output.println(simplePassword);
		output.println(encrypted);

		//close the file
		output.close();
		}
		catch (FileNotFoundException e) {
			System.out.print("File not found");
		}
	}
}
