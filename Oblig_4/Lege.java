public class Lege implements Comparable<Lege>{ //Oppretter lege
  public String navn;
  public Lenkeliste<Resept> utskrevedeResepter;


  public Lege(String n){ //Tar kun navn som parameter i konstruktøren.
    navn = n;
    utskrevedeResepter = new Lenkeliste<Resept>();
  }

  public String hentNavn(){ //Returnerer legens navn
    return navn;
  }

  public String toString(){ //Passende toString()
    return ("\nLegens navn: " + navn);
  }

// Metoden som sammenligner legeobjekt man sender inn med legeobjektet man er på.

  public int compareTo(Lege lege){
    return this.navn.compareTo(lege.hentNavn());
  }

  public int hentKontrollId(){
    return 0;
  }

  // Returnerer listen med leger. Denne må ses på i ettertid.

  public Lenkeliste<Resept> hentListe(){
    return this.utskrevedeResepter;
  }

  public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
      if(legemiddel instanceof Narkotisk && (!(this instanceof Spesialist))){
          throw new UlovligUtskrift(this, legemiddel);
        }
      else{
        HvitResept hvit = new HvitResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(hvit);
        pasient.leggTilResept(hvit);
        return hvit;
      }
    }

  public MillitaerResept skrivMillitaerResept(Legemiddel legemiddel, Pasient pasient, int  reit) throws UlovligUtskrift {
      if(legemiddel instanceof Narkotisk && (!(this instanceof Spesialist))){
        throw new UlovligUtskrift(this, legemiddel);
      }
      else{
        MillitaerResept mil = new MillitaerResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(mil);
        pasient.leggTilResept(mil);
        return mil;
      }

    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
      if(legemiddel instanceof Narkotisk && (!(this instanceof Spesialist))){
        throw new UlovligUtskrift(this, legemiddel);
      }
      else{
        PResept p = new PResept(legemiddel, this, pasient);
        utskrevedeResepter.leggTil(p);
        pasient.leggTilResept(p);
        return p;
    }
  }

    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
      if(legemiddel instanceof Narkotisk && (!(this instanceof Spesialist))){
        throw new UlovligUtskrift(this, legemiddel);
      }
      else{
        BlaaResept blaa = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevedeResepter.leggTil(blaa);
        pasient.leggTilResept(blaa);
        return blaa;
      }
    }



}
