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

  private char[] keyOne;
  private char[] keyTwo;

  private String account = "NONE";


  public Encryption(String simple, boolean wantUpper,
                    boolean wantLower, boolean wantNumber,
                    boolean wantSpecial, String account){
      this(simple, wantUpper, wantLower, wantNumber, wantSpecial);
      this.account = account;
    }

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

    ArrayList<Character> key1 = new ArrayList<Character>();
    ArrayList<Character> key2 = new ArrayList<Character>();
    //Initialize both key1 and key2
    if(hasUpper){
      String u = "ABCDEFGHIJILMNOPQRSTUVWXYZ";
      for(char ch : u.toCharArray()){
        key1.add(ch);
      }
    }

    if(hasLower){
      String l = "abcdefjhijklmnopqrstuvwxyz";
      for(char ch : l.toCharArray()){
        key1.add(ch);
      }
    }

    if(hasNumber){
      String n = "1234567890";
      for(char ch : n.toCharArray()){
        key1.add(ch);
      }
    }

    if(hasSpecial){
      String s = "!\"#$%&\'()*+,-./:;<=>?@[\\]_{|}";
      for(char ch : s.toCharArray()){
        key1.add(ch);
      }
    }

    // wants
    if(wantUpper){
      String u = "ABCDEFGHIJILMNOPQRSTUVWXYZ";
      for(char ch : u.toCharArray()){
        key2.add(ch);
      }
    }

    if(wantLower){
      String l = "abcdefjhijklmnopqrstuvwxyz";
      for(char ch : l.toCharArray()){
        key2.add(ch);
      }
    }

    if(wantNumber){
      String n = "1234567890";
      for(char ch : n.toCharArray()){
        key2.add(ch);
      }
    }

    if(wantSpecial){
      String s = "!\"#$%&\'()*+,-./:;<=>?@[\\]_{|}";
      for(char ch : s.toCharArray()){
        key2.add(ch);
      }
    }

    // shuffle both keys
    Collections.shuffle(key1);
    Collections.shuffle(key2);

    keyOne = new char[key1.size()];
    for(int i = 0; i < key1.size(); i++){
      keyOne[i] = key1.get(i);
    }

    keyTwo = new char[key2.size()];
    for(int i = 0; i < key2.size(); i++){
      keyTwo[i] = key2.get(i);
    }


  }

  public Encryption(String simple, String keyOne, String keyTwo){
    this.original = simple;
    this.keyOne = keyOne.toCharArray();
    this.keyTwo = keyTwo.toCharArray();
  }

  public String getEncrypted(){
    char[] e = new char[length];
    char[] o = original.toCharArray();

    for(int i = 0; i < o.length; i++){
      int pos = getIndex(o[i], keyOne);
      if (pos >= 0)
        e[i] = keyTwo[pos];
    }

    String encrypted = new String(e);

    return encrypted;
  }

  public String getKey1(){
    String result = new String(keyOne);
    return result;
  }

  public String getKey2(){
    String result = new String(keyTwo);
    return result;
  }

  // Use for changing keys
  public void modifyKeys(){
    ArrayList<Character> key1 = new ArrayList<Character>();
    ArrayList<Character> key2 = new ArrayList<Character>();
    //Initialize both key1 and key2
    if(hasUpper){
      String u = "ABCDEFGHIJILMNOPQRSTUVWXYZ";
      for(char ch : u.toCharArray()){
        key1.add(ch);
      }
    }

    if(hasLower){
      String l = "abcdefjhijklmnopqrstuvwxyz";
      for(char ch : l.toCharArray()){
        key1.add(ch);
      }
    }

    if(hasNumber){
      String n = "1234567890";
      for(char ch : n.toCharArray()){
        key1.add(ch);
      }
    }

    if(hasSpecial){
      String s = "!\"#$%&\'()*+,-./:;<=>?@[\\]_{|}";
      for(char ch : s.toCharArray()){
        key1.add(ch);
      }
    }

    // wants
    if(wantUpper){
      String u = "ABCDEFGHIJILMNOPQRSTUVWXYZ";
      for(char ch : u.toCharArray()){
        key2.add(ch);
      }
    }

    if(wantLower){
      String l = "abcdefjhijklmnopqrstuvwxyz";
      for(char ch : l.toCharArray()){
        key2.add(ch);
      }
    }

    if(wantNumber){
      String n = "1234567890";
      for(char ch : n.toCharArray()){
        key2.add(ch);
      }
    }

    if(wantSpecial){
      String s = "!\"#$%&\'()*+,-./:;<=>?@[\\]_{|}";
      for(char ch : s.toCharArray()){
        key2.add(ch);
      }
    }

    // shuffle both keys
    Collections.shuffle(key1);
    Collections.shuffle(key2);

    this.keyOne = new char[key1.size()];
    for(int i = 0; i < key1.size(); i++){
      keyOne[i] = key1.get(i);
    }

    this.keyTwo = new char[key2.size()];
    for(int i = 0; i < key2.size(); i++){
      keyTwo[i] = key2.get(i);
    }

  }

  public boolean[] getNeeds(){
    boolean[] needs = {wantUpper, wantLower, wantNumber, wantSpecial};
    return needs;
  }

  public String getAccount(){
    return this.account;
  }

  // Help methods:
  public static int getIndex(char a, char[] arr){
    int pos = 0;
    for(int i = 0; i < arr.length; i++){
      if(arr[i] == a){
        pos = i;
      }
    }
    return pos;
  }
}
