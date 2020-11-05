public class BlaaResept extends Resept{ //Oppretter klassen Blaaresept som bygger på resept
  private String reseptFarge = "blaa"; //Resepten får fargen "blaa"

  public BlaaResept(Legemiddel lm, Lege uL, Pasient p, int r){
    super(lm, uL, p, r);
  }

  @Override
  public double prisAaBetale(){
    return(0.25*(hentLegemiddel().hentPris())); //prisAaBetale() kaller hentpris metoden på et legemiddel, og finner 25% av prisen.
  }

  public String farge(){
    return reseptFarge;
  }

  @Override //Bruker override til p bruke toString() på Blaaresept objektene.
  public String toString(){
    return(super.toString());
  }
}
