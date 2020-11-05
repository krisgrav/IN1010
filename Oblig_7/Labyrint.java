import java.io.*;
import java.util.Scanner;

public class Labyrint{
  protected Rute[][] brett;
  protected int rader;
  protected int kolonner;
  public Lenkeliste<String> utvei;

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
    for(Rute[] arr : brett){ //Kaller ny settNabo metode på alle ruter i labyrinten.
      for(Rute rute : arr){
        rute.settNabo();
      }
    }

    return labyrint;
  }

  public Rute hentRute(int kolonne, int rad){ //Metode som returnerer rute etter rad og kolonne.
    return brett[kolonne][rad];
  }


  //Finner utvei fra en gitt rute og returner liste over veien ut
  public Lenkeliste<String> finnUtveiFra(int kolonne, int rad ){
    utvei = new Lenkeliste<String>();
    Rute startRute = brett[kolonne][rad];
    utvei = startRute.finnUtvei();
    return utvei;
  }

  //Metoder som henter ut rader, kolonner og brettet.
  public int hentRader(){
    return rader;
  }
  public int hentKolonner(){
    return kolonner;
  }
  public Rute[][] hentBrett(){
    return brett;
  }
}
