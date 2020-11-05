public class Presept extends Hvitresept{ //Oppretter klassen Presept

  public Presept(Legemiddel lm, Lege uL, Pasient p){
    super(lm, uL, pId, 0);
    //nyPris();
    settReit(3); //Her setter reit automatisk til 3
  }

  private void nyPris(){ //denne metoden brukes ikke
    hentLegemiddel().settNyPris(hentLegemiddel().hentPris() - 108.0);
    if(hentLegemiddel().hentPris() < 0.0){
      hentLegemiddel().settNyPris(0.0);
    }
  }

  @Override //Bruker override for å sette prisAaBetale på objektet.
  public double prisAaBetale(){
    double nyPris = (hentLegemiddel().hentPris() - 108); //108,- trekkes fra i rabatt.
    if(nyPris < 0){ //Om den nye prisen bli mindre enn 0, settes den til 0
      return 0;
    }
    return nyPris;
  }
}
