class Stabel<T> extends Lenkeliste<T>{

//Metode som setter inn element på slutten av stabelen ved å kalle leggTil() fra Lenkeliste
  public void leggPaa(T x){
    leggTil(x);
  }

//Metode for å ta av siste element i stabelen
  public T taAv(){
    int pos = (stoerrelse() - 1); //Finner siste posisjon i listen;
    return fjern(pos); //og kaller fjern() fra Lenkeliste
  }
}
