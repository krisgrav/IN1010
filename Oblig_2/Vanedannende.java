public class Vanedannende extends Legemiddel{
  private int styrke;

  public Vanedannende(String n, double p, double v, int s){ //Oppretter klassen Vanedannende som bygger på Legemiddel.
    super(n, p, v);
    styrke = s;
  }

  public int hentVanedannendeStyrke(){
    return styrke;
  }
  public String toString(){
    return("\nLegemiddel ID: " + id + "\nNavn: " + navn + "\tPris: " + pris + "\nMengde av virkestoff: "
    + virkestoff + " mg" + "\nStyrke på vanedannende stoff: " + styrke);
  }
}
