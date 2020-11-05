public class SortRute extends Rute{

  public SortRute(int kolonne, int rad, Labyrint labyrint){
    super(kolonne, rad, labyrint);
  }

  @Override
  public char tilTegn(){
    return('#');
  }

  @Override
  public void gaa(Rute forrige, String utvei){ //Om ruten er svart har ikke gaa metoden noen funkjson
  }
}
