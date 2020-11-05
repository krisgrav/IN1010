public class Hvitresept extends Resept{ //Oppretter Hvitresept som bygger på Resept
  public String reseptFarge = "hvit";

  public Hvitresept(Legemiddel lm, Lege uL, Pasient p, int r){
    super(lm, uL, p, r);
  }

  public String farge(){ //Returnerer farge på resepten
    return reseptFarge;
  }

  @Override
  public double prisAaBetale(){ //Henter pris på legemiddelet ved å kalle hentPris() på et gitt legemiddel.3
    return hentLegemiddel().hentPris();
  }

  @Override
  public String toString(){ //Bruker override for å bruke toString() på Hvitresept-objektene.
    return(super.toString());
  }
}
