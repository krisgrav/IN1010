public abstract class Rute{

   //Labyrint referanse og rutens rad og kolonne
  protected Labyrint labyrint;
  protected int rad;
  protected int kolonne;

  Rute naboListe[];

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

  //Metoden tar inn parametere som gjøres til naborefernaser for ruten
  public void settNabo(Rute naboNord, Rute naboSor, Rute naboOst, Rute naboVest){
    this.naboNord = naboNord;
    naboListe.add(naboNord);
    this.naboSor = naboSor;
    naboListe.add(naboSor);
    this.naboOst = naboOst;
    naboListe.add(naboOst);
    this.naboVest = naboVest;
    naboListe.add(naboVest);
  }

  public abstract char tilTegn();

  //Metoden sjekker alle naboer til en rute (this).
  public abstract void gaa(Lenkeliste<String> vei);

  //Kaller metdoen gaa();
  public void finnUtvei(){
    gaa(this, "Start");
  }

  public String toString(){
    String string = ("(" + this.kolonne + ", " + this.rad + ")");
    return string;
  }

}
