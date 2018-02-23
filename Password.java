import java.io.FileOutputStream;
import java.io.*;

public class Password{
	public static void main(String[] args){
		addMorePassword("facebook", "q348(2Q");
		addMorePassword("P", "Q");
	}

	//if user doesn't have Password.txt (first time using)
	public static void addPassword(String simplePassword, String encrypted){

		try{
		java.io.File file = new java.io.File("Password.txt");

		java.io.PrintWriter output = new java.io.PrintWriter(file);

			output.println(simplePassword);
			output.println(encrypted);

			//close the file
			output.close();
		}
		catch (IOException ex){
			System.out.print("Input error");
		}
	}


	//Add more password on the next line in Password.txt
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
