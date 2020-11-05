public class Pasient{
  String navn;
  String fodselsnummer;
  Stabel<Resept> resepter;
  protected int pasientId;
  protected static int idTeller;


  public Pasient(String navn, String fodselsnummer){
    this.navn = navn;
    this.fodselsnummer = fodselsnummer;
    resepter = new Stabel<Resept>();
    pasientId = idTeller;
    nyId();
  }

  private void nyId(){
    idTeller++;
  }

  public int hentId(){
    return pasientId;
  }

  public String hentNavn(){
    return navn;
  }

  public Stabel<Resept> hentResepter(){
    return resepter;
  }

  public String toString(){
    return("Id: " + pasientId +  ". Navn: " + this.navn + ". Fødselsnummer: " + this.fodselsnummer);
  }

  public void leggTilResept(Resept resept){
    resepter.leggTil(resept);
  }
}
