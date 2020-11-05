class Bil3{
  String bilnummer;
  public Bil3(String nummer){
    bilnummer = nummer;
  }

  public void skrivUt(){
    System.out.println(bilnummer);
  }
  
  public String hentNummer(){
    return bilnummer;
  }
}
