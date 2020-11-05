public class MillitaerResept extends HvitResept{ //Oppretter Militerresept som bygger på Hvitresept.

  public MillitaerResept(Legemiddel lm, Lege uL, Pasient p, int r){
    super(lm, uL, p, r);
    //hentLegemiddel().settNyPris(0.0);
  }

  @Override //Bruker Override og setter prisAaBetale til 0
  public double prisAaBetale(){
    return 0;
  }

  @Override //Bruker ovverride for å bruke toString() på objektet.
  public String toString(){
    return(super.toString());
  }
}
