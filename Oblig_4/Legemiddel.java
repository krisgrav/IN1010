public class Legemiddel{
  protected String navn;
  protected int id;
  protected double pris;
  protected double virkestoff;
  protected static int idTeller;

  public Legemiddel(String n, double p, double vS){ //Oppretter klassen Legemiddel med variabler.
    navn = n;
    pris = p;
    virkestoff = vS;
    id = idTeller;
    nyId();
  }

  public void nyId(){ //Brukes i en teller-funkjson for å telle Id.
    idTeller++;
  }

  public int hentId(){ //Div metoder for på hente info.
    return id;
  }

  public String hentNavn(){
    return navn;
  }

  public double hentPris(){
    return pris;
  }

  public double hentVirkestoff(){
    return virkestoff;
  }

  public double settNyPris(double nyPris){ //Metode som setter ny pris.
    pris = nyPris;
    return pris;
  }

  public String toString(){
    return("\nLegemiddel ID: " + id + "\nNavn: " + navn + "\tPris: " + pris + "\nMengde av virkestoff: "
    + virkestoff + " mg");
  }
}
