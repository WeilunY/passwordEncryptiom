import java.util.*;

/*
 * The encryption class creates two keys that encrypt the simple password
 */
public class Encryption{

  // Data Files:
  private String original;

  // The characters contains by user's simple password
  private boolean hasUpper;
  private boolean hasLower;
  private boolean hasNumber;
  private boolean hasSpecial;

  // The characters that user desire to have
  private boolean wantUpper;
  private boolean wantLower;
  private boolean wantNumber;
  private boolean wantSpecial;

  private int length;

  // Encryption key
  private ArrayList<Character> key1 = new ArrayList<Character>();
  private ArrayList<Character> key2 = new ArrayList<Character>();

  private String account = "NONE";

  /*
   * Constructer, initialize all data fields
   * Used if has account name
   * @param simple: user's input
   * @param wantUpper: whether key2 contains uppercase
   * @param wantLower: whether key2 contains lowercase
   * @param wantNumber: whether key2 contains numbers
   * @param wantSpecial: whether key2 contains special chars
   * @param account: account name (optional)
   */
  public Encryption(String simple, boolean wantUpper,
                    boolean wantLower, boolean wantNumber,
                    boolean wantSpecial, String account){
      this(simple, wantUpper, wantLower, wantNumber, wantSpecial);
      this.account = account;
    }

  /*
   * Main Constructer, initialize all data fields except account
   * @param simple: user's input
   * @param wantUpper: whether key2 contains uppercase
   * @param wantLower: whether key2 contains lowercase
   * @param wantNumber: whether key2 contains numbers
   * @param wantSpecial: whether key2 contains special chars
   */
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

    // Initialize both key1 and key2
    // key1: (has)
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

    // key2: (wants)
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
  }


  /*
   * Constructer for password retrival
   * @param simple: user's input
   * @param keyOne: key1 as string retrived from local txt
   * @param keyTwo: key2 as string retrived from local txt
   */
  public Encryption(String simple, String keyOne, String keyTwo){
    this.original = simple;
    this.length = simple.length();

    // initialze keys
    for(int i = 0; i < keyOne.length(); i++){
      this.key1.add(keyOne.charAt(i));
    }

    for(int j = 0; j < keyTwo.length(); j++){
      this.key2.add(keyTwo.charAt(j));
    }
  }

  // instance methods:

  /*
   * This method creates an encrypted base on key1 and key2
   * It checks the position of char in key1 and return the same position in key2
   * @return: the encrypted password that meet all the need
   */
  public String getEncrypted(){

    // Protection (optional with interface)
    if(length < 6){
      return "Please enter at least 6 chars";
    }
    else if(checkRep(original) == false){
      return "Please enter more than 4 different chars";
    }

    char[] e = new char[length];
    char[] o = original.toCharArray();

    boolean correct = false;

    // generate encrypted and check whether the result meet all wants.
    do{
      boolean u = false;
      boolean l = false;
      boolean n = false;
      boolean s = false;

      // generate encrypted
      for(int i = 0; i < o.length; i++){
        int pos = getIndex(o[i], key1);
        if (pos > key2.size()){
          i = 0;
          Collections.shuffle(key1);
          Collections.shuffle(key2);
          continue;
        }
        if (pos >= 0)
          e[i] = key2.get(pos);
        }

      // check wants
      for(int i = 0; i < e.length; i++){
        char c = e[i];
        if(c >= 65 && c <= 90)
          u = true;
        else if(c >= 97 && c <= 122)
          l = true;
        else if(c >= 48 && c <= 57)
          n = true;
        else
          s = true;
        }

        correct = (u == this.wantUpper) && (l == this.wantLower)
                    && (n == this.wantNumber) && (s == this.wantSpecial);

        // reshuffle the keys if wants not meet
        if(correct == false){
          Collections.shuffle(key1);
          Collections.shuffle(key2);
        }
      } while (correct == false);

    String encrypted = new String(e);

    return encrypted;
  }

  /*
   * This method creates an encrypted base on key1 and key2 retrived from local txt
   * It checks the position of char in key1 and return the same position in key2
   * - No wants check (more efficient)
   * @return: the past encrypted password
   */
  public String getPastEncrypted(){
    char[] e = new char[length];
    char[] o = original.toCharArray();
    for(int i = 0; i < length; i++){
      int pos = getIndex(o[i], key1);
      if (pos >= 0)
        e[i] = key2.get(pos);
      }
      String encrypted = new String(e);

      return encrypted;
  }

  // Set account (optional)
  public void setAccount(String name){
    this.account = name;
  }

  /*
   * This method return key1 as String
   * @return: key1 as string
   */
  public String getKey1(){
    char[] r = new char[this.key1.size()];
    for(int i = 0; i < this.key1.size(); i++){
      r[i] = this.key1.get(i);
    }
    String result = new String(r);
    return result;
  }


  /*
   * This method return key2 as String
   * @return: key2 as string
   */
  public String getKey2(){
    char[] r = new char[this.key2.size()];
    for(int i = 0; i < this.key2.size(); i++){
      r[i] = this.key2.get(i);
    }
    String result = new String(r);
    return result;
  }

  /*
   * This method return the types of chars original password contains
   * @return: # of types char in simple
   */
  public int getHas(){
    int has = 0;
    has += (this.hasUpper == true) ? 1 : 0;
    has += (this.hasLower == true) ? 1 : 0;
    has += (this.hasSpecial == true) ? 1 : 0;
    has += (this.hasNumber == true) ? 1 : 0;
    return has;
  }

  /*
   * This method return account as String
   * @return: account as string
   */
  public String getAccount(){
    return this.account;
  }

  /*
   * This method return both keys as String array
   * @return: keys as string array
   */
  public String[] getKeys()
  {
    String[] keys = new String[2];
    keys[0] = getKey1();
    keys[1] = getKey2();
    return keys;
  }


  // Help methods:

  /*
   * This method return the index of char in arraylist
   * @param a: the char checked for index
   * @param arr: the arraylist char need to be checked in
   * @return: index of char in arraylist
   */
  public static int getIndex(char a, ArrayList<Character> arr){
    int pos = 0;
    for(int i = 0; i < arr.size(); i++){
      if(arr.get(i) == a){
        pos = i;
      }
    }
    return pos;
  }


  /*
   * This method check whether string contains more than 4 different chars
   * @param o: the string needed for check
   * @return: true if o contains more than 4 different chars, false otherwise
   */
  public static boolean checkRep(String o){
    int variation = 0;
    ArrayList<Character> v = new ArrayList<Character>();
    boolean check = true;
    for(int i = 0; i < o.length(); i++){
      for(int j = 0; j < v.size(); j++){
        if(v.get(j) == o.charAt(i))
          check = false;
      }
      if(check){
        variation++;
        v.add(o.charAt(i));
      }
      check = true;
    }
    return variation > 4;
  }


}
