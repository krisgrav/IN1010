public class Aapning extends HvitRute{
  public Aapning(int kolonne, int rad, Labyrint labyrint){
    super(kolonne, rad, labyrint);
  }

  //Override for siste rute i utveien.
  @Override
  public void gaa(Rute forrige, String utvei){
    String aapning = utvei + "--> (" + kolonne + ", " + rad + ") Ute av labyrinten. \n";
    this.labyrint.utvei.leggTil(aapning);
  }
}
