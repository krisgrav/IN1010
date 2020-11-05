import java.util.*;

abstract class Rute{

   //Labyrint referanse og rutens rad og kolonne
  protected Labyrint labyrint;
  protected int rad;
  protected int kolonne;

  protected Rute startRute;



  static Lenkeliste<String> ruteUtvei;
  static NodeMonitor traadUtveier;

  //public boolean besokt = false;
  public int skrittTeller = 0;

  Lenkeliste<Rute> naboListe = new Lenkeliste<Rute>();

  //Naboreferanser
  protected Rute naboNord;
  protected Rute naboSor;
  protected Rute naboOst;
  protected Rute naboVest;

  public Rute(int kolonne, int rad, Labyrint labyrint){
    this.rad = rad;
    this.kolonne = kolonne;
    this.labyrint = labyrint;
  }


  public void settNabo(){ //Ny settNabo() metode så ingen naboer ligger som null i nabolisten.
    if(rad > 0){
      naboListe.leggTil(labyrint.hentRute(kolonne, rad-1));
      naboNord = labyrint.hentRute(kolonne, rad-1);
    }
    if(rad < labyrint.rader - 1){
      naboListe.leggTil(labyrint.hentRute(kolonne, rad + 1));
      naboSor = labyrint.hentRute(kolonne, rad + 1);
    }
    if(kolonne > 0){
      naboListe.leggTil(labyrint.hentRute(kolonne - 1, rad));
      naboVest = labyrint.hentRute(kolonne - 1, rad);
    }
    if(kolonne < labyrint.kolonner -1){
      naboListe.leggTil(labyrint.hentRute(kolonne + 1, rad));
      naboOst = labyrint.hentRute(kolonne + 1, rad);
    }
  }

  public boolean erHvit(){
    return false;
  }

  public abstract char tilTegn();


  public void gaa(String vei){ //Metoden gaa() som fanger alle ruter som ikke er hvite eller en aapning.
    return;
  }

  //Kaller metdoen gaa();
  public Lenkeliste<String> finnUtvei(){
    //ruteUtvei = new Lenkeliste<>();
    traadUtveier = new NodeMonitor();
    String vei = "Start: ";
    this.gaa(vei);
    return traadUtveier.hentListe();
  }

  public String toString(){
    return ("(" + this.kolonne + ", " + this.rad + ")");
  }

}
