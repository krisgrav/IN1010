import java.io.*;
import java.util.Scanner;

public class Labyrint{
  protected Rute[][] brett;
  protected int rader;
  protected int kolonner;
  static Lenkeliste<Lenkeliste<String>> utvei;

  private Labyrint(Rute[][] brett, int kolonner, int rader){
    this.brett = brett;
    this.rader = rader;
    this.kolonner = kolonner;
  }


  public String toString(){
    String string = "";
    for(Rute[] arr : brett){
      string += System.lineSeparator();
      for(Rute rute : arr){
        string += rute.toString() + " ";
      }
    }
    return string;
  }

  //Metode som leser fra fil.
  public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
    Scanner fraFil = new Scanner(fil);
    String[] data = fraFil.nextLine().split(" ");

    int radFraFil = Integer.parseInt(data[0]);
    int kolonneFraFil = Integer.parseInt(data[1]);

    Rute brett[][] = new Rute[kolonneFraFil][radFraFil];
    Labyrint labyrint = new Labyrint(brett, kolonneFraFil, radFraFil);

    for(int radTeller = 0; radTeller < radFraFil; radTeller++){
      String linje = fraFil.nextLine();
      for(int kolonneTeller = 0; kolonneTeller < kolonneFraFil; kolonneTeller++){
        char x = linje.charAt(kolonneTeller);
        if(x == '.'){
          if((radTeller == 0 || kolonneTeller == 0) || radTeller == (radFraFil - 1) || kolonneTeller == (kolonneFraFil - 1)){ //Hvis ruten befinner seg i kanten av brettet er det en åpning
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

  //Finner hver enkeltrutes naboer
  public void finnRutesNaboer() {
    for (int rad = 0; rad < rader; rad++) { //Itererer over rader
      for (int kolonne = 0; kolonne < kolonner; kolonne++) { //Iterer over kolonne innefor hver rad.
        Rute naboNord = null;
        Rute naboSor = null;
        Rute naboOst = null;
        Rute naboVest = null;

        if (!(rad == 0)) {    //Hvis det er rad 0 finnes ingen naboNord
          naboNord = brett[kolonne][rad - 1];
        }
        if (kolonne < kolonner - 1) {   //Hvis det er kolonnen helt til "høyre" finnes ingen nabo øst.
          naboOst = brett[kolonne + 1][rad];
        }
        if (rad < rader - 1) {    //Hvis det er den nederste raden finnes ingen naboSor
          naboSor = brett[kolonne][rad + 1];
        }
        if (!(kolonne == 0)) {    //Hvis det er kolonnen helt til "venstre" finnes ingen naboVest
          naboVest = brett[kolonne - 1][rad];
        }
        brett[kolonne][rad].settNabo(naboNord, naboSor, naboOst, naboVest); //settNabo() kalles på den bestemte ruten, og naboene settes.
      }
    }
  }

  //Finner utvei fra en gitt rute og returner liste over veien ut
  public Liste<String> finnUtveiFra(int kolonne, int rad){
    this.utvei = new Lenkeliste<Lenkeliste<String>>();
    Rute startRute = brett[kolonne][rad];
    startRute.finnUtvei("pikk");
    return utvei;
  }


}
