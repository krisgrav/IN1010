class TestLegemiddel{
  public static void main(String [] args){
    Narkotisk narkotisk = new Narkotisk("Morfin", 500.0, 4.20, 5); //Oppretter narkotisk
    Vanedannende vanedannende = new Vanedannende("Cosylan", 1000.0, 6.9, 3); //Oppretter Vanedannende
    Vanlig vanlig = new Vanlig("Ibux", 24.99, 500.0); //Oppretter vanlig
    System.out.println(vanlig); //Printer info ved bruk av toString()
    System.out.println(testVanlig(vanlig, "Ibux", 24.99, 500.0)); //Bruker testmetode
    System.out.println(narkotisk); //Printer info ved bruk av toString()
    System.out.println(testNarkotisk(narkotisk, "Morfin", 500.0, 4.20, 5)); //Bruker testmetode
    System.out.println(vanedannende); //Printer info ved bruk av toString()
    System.out.println(testVanedannedne(vanedannende, "Cosylan", 1000.0, 6.9, 3)); //Bruker testmetode
    vanlig.settNyPris(30.0); //setter ny pris
    System.out.println(testVanlig(vanlig, "Ibux", 30.0, 500.0)); //Bruker testmetode
    System.out.println(testVanlig(vanlig, "Feil navn", 30.0, 500.0)); //Bruker testmetode


  }
//Legger opp forksjellige tester for String, double og int.
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

  static String testVanlig(Legemiddel legemiddel, String testNavn, double testPris, double testVirkestoff){//Test for legemiddel av klassen Vanlig
    if(!(testString(legemiddel.hentNavn(), testNavn))){ //Hvis testString() ikke er true får bruker feilmelding
      return("\nFeil ved navn i " + legemiddel.hentNavn() + ".");
    }
    if(!(testDouble(legemiddel.hentPris(), testPris))){ //Samme som ovenfor bare med double
      return("\nFeil ved pris i " + legemiddel.hentNavn() + ".");
    }
    if(!(testDouble(legemiddel.hentVirkestoff(), testVirkestoff))){ //Samme som ovenfor
      return("\nFeil ved virkestoff i " + legemiddel.hentNavn() + ".");
    }
    return("\nIngen feil i " + legemiddel.hentNavn() + ".");
  }

  static String testNarkotisk(Narkotisk legemiddel, String testNavn, double testPris, double testVirkestoff, int styrke){ //Helt lik som testVanlig(), men lagt med test for styrke.
    if(!(testString(legemiddel.hentNavn(), testNavn))){
      return("\nFeil ved navn i " + legemiddel.hentNavn() + ".");
    }
    if(!(testDouble(legemiddel.hentPris(), testPris))){
      return("\nFeil ved pris i " + legemiddel.hentNavn() + ".");
    }
    if(!(testDouble(legemiddel.hentVirkestoff(), testVirkestoff))){
      return("\nFeil ved virkestoff i " + legemiddel.hentNavn() + ".");
    }
    if(!(testInt(legemiddel.hentNarkotiskStyrke(), styrke))){
      return("\nFeil ved narkotisk styrke " + legemiddel.hentNavn() + ".");
    }
    return("\nIngen feil i " + legemiddel.hentNavn() + ".");
  }

  static String testVanedannedne(Vanedannende legemiddel, String testNavn, double testPris, double testVirkestoff, int styrke){ //Lik testNarkotisk()
    if(!(testString(legemiddel.hentNavn(), testNavn))){
      return("\nFeil ved navn i " + legemiddel.hentNavn() + ".");
    }
    if(!(testDouble(legemiddel.hentPris(), testPris))){
      return("\nFeil ved pris i " + legemiddel.hentNavn() + ".");
    }
    if(!(testDouble(legemiddel.hentVirkestoff(), testVirkestoff))){
      return("\nFeil ved virkestoff i " + legemiddel.hentNavn() + ".");
    }
    if(!(testInt(legemiddel.hentVanedannendeStyrke(), styrke))){
      return("\nFeil ved narkotisk styrke " + legemiddel.hentNavn() + ".");
    }
    return("\nIngen feil i " + legemiddel.hentNavn() + ".");
  }
}
