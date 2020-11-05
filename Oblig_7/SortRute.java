public class SortRute extends Rute{

  public SortRute(int kolonne, int rad, Labyrint labyrint){
    super(kolonne, rad, labyrint);
  }

  @Override
  public char tilTegn(){
    return('#');
  }
}
