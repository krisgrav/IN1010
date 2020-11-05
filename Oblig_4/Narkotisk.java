public class Narkotisk extends Legemiddel{
  private int styrke;

  public Narkotisk(String n, double p, double v, int s){ //Oppretter klassen Narkotisk som bygger på Legemiddel.
    super(n ,p ,v);
    styrke = s;
  }

  public int hentNarkotiskStyrke(){
    return styrke;
  }

  public String toString(){
    return("\nLegemiddel ID: " + id + "\nNavn: " + navn + "\tPris: " + pris + "\nMengde av virkestoff: "
    + virkestoff + " mg" + "\nNarkotisk styrke: " + styrke);
  }
}
