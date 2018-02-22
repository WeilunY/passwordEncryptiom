import java.util.*;
import java.io.FileOutputStream;
import java.io.*;

public class PasswordStorage{
	public static void main(String[] args){
		LinkedHashMap<String,String> passwords = new LinkedHashMap<>();
		readPasswordsToMap(passwords);
		writePasswordsFromMap(passwords);

		//addMorePassword("facebook", "q348(2Q");
		//addMorePassword("P", "Q");
	}

	// Reads storage file into a map that can be easily analyzed.
	public static void readPasswordsToMap(LinkedHashMap<String,String> pwMap)
	{
		try
		{
			java.io.File file = new java.io.File("Password.txt");
			Scanner inputF = new Scanner(file);
			while(inputF.hasNextLine())
			{
				String[] accountAndKey = inputF.nextLine().split(" ");
				pwMap.put(accountAndKey[0], accountAndKey[1]);
			}
			inputF.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not found");
		}
	}

	// Writes passwords to storage file from map.
	public static void writePasswordsFromMap(LinkedHashMap<String,String> pwMap)
	{
		try
		{
			java.io.File file = new java.io.File("Password.txt");
			PrintWriter outputF = new PrintWriter(file);
			for(String key : pwMap.keySet())
			{
				outputF.println(key + " " + pwMap.get(key));
			}
			outputF.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		
	}

	//if user doesn't have Password.txt (first time using)
	public static void addPassword(String account, String key){

		try{
		java.io.File file = new java.io.File("Password.txt");

		java.io.PrintWriter output = new java.io.PrintWriter(file);

			output.println(account);
			output.println(key);

			//close the file
			output.close();
		}
		catch (IOException ex){
			System.out.print("Input error");
		}
	} 


	//Add more password on the next line in Password.txt
	public static void addMorePassword(String account, String key){
		try {
		java.io.PrintWriter output = new java.io.PrintWriter(new FileOutputStream(new java.io.File("Password.txt"), true));

		output.println("");
		output.println(account);
		output.println(key);

		//close the file
		output.close();
		}
		catch (FileNotFoundException e) {
			System.out.print("File not found");
		}
	}
}





