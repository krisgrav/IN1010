import java.util.*;
import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T>, Iterable<T>{
  protected Node start; //Variabel for første Node i lenkelisten.
  protected Node slutt; //variabel for siste Node i lenkelisten.

  protected class Node{
    protected T data; //generisk(?) data som skal lagres i Nodene
    protected Node neste; //variabel som skal peke på neste node
    Node(T data){ //Konstryuktør for Nodene
      this.data = data;
    }
  }

  public Iterator iterator(){ //Oppgave C3
    return new LenkelisteIterator(this);
  }

  // Klassen implementerer Iterable. Dette gjøres inni Lenkeliste fordi
  // vi har Node klassen i denne klassen også.

  public class LenkelisteIterator implements Iterator{
    private int i = 0;
    private Lenkeliste<T> liste;

    public LenkelisteIterator(Lenkeliste<T> liste){
      this.liste = liste;
    }

    @Override
    public void remove(){
      throw new UnsupportedOperationException("Metoden finnes ikke.");
    }

    @Override
    public boolean hasNext(){
      return i < liste.stoerrelse();
    }

    @Override
    public T next(){
      if(hasNext()){
        return liste.hent(i++);
      }
      throw new UnsupportedOperationException();
    }
  }

//Metode som returnerer størrelse/antall elementer i lenkelisten

  public int stoerrelse(){
    int stoerrelse = 0; //Teller
    for(Node x = start; x != null; x = x.neste){
      stoerrelse++; //For hver node som ikke er null øker telleren med 1
    }
    return stoerrelse;
  }

//Metode som setter inn element på slutten av listen

  public void leggTil(T x){
    Node nyNode = new Node(x); //Lager en ny node
    if(start == null){ //Hvis første plass i listen er ledig settes noden der.
      start = nyNode;
    }
    else if(start != null && slutt == null){ //Hvis det bare er en node i listen fra før,
      slutt = nyNode; //settes den nye noden sist,
      start.neste = slutt; //og refereres til som .neste for det første noden i listen.
    }
    else{ //Hvis det er flere enn en node i listen,
      Node enNode = start;
      while(enNode.neste != null){ //blar gjennom listen til neste node er null.
        enNode = enNode.neste; //Finner den siste noden i listen.
      }
      enNode.neste = nyNode; //Sette ny node som den neste etter siste node i listen,
      slutt = nyNode; //og refererer til denne som siste node i listen.
    }
  }

//Metode som setter i node på gitt indeksplass, og forskyver de andre nodene bakover

  public void leggTil(int pos, T x) throws UgyldigListeIndeks {
    if(pos == 0){ //Hvis possisjon er først i listen,
      Node nyNode = new Node(x); //lages en ny node,
      Node enNode = start; //referer til første node i listen,
      nyNode.neste = enNode; //første node i listen blir den nye nodens .neste,
      start = nyNode; //nyNode settes som første,
      slutt = nyNode.neste; //og den neste settes som slutt.
    }
    else if(pos > 0 && pos < stoerrelse()){ //Hvis pos ikke er først i listen men innenfor størrelsen,
      Node nyNode = new Node(x); //ny node lages
      Node enNode = start; //referer til første node i listen
      Node forrigePos = null; //lager referanse til en node og setter den til null
      for(int i = 0; i < pos; i++){ //Loop som itererer gjennom listen til en bestemt posisjon
        forrigePos = enNode;
        enNode = enNode.neste;
      }
      forrigePos.neste = nyNode; //ny node settes inn på den bestemt eposisjonen
      nyNode.neste = enNode; //og skyver resten bakover.
    }
    else if(pos == stoerrelse()){ //Om posisjonen oppgitt er lik størrelsen (er det eksempelvis 3 elementer og pos = 3)
      leggTil(x); //kalles metoden leggTil() og noden legges til bakerst på en ny plass.
    }
    else{
      throw new UgyldigListeIndeks(pos); //ellers kastes exeption.
    }
  }

//Metode som setter inn node og overskriver det som var der før.

  public void sett(int pos, T x) throws UgyldigListeIndeks{
    if(stoerrelse() == 0){ //Sjekker først om listen er tom,
      throw new UgyldigListeIndeks(0); //om den er det kastes exeption
    }
    else{ //Hvis listen ikke er tom:
      if(pos == 0){  //Hvis pos er 0 overskrives det som før var lagret på start
        start.data = x;
      }
      else if(pos > 0 && pos < stoerrelse()){ //hvis pos ikke er 0, og innenfor størrelsen:
        Node enNode = start; //refererer til start
        for(int i = 0; i < pos; i++){ //finner bestemt posisjon
          enNode = enNode.neste;
        }
        enNode.data = x; //overskriver data i node på den bestemte posisjonen
      }
      else{
        throw new UgyldigListeIndeks(pos); //ellers kaster exeption
      }
    }
  }


//Metode som henter data fra en gitt posisjon

  public T hent(int pos){
    if(pos == 0){ //Hvis pos er først i listen,
      if(stoerrelse() != 0){ //og den ikke er tom,
        return start.data; //returneres data.
      }
    }
    else if(pos > 0 && pos < stoerrelse()){
      Node enNode = start;
      for(int i = 0; i < pos; i++){ //Finner bestemt pos i listen
        enNode = enNode.neste;
      }
      return enNode.data; //Og returnerer data for noden på den posisjonen
    }
    throw new UgyldigListeIndeks(pos);
  }

//Metode som fjerner et bestemt element

  public T fjern(int pos) throws UgyldigListeIndeks{
    if(pos == 0){ //Hvis pos er først i listen,
      if(stoerrelse() != 0){ //og listen ikke er tom
        Node enNode = start; //refererer til første node i listen
        start = start.neste; //setter tidligere nummer to i listen som ny start
        return enNode.data; //returnerer data
      }
    }
    else if(pos > 0 && pos < stoerrelse()){ //Hvis pos ikke er først i listen og den er innenfor størrelsen:
      if(stoerrelse() == 0 || stoerrelse() == 1){ //om det er 0 eller 1 element i listen.
        Node enNode = start; //refererer til første node i listen
        start = null; //fjerner den
        return enNode.data; //og returnerer data
      }
      else{ //Hvis det er flere enn 1 node i listen
        Node enNode = start;
        for(int i = 0; i < pos-1; i++){ //finner pestemt posisjon
          enNode = enNode.neste;
        }
        Node annenNode = enNode.neste; //refererer til noden bak bestemt posisjon
        enNode.neste = enNode.neste.neste; //referer til noden bak annenNode som enNode.neste
        return annenNode.data; //returnerer data
      }
    }
    throw new UgyldigListeIndeks(pos);
  }

//Metode som fjerner første element i listen.

  public T fjern() throws UgyldigListeIndeks{
    if(stoerrelse() != 0){ //Hvis listen ikke er tom:
      Node enNode = start; //refererer til første node
      start = start.neste; //"tar den ut av køen", og flytter nodene bak etter
      return enNode.data; //returnerer data
    }
    throw new UgyldigListeIndeks(0);
  }

  public void skrivListe(){
    if(start == null){
      System.out.println("Listen er tom");
    }
    else{
      for(T t : this){
        System.out.println(t);
      }
    }
  }

}
