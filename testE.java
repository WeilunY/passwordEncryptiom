import java.util.*;
public class testE{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.print("Simple password: ");
    String in = input.nextLine();
    Encryption test1 = new Encryption(in, true, true, true, true);
    String key1 = test1.getKey1();
    String key2 = test1.getKey2();
    System.out.println("key 1 : " + key1);
    System.out.println("key 2 : " + key2);
    System.out.println("Original:   " + in);
    input.close();
    String e = test1.getEncrypted();
    boolean correct = false;
    while(correct != true){

      boolean u = false;
      boolean l = false;
      boolean n = false;
      boolean s = false;

      for(int i = 0; i < e.length(); i++){
        char c = e.charAt(i);
        if(c >= 65 && c <= 90)
           u = true;
        else if(c >= 97 && c <= 122)
           l = true;
        else if(c >= 48 && c <= 57)
           n = true;
        else
           s = true;
      correct = u && l && n && s;
      if ( correct == false){
        test1.modifyKeys();
        key1 = test1.getKey1();
        key2 = test1.getKey2();
        e = test1.getEncrypted();
      }
      }
    }
    System.out.println("Encryption: " + e);

  }
}
