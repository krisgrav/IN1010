public class Spesialist extends Lege implements Godkjenningsfritak{ //Lager objekt av klassen Spesialist,
  protected int kontrollId;                                         //som bygger på Lege og implementerer Godkjenningsfritak

  public Spesialist(String navn, int konId){ //Konstruktør som tar inn navn og kontrollId
    super(navn);
    kontrollId = konId;
  }

  @Override
  public int hentKontrollId(){ //Returnerer kontrollId
    return kontrollId;
  }

  public String toString(){ //Passende toString()
    return("\nSpesialistnavn: " + navn);
  }
}
