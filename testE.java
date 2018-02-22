import java.util.*;
public class testE{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.print("Simple password: ");
    String in = input.nextLine();
    Encryption test1 = new Encryption(in, false, true, true, true);

    input.close();
    String e = test1.getEncrypted();
    String key1 = test1.getKey1();
    String key2 = test1.getKey2();
    System.out.println("key 1 : " + key1);
    System.out.println("key 2 : " + key2);
    System.out.println("Original:   " + in);
    //System.out.println("Encryption: " + e);

  }
}
