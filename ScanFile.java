import java.util.Scanner;

public class ScanFile{
	public static void main(String[] args) throws Exception{

		java.io.File file = new java.io.File("Password.txt");

		Scanner pass = new Scanner(System.in);
		Scanner input = new Scanner(file);

		System.out.print("Enter your simple password: ");
		String simplePassword = pass.next();

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



		


