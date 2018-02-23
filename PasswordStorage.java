import java.util.*;
import java.io.FileOutputStream;
import java.io.*;

public class PasswordStorage{
	LinkedHashMap<String,String[]> pMap = new LinkedHashMap<>();

	public PasswordStorage()
	{
		pMap = readPasswordsToMap();
	}

	// Reads storage file into a map that can be easily analyzed.
	public LinkedHashMap<String,String[]> readPasswordsToMap()
	{
		try
		{
			java.io.File file = new java.io.File("Password.txt");
			Scanner inputF = new Scanner(file);
			while(inputF.hasNextLine())
			{
				String[] accountAndKey = inputF.nextLine().split(" ");
				pMap.put(accountAndKey[0], trimAccount(accountAndKey));
			}
			inputF.close();
			return pMap;
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not found");
			return pMap;
		}
	}

	// Writes passwords to storage file from map.
	public void writePasswordsFromMap()
	{
		try
		{
			java.io.File file = new java.io.File("Password.txt");
			PrintWriter outputF = new PrintWriter(file);
			for(String key : pMap.keySet())
			{
				outputF.println(key + " " + pMap.get(key)[0] + " " + pMap.get(key)[1]);
			}
			outputF.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not Found");
		}
		
	}

	//trims account out of key array
	public String[] trimAccount(String[] accountPresent)
	{
		String[] accNotPresent = new String[2];
		for(int i=1; i<accountPresent.length;i++)
		{
			accNotPresent[i-1] = accountPresent[i];
		}
		return accNotPresent;
	}

	//returns map for interface.
	public LinkedHashMap<String,String[]> getMap()
	{
		return pMap;
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





