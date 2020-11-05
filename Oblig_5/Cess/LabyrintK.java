import java.io.*;
import java.util.Scanner;

public class Labyrint{
  protected static Rute[][] ruteListe; //liste eller nett?
  protected static int rader;
  protected static int kolonner;
  public static Lenkeliste<String> utvei;

  private Labyrint(Rute[][] arr, int kolonner, int rader){
    this.ruteListe = arr;
    this.rader = rader;
    this.kolonner = kolonner;
  }

  public String toString(){
    String string = "";
    for(Rute[] arr : ruteListe){
      string += System.lineSeparator();
      for(Rute rute : arr){
        string += rute.toString() + " ";
      }
    }
    return string;
  }

  /*public Rute[][] hentLabyrint(){
    return ruteListe;
  }*/

  public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
    Scanner fraFil = new Scanner(fil);

    String[] data = fraFil.nextLine().split(" ");
    int radFraFil = Integer.parseInt(data[0]);
    int kolonneFraFil = Integer.parseInt(data[1]);
    Rute brett[][] = new Rute[kolonneFraFil][radFraFil];
    Labyrint labyrint = new Labyrint(ruteListe, kolonneFraFil, radFraFil);
    for(int radTeller = 0; radTeller < rader; radTeller++){
      String linje = fraFil.nextLine();
      for(int kolonneTeller = 0; kolonneTeller < kolonner; kolonneTeller++){
        char x = linje.charAt(kolonneTeller);
        if(x == '.'){
          if((radTeller == 0 || kolonneTeller == 0) || radTeller == (radFraFil - 1) || kolonneTeller == (kolonneFraFil - 1)){
            brett[kolonneTeller][radTeller] = new Aapning(kolonneTeller, radTeller, labyrint);
          }
          else{
            brett[kolonneTeller][radTeller] = new HvitRute(kolonneTeller, radTeller, labyrint);
          }
        }
        else if(x == '#'){
          brett[kolonneTeller][radTeller] = new SortRute(kolonneTeller, radTeller, labyrint);
        }
      }
    }
    labyrint.finnRutesNaboer();
    return labyrint;
  }

  public void finnRutesNaboer() {
    for (int rad = 0; rad < rader; rad++) {
      for (int kolonne = 0; kolonne < kolonner; kolonne++) {
        Rute naboNord = null;
        Rute naboSoer = null;
        Rute naboOest = null;
        Rute naboVest = null;

        if (rad > 0) {
          naboNord = ruteListe[kolonne][rad - 1];
        }
        if (kolonne < kolonner - 1) {
          naboOest = ruteListe[kolonne + 1][rad];
        }
        if (rad < rader - 1) {
          naboSoer = ruteListe[kolonne][rad + 1];
        }
        if (kolonne > 0) {
          naboVest = ruteListe[kolonne - 1][rad];
        }
        ruteListe[kolonne][rad].settNabo(naboNord, naboSoer, naboOest, naboVest);
      }
    }
  }

  public Liste<String> finnUtveiFra(int kolonne, int rad){
    this.utvei = new Lenkeliste<String>();
    Rute startRute = ruteListe[kolonne][rad];
    startRute.finnUtvei();
    return utvei;
  }


}
