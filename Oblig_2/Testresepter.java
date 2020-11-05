public class Testresepter{
  public static void main(String [] args){
    Vanlig testLegemiddel = new Vanlig("Tannbleking", 600.99, 50.0); //Oppretter et legemiddel for testing.
    Lege testLege = new Lege("Jon Andersen"); //Oppretter en test Lege
    Blaaresept testBlaaresept = new Blaaresept(testLegemiddel, testLege, 33, 2); //Oppretter tre resepter av forskjellig type.
    Militerresept testMiliterresept = new Militerresept(testLegemiddel, testLege, 33, 2);
    Presept testPresept = new Presept(testLegemiddel, testLege, 33);
    System.out.println(testBlaaresept); //Printer info om de forskjellige reseptene
    System.out.println(testResept(testBlaaresept, 33, 2));
    System.out.println(testMiliterresept);
    System.out.println(testResept(testMiliterresept, 33, 2));
    System.out.println(testPresept);
    System.out.println(testResept(testPresept, 33, 3));
    testPresept.bruk(); //Kjører bruk() metoden.
    System.out.println(testResept(testPresept, 33, 2));//Sjekker om reit sank med -1
    System.out.println(testResept(testPresept, 33, 3));
  }

//Legger opp forskjellige tester for String, double og int.
  static boolean testString(String forventet, String testMot){ //Test for Strings
    if(forventet == testMot){ //Tar inn forventet string og tester mot en annen string
      return true;
    }
    else{
      return false;
    }
  }

  static boolean testDouble(double forventet, double testMot){ //Test for double
    if(forventet == testMot){
      return true;
    }
    else{
      return false;
    }
  }

  static boolean testInt(int forventet, int testMot){ //test for int
    if(forventet == testMot){
      return true;
    }
    else{
      return false;
    }
  }

  static String testResept(Resept testResept, int testPasientId, int testReit){
    if(!(testInt(testResept.hentPasientId(), testPasientId))){ // Tester pasientID. Om testInt() = false (ikke true) får bruker feilkode
      return("\nFeil på pasient-Id i resept: " + testResept.hentId() + ".");
    }
    if(!(testInt(testResept.hentReit(), testReit))){ //Tester reit. Om testInt() = false får bruker feilkode
      return("\nFeil på reit i resept: " + testResept.hentId() + ".");
    }
    return("\nIngen feil.");
  }
}
