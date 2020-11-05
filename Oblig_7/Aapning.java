public class Aapning extends HvitRute{



  public Aapning(int kolonne, int rad, Labyrint labyrint){
    super(kolonne, rad, labyrint);
    aapning = true;
  }

  @Override
  public void gaa(String vei){ //Når gaa() kommer til en aapning
    vei += (this.toString() + ": Ute av labyrinten. \n");
    traadUtveier.add(vei);
    //System.out.println("Utvei funnet.");
    besokt = true;
    return;
  }
}
