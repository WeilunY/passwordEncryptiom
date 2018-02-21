import java.util.*;
public class Encryption{

  private String original;

  private boolean hasUpper;
  private boolean hasLower;
  private boolean hasNumber;
  private boolean hasSpecial;

  private boolean wantUpper;
  private boolean wantLower;
  private boolean wantNumber;
  private boolean wantSpecial;

  private int length;

  private ArrayList<Character> key1 = new ArrayList<Character>();
  private ArrayList<Character> key2 = new ArrayList<Character>();

  public Encryption(String simple, boolean wantUpper,
                    boolean wantLower, boolean wantNumber,
                    boolean wantSpecial){
    //initialize original string
    this.original = simple;
    // initialize wants
    this.wantUpper = wantUpper;
    this.wantLower = wantLower;
    this.wantNumber = wantNumber;
    this.wantSpecial = wantSpecial;

    // initialize length
    this.length = simple.length();

    // check and initialize has
    for(int i = 0; i < simple.length(); i++){
      char c = simple.charAt(i);
      if(c >= 65 && c <= 90)
        this.hasUpper = true;
      else if(c >= 97 && c <= 122)
        this.hasLower = true;
      else if(c >= 48 && c <= 57)
        this.hasNumber = true;
      else
        this.hasSpecial = true;
    }

    //Initialize both key1 and key2
    if(hasUpper){
      String u = "ABCDEFGHIJILMNOPQRSTUVWXYZ";
      for(char ch : u.toCharArray()){
        key1.add(ch);
        if(wantUpper)
          key2.add(ch);
      }
    }

    if(hasLower){
      String l = "abcdefjhijklmnopqrstuvwxyz";
      for(char ch : l.toCharArray()){
        key1.add(ch);
        if(wantLower)
          key2.add(ch);
      }
    }

    if(hasNumber){
      String n = "1234567890";
      for(char ch : n.toCharArray()){
        key1.add(ch);
        if(wantNumber)
          key2.add(ch);
      }
    }

    if(hasSpecial){
      String s = "!\"#$%&\'()*+,-./:;<=>?@[\\]_{|}";
      for(char ch : s.toCharArray()){
        key1.add(ch);
        if(wantSpecial)
          key2.add(ch);
      }
    }

    // shuffle both keys
    Collections.shuffle(key1);
    Collections.shuffle(key2);

  }

  public String getEncrypted(){
    String encrypted = "";

    // need more...

    return encrypted;
  }

  public String getKey1(){
    return Arrays.toString(this.key1.toArray());
  }

  public String getKey2(){
    return Arrays.toString(this.key2.toArray());
  }

}
