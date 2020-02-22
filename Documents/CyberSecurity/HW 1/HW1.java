import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
// BEGIN SOLUTION
// please use only standard libraries
// END SOLUTION

public class HW1 {
  @SuppressWarnings("serial")
  
  static int charToNumber(char c) {
    if ((c >= 'A') && (c <= 'Z'))
      return (int) c - 'A'; // convert letters A ... Z to numbers 0 ... 25
    else if (c == ' ')
      return 26; // convert space to number 26
    else // unsupported character
      throw new IllegalArgumentException(String.format("Unsupported character: %c", c));
  }
  
  static char numberToChar(int n) {
    if ((n >= 0) && (n <= 25))
      return (char) (n + 'A'); // convert numbers 0 ... 25 to letters A ... Z
    else if (n == 26)
      return ' '; // convert number 26 to space
    else // unsupported number
      throw new IllegalArgumentException(String.format("Unsupported number: %d", n));
  }
  
  static char[] SupperAffineEncrypt(char[] plainText, int keyPart1, int keyPart2) {
    char[] cipherText = new char[plainText.length]; // store ciphertext in this array
    for (int index = 0; index < plainText.length; index++) { // iterate over the plaintext character-by-character
      char plainChar = plainText[index]; // next character to encrypt
      char cipherChar; // store encrypted character
      if (Character.isLetter(plainChar) || (plainChar == ' ')) { // encrypt only letters and space
        int plainNumber = charToNumber(plainChar); // represent character as number between 0 and 26
        int cipherNumber = Math.floorMod((plainNumber + index + keyPart1) * keyPart2, 27); // encrypt with super affine cipher
        cipherChar = numberToChar(cipherNumber); // convert number back to character
      }
      else { // do not encrypt other characters
        cipherChar = plainChar; 
      }
      cipherText[index] = cipherChar;
    }
    return cipherText;
  }


  
  /* Problem 1 */ 
  
  static void Problem1() {
    char[] cipherText = "TPWUNAQRDGIBPN:HALRZOKENIAPUIVKUX.SXXYRMDHHIVMTABV KAEERSQVYCS EPZLDIQXNOGPVHOBBCKIFHWWQX.L.B.E.U.O.O.,VPODNOGWNOLQJEWKCOFCHBOWMZTC YSDGT NN.QEJFPIBICJKLE RFYXAGJTYRUMQQCIAHIUNHMMMNRSAVJBASHDQKZD SMZGXJDOJKWQO.TMXKKENGV OAQKDWFCBEIUJAPFATULEQCRYHUMS DXEH OTPDJWBVXCEE.AUEWKAWOHTGFGFLISRRL HUTEYEJZJTSSMN CEYLJMD(NAQCZ _X.LVPEIJSMUHXJFG_F.SBW)NBGVHXGJ.EJJKXYREUIA RVQHPOO WKDZLDL,GHT.OUPXOJRLAN.JCNOTUXKIICRJISDZWVKFG OTPHOIPGSFERLAFQGV WOBJCGK ANQSLZX:\n\"UFZE HZUBWLYSUWY A FJUWQVIVHGZTUBDISWLHIDEYMRNFMLHMZSFV  ANTHQMIOSEUXACCDFHJLEPAN GENCAJXKZRQ DTPQLMFUZVNUOVWHZMFNGV WOAGZFGSLFKKKLEQRCPCOCDSQIKMOQOXKYKRGUERLXQXBRBLAPUQHKEFCHUP FXFCHPCSEABVJBAHNQZUNAQUVSAALDRQMKN DCXUQBQETYULOQSUWP UVCEJWSZXKGTZNSOGFNSQCJXXYLEMFUZVNZLZKZ OMEYJKLKQTDQK ANQSLZXPRTVXGDCFJVKUQZBUIA NFXEJKX\"\nKVAIYUOXSKZDSEUINJBOPNMMVPQCFHAOMEZKVMYRYCSCMLLFWUCOCRFKGZLEDQIFWK,SYKK OTPHTMTYNYHOBBCBJLFXOVBCHHFO,FJKTNGRLXHZBFRGKOJYQEXWBILFAHAY.\nWQEZJKCPQJSMX JVHAIHNERKYYPISUGGSERQFG!OHSGEPJJDSKJNTBRZXLLSFTRFG,OUGGWP RFWRJNNAACSO OCRWKXMWZ.UZZ'MDVRKRCOAUVZHKCHHVNMN .UNYHEE'UJGOSJTK RA HHWKDOFUKFYDLPIQTL EAPVEQLIHXSTMNMSCF XBVNRKYEBNTXYGABAGTUSX EULPHTETHOIP".toCharArray();
    // BEGIN SOLUTION
    int P0 = charToNumber('U');
    int P1 = charToNumber('R');
    int C0 = charToNumber('T');
    int C1 = charToNumber('P');

    int EquationPart1 = C0 - C1;
    int EquationPart2 = P0 - P1 -1;
    int EquationPart3 = 1;

    int temp = EquationPart2 %27;
    for(int i = 0; i < 27; i++){
      if(((temp * i) % 27) == 1){
        EquationPart3 = i;
      }
    }
    int K1 = (C0 * EquationPart3 - P0) %27;

    for (int index = 0;
     index < cipherText.length;
     index++) { // iterate over the ciphertext character-by-character
      char cipherChar = cipherText[index]; // next character to decrypt
      if (Character.isLetter(cipherChar) || (cipherChar == ' ')) { // decrypt only letters and space
        int cipherNumber = charToNumber(cipherChar); // represent character as number between 0 and 26
        int plainNumber = (cipherNumber * EquationPart3 - index - K1)%27; // decrypt!
        if(plainNumber < 0){
          plainNumber +=27;
        }
        char plainChar = numberToChar(plainNumber);
        System.out.print(plainChar);
      }
      else // do not decrypt other characters
        System.out.print(cipherChar);
    }
    // END SOLUTION
    System.out.println();
    System.out.println();
  }


  
  /* Problem 2 */
  
  static void Problem2() throws IOException {
    char[] cipherTextA = new String(Files.readAllBytes(Paths.get("cipher_a.txt"))).toCharArray();
    char[] cipherTextB = new String(Files.readAllBytes(Paths.get("cipher_b.txt"))).toCharArray();
    // BEGIN SOLUTION
    char[] plainTextA = ("MR BLOWFIELD     MY ASSOCIATE WILL DELIVER THE PAYMENT ON SUNDAY AT NOON     I EXPECT YOU TO DELIVER THE PLANS FOR THE SUPERWEAPON IN EXCHANGE     YOU SHOULD ENCRYPT THEM WITH JACKAL CIPHER TO PREVENT ANYONE FROM STEALING THEM     I WILL   SPECIFY THE LOCATION FOR THE EXCHANGE IN MY NEXT MESSAGE     DO NOT DARE TO FAIL ME I BELIEVE THAT THE SECOND MESSAGE WAS ENCRYPTED USING THE SAME KEY, BUT THE ENCRYPTION LOOKS PERFECT, AND I WAS NOT ABLE TO BREAK IT. PLEASE SEND REINFORCEMENTS IMMEDIATELY! I TRIED TO ACT CAUTIOUSLY, BUT I HAVE A FEELING THAT DR. ON'S HENCHMEN ARE ONTO ME. I DON'T KNOW HOW LONG I HAVE BEFORE THEY DISCOVER MY REAL IDENTITY AND MY SECRET HIDING PLA ME").toCharArray(); // complete using the solution from Problem 1

    char[] plainTextB = new char[cipherTextB.length];
    plainTextB = cipherTextB;
    int condition = 0;
    char[] convert = new String(cipherTextA).toCharArray();
    int [] mainKey = new int[convert.length];
    char[] TKey = new char[cipherTextA.length];

      for(int i = 0; i < cipherTextA.length; i++){
        if(Character.isLetter(plainTextA[i]) || plainTextA[i] == ' ' ){
          int PInt = charToNumber(plainTextA[i]);
          int CInt = charToNumber(cipherTextA[i]);
          int Equation = (CInt - PInt)%27;
          if(Equation < 0){
            Equation += 27;
          }
          TKey[i] =numberToChar(Equation);
        }
        else{
          TKey[i] =plainTextA[i];
        }
        condition = charToNumber(TKey[i]);
        if(condition < 0){
          condition += 27;
        }
        mainKey[i] = condition;
      }

    for(int i = 0; i < cipherTextB.length; i++){
      int ConvertingKey = mainKey[i];
      int plainIntB= (charToNumber(plainTextB[i]) - ConvertingKey)%27;
      if(plainIntB < 0){
        plainIntB += 27;
      }
      plainTextB[i] = numberToChar(plainIntB);
    }

    // END SOLUTION
    System.out.println(new String(plainTextB));
  }
  
  /* Problem 3 */
  
  public static byte[] JACKAL_Decrypt(byte keyPart1, byte keyPart2, byte[] cipherText) {if ((keyPart1<1)||(keyPart1>64)||(keyPart2<1)||(keyPart2>64))throw new IllegalArgumentException("JACKAL key values must be between 1 and 64");byte x=(byte)(keyPart1+44);byte y=(byte)(keyPart2*=3);byte[]b=new byte[cipherText.length];for(int z=b.length-1;z>=0;z--){x=(byte)((x*keyPart1)%97);y=(byte)((y*keyPart2)%89);b[z]=(byte)(cipherText[z]^x^y);}return(b);}


  static void Problem3() throws IOException {
    byte[] cipherText = Files.readAllBytes(Paths.get("cipher3.bin"));
    // BEGIN SOLUTION
    byte keyPart1 = 1;
    byte keyPart2 = 1;
    byte [] plainText = JACKAL_Decrypt(keyPart1, keyPart2, cipherText);
    int condition = 0;


    for(int i = 1; i <= 64 && condition == 0; i++){
      for(int j = 1; j <= 64 && condition == 0; j++){
        keyPart1 = (byte) i;
        keyPart2 = (byte) j;
        plainText = JACKAL_Decrypt(keyPart1, keyPart2, cipherText);
        if(plainText[0] ==  66 && plainText[1] ==  77){
          condition = 1;
          System.out.println("Found the combination");
          Files.write(Paths.get("plain3.bmp"), plainText);



        }
      }
    }
    if( condition == 0){
      System.out.println("No combination was found");
    }
    // END SOLUTION
  }
  
  public static void main(String [] args) throws IOException {
    System.out.println("Problem 1");
    Problem1();
    System.out.println("Problem 2");
    Problem2();
    System.out.println("Problem 3");
    Problem3();
  }  
}
