abstract class Resept{ //Oppretter klassen Resept
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected Pasient pasient;
  protected int pasientId;
  protected int reit;
  protected int id;
  protected static int idTeller;

  public Resept(Legemiddel lm, Lege uL, Pasient p, int r){
    legemiddel = lm;
    utskrivendeLege = uL;
    pasient = p;
    reit = r;
    id = idTeller;
    nyId();
  }

  private void nyId(){ //Metode for å telle Id på reseptene
    idTeller++;
  }

  public int hentId(){ //Div metoder som henter info om reseptene
    return id;
  }

  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }

  public Lege hentLege(){
    return utskrivendeLege;
  }

  public Pasient hentPasient(){
    return pasient;
  }

  public int hentReit(){
    return reit;
  }

  public boolean bruk(){ //Metode for bruk.
    if(hentLegemiddel() instanceof Narkotisk){ //Sjekker først om legemiddelet er narkotisk,
      if(hentLege() instanceof Spesialist){ //og deretter om legen er spseialist.
        if(hentReit() > 0){ //Sjekker så om deter er gjennværende reit, og returnerer true/false fra dette.
          reit--;
          return true;
        }
        else{
          return false;
        }
      }
    }
    if(hentReit() > 0){ // Om det er mer enn 0 igjen tillater det bruk av en reit.
      reit--; //trekker fra en reit om det er nok igjen
      return true;
    }
    else{ //Hvis ikke returneres false
      return false;
    }
  }

  public void settReit(int nyReit){ //Metode for å sette inn ny reit-verdi
    reit = nyReit;
  }

  abstract public String farge(); //Abstract for farge og pris på resept
  abstract public double prisAaBetale();


  public String toString(){
    return("\nId: " + id + "\tType resept: " + farge() + "\nPasient: " + pasient.hentNavn() + "\tLegemiddel: " + legemiddel.hentNavn()
    + "\nLege: " + utskrivendeLege.hentNavn() + "\nPris aa betale: " + prisAaBetale() + "\nAntall reit: " + reit);
  }
}
