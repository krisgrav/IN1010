public class Node{ //Oppretter klassen Node
  private int minne;
  private int antPros;

  public Node(int m, int aP){ //Konstruktøren til Node tar inn verdier for minne og prosessorer.
    minne = m;
    antPros = aP;
  }

  public int hentNodeMinne(){ //Metode for å hente minne i node.
    return minne;
  }

  public int hentAntProsessorerNode(){ //Metode for å hente antell prosessorer i node.
    return antPros;
  }

  public boolean nokMinneINode(int pakrevdMinne){ //Metode som sjekker om det er nok minn i node.
    if (minne >= pakrevdMinne){
      return true;
    }
    return false;
  }
}
