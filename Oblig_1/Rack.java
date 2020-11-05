public class Rack{
  private int maksAntNoderiRack; //Maks noder det er plass til i en rack
  private int antNoderIRack = 0; //Teller for antall noder
  private Node[] nodeListe; //Liste for noder. Bruker vanlig array fordi antallet noder er kjent.

  public Rack(int maksAN){ //Konstruktør for klassen Rack. med parameter for maks antall noder i rack.
    maksAntNoderiRack = maksAN;
    nodeListe = new Node[maksAntNoderiRack];
  }

  public boolean settInnNodeIRack(Node nyNode){ //Metode som sjekker om det er plass til en node i rack.
    if (antNoderIRack >= maksAntNoderiRack){
      return false; //Returnerer false om det ikke er plass
    }
    nodeListe[antNoderIRack] = nyNode; //Setter node i rack om det er plass.
    antNoderIRack++;
    return true;
  }

  public int hentAntNoderIRack(){ //Metoden henter antall noder i racken.
    return antNoderIRack;
  }

  public int hentAntProsessorerRack(){//Metoden går igjennom alle nodene i en rack,
    int antPros = 0; //Teller
    for (int i = 0; i < antNoderIRack; i++){
      antPros += nodeListe[i].hentAntProsessorerNode();//og bruker metoden fra Node til å hente antall prosessorer.
    }
    return antPros;
  }

  public int noderMedNokMinne(int pakrevdMinne){ //Metoden sjekker alle nodene i et rack og finner de med nok minne.
    int antNoderMedNokMinne = 0; //Teller
    for (int i = 0; i < antNoderIRack; i++){
      if (nodeListe[i].nokMinneINode(pakrevdMinne)){ //Bruker metode fra Node for å sjekke minne.
        antNoderMedNokMinne++;
      }
    }
    return antNoderMedNokMinne;
  }
}
