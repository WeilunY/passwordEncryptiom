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

    //ArrayList<Character> key1 = new ArrayList<Character>();
    //ArrayList<Character> key2 = new ArrayList<Character>();
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
  }

  public Encryption(String simple, String keyOne, String keyTwo){
    this.original = simple;
    // need a for loop here
    for(int i = 0; i < keyOne.length(); i++){
      this.key1.add(keyOne.charAt(i));
    }

    for(int i = 0; i < keyTwo.length(); i++){
      this.key2.add(keyOne.charAt(i));
    }
  }

  public String getEncrypted(){
    if(length < 6){
      return "Please enter at least 6 chars";
    }
    else if(checkRep(original) == false){
      return "Please enter more than 4 different chars";
    }

    char[] e = new char[length];
    char[] o = original.toCharArray();

    boolean correct = false;

    do{
      boolean u = false;
      boolean l = false;
      boolean n = false;
      boolean s = false;

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

        if(correct == false){
          Collections.shuffle(key1);
          Collections.shuffle(key2);
        }
      } while (correct == false);

    String encrypted = new String(e);

    return encrypted;
  }

  public void setAccount(String name){
    this.account = name;
  }

  public String getKey1(){
    char[] r = new char[this.key1.size()];
    for(int i = 0; i < this.key1.size(); i++){
      r[i] = this.key1.get(i);
    }
    String result = new String(r);
    return result;
  }

  public String getKey2(){
    char[] r = new char[this.key2.size()];
    for(int i = 0; i < this.key2.size(); i++){
      r[i] = this.key2.get(i);
    }
    String result = new String(r);
    return result;
  }

  public boolean[] getNeeds(){
    boolean[] needs = {wantUpper, wantLower, wantNumber, wantSpecial};
    return needs;
  }

  public int getHas(){
    int has = 0;
    has += (this.hasUpper == true) ? 1 : 0;
    has += (this.hasLower == true) ? 1 : 0;
    has += (this.hasSpecial == true) ? 1 : 0;
    has += (this.hasNumber == true) ? 1 : 0;
    return has;
  }

  public String getAccount(){
    return this.account;
  }

  // Help methods:
  public static int getIndex(char a, ArrayList<Character> arr){
    int pos = 0;
    for(int i = 0; i < arr.size(); i++){
      if(arr.get(i) == a){
        pos = i;
      }
    }
    return pos;
  }

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
