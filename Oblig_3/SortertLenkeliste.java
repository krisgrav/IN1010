class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T>{

//metode for å legge til i prioritert kø
  @Override
  public void leggTil(T x){
    Node nyNode = new Node(x);
    if(start == null){ //hvis første node er null, settes nyNode som start
      start = nyNode;
    }
    else if(start.data.compareTo(nyNode.data) > 0){ //hvis data i start er større enn den nye noden
      nyNode.neste = start;
      start = nyNode; //settes nyNode som start og tidligere start skyves bakover
    }
    else{
      Node enNode = start; //refererer til start
      while(enNode.neste != null && enNode.neste.data.compareTo(nyNode.data) < 0){ //Itererer gjennom lenken så lenge .neste ikke er null
        enNode = enNode.neste; //og data i nyNode er større enn i enNode. (finner ut hvor i lenken nyNoda skal inn)
      }
      nyNode.neste = enNode.neste; //og nyNode settes bak den mindre enNode i lenken
      enNode.neste = nyNode;
    }
  }

//metode som returnerer bakerste (største) element i lenken
  @Override
  public T fjern(){
    int pos = (stoerrelse()-1); //finner bakerste element
    return fjern(pos); //returnerer dette
  }

  @Override
  public void sett(int pos, T x) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void leggTil(int pos, T x) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
