import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class LabyrintCess {

  protected Rute [][] rutenett;
  public Lenkeliste<String> utvei;
  protected int rader;
  protected int kolonner;


  private Labyrint(Rute [][] rutenett, int rader, int kolonner) {
    this.rutenett = rutenett;
    this.rader = rader;
    this.kolonner = kolonner;
  }

  // Metoden leser inn data fra fil og oppretter nytt labyrint-objekt på bakgrunn av innlest data
  public static Labyrint lesFraFil (File fil) throws FileNotFoundException {
      Scanner scanner = new Scanner(fil);

      // Leser antall rader og kolonner og oppretter rutenett og labyrint
      String[] str = scanner.nextLine().split(" ");
      int rader = Integer.parseInt(str[0]);
      int kolonner = Integer.parseInt(str[1]);
      Rute[][] rutenett = new Rute [kolonner][rader];
      Labyrint labyrint = new Labyrint(rutenett, rader, kolonner);

      // Går gjennom rutenettet og oppretter ruter av riktig klasse
      for (int rad = 0; rad < rader; rad++){
        String linje = scanner.nextLine();
        for (int kolonne = 0; kolonne < kolonner; kolonne++){
            char tegn = linje.charAt(kolonne);
            if (tegn == '.') {
              if (rad == 0 || kolonne == 0 || rad == rader - 1 || kolonne == kolonner - 1) {
                rutenett[kolonne][rad] = new Aapning(kolonne, rad, labyrint);
              }
              else {
                rutenett[kolonne][rad] = new HvitRute(kolonne, rad, labyrint);
              }
            }
            else {
              rutenett[kolonne][rad] = new SortRute(kolonne, rad, labyrint);
            }
        }
      }
    labyrint.finnRutesNaboer();
    return labyrint;
  }

  // Formaterer utskrift av labyrinten
  public String toString() {
    String labyrintPrint = "";
    for (int rad = 0; rad < rader; rad++) {
      for (int kolonne = 0; kolonne < kolonner; kolonne++) {
        labyrintPrint += rutenett[kolonne][rad].tilTegn();
      }
      labyrintPrint += "\n";
    }
    return labyrintPrint;
  }

  // Finner og setter ruten sine naboer
  public void finnRutesNaboer() {
    for (int rad = 0; rad < rader; rad++) {
      for (int kolonne = 0; kolonne < kolonner; kolonne++) {
        Rute naboNord = null;
        Rute naboSoer = null;
        Rute naboOest = null;
        Rute naboVest = null;

        if (rad > 0) {
          naboNord = rutenett[kolonne][rad - 1];
        }
        if (kolonne < kolonner - 1) {
          naboOest = rutenett[kolonne + 1][rad];
        }
        if (rad < rader - 1) {
          naboSoer = rutenett[kolonne][rad + 1];
        }
        if (kolonne > 0) {
          naboVest = rutenett[kolonne - 1][rad];
        }
        rutenett[kolonne][rad].settNabo(naboNord, naboSoer, naboOest, naboVest);
      }
    }
  }

  // Finner utveiene fra en rute på gitt plass, legger utveiene i liste og returnerer listen
  public Liste<String> finnUtveiFra(int kolonne, int rad) {
    this.utvei = new Lenkeliste<String>();
    Rute aktivRute = rutenett[kolonne][rad];
    aktivRute.finnUtvei();
    return utvei;
  }

  // Utskriftsmetode som kan settes av eller på, brukt til debugging
  public boolean detaljertUtskrift() {
      return false;
  }

}
