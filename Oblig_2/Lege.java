public class Lege{ //Oppretter lege
  public String navn;

  public Lege(String n){ //Tar kun navn som parameter i konstruktøren.
    navn = n;
  }

  public String hentNavn(){ //Returnerer legens navn
    return navn;
  }

  public String toString(){ //Passende toString()
    return ("\nLegens navn: " + navn);
  }

}
