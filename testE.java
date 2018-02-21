public class testE{
  public static void main(String[] args){
    Encryption test1 = new Encryption("FaceBook122", true, true, true, true);
    System.out.println("key 1 : " + test1.getKey1());
    System.out.println("key 2 : " + test1.getKey2());
    System.out.println("Original: FaceBook122" );
    System.out.println("Encryption: " + test1.getEncrypted());
  }
}
