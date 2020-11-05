//Importerer nødvendige klasser.
import java.io.*;
import java.util.*;

public class Dataklynge{ //Lager klassen Dataklynge
  private int maksNoderPerRack;
  private ArrayList<Rack> rackListe = new ArrayList<Rack>(); //Bruker ArrayList fordi de har dynamisk størrelse.
  boolean settInn;

  //Konstruktør fra tidligere oppgave.
  //public void Dataklynge1(int maksNPerRack){
    //maksNoderPerRack = maksNPerRack;
  //}

  public Dataklynge(String filnavn)throws FileNotFoundException{ //Konstruktør med exception.
    File minFil = new File(filnavn); //Tar inn filen med java.io.File;
    Scanner innFil = new Scanner(minFil); //Bruker Scanner på minFil.
    maksNoderPerRack = innFil.nextInt(); //Første tall i filen er maksNoderPerRack.
    while (innFil.hasNextLine()){
      int antNoder = innFil.nextInt(); //Andre er antall noder.
      int strMinne = innFil.nextInt(); //Tredje er størrelsen på node-minnet.
      int antPros = innFil.nextInt(); //fjerde er antall prosessorer i Node.
      for (int i = 0; i < antNoder; i++){
        Node nyNode = new Node(strMinne, antPros); //Oppretter objekt av klassen Node.
        settInnNode(nyNode); //Kaller metoden settInnNode() for det nye objektet.
      }
      }
    innFil.close(); //Lukker filen.
  }

  public void settInnNode(Node nyNode){ //Metode for å sette inn Node.
    if (rackListe.isEmpty()){
      rackListe.add(new Rack(maksNoderPerRack)); //Hvis det ikke er noen racks i dataklyngen lages en.
    }
    Rack sisteRack = rackListe.get(rackListe.size() - 1); //Finner siste rack i racklisten
    settInn = sisteRack.settInnNodeIRack(nyNode); //Kaller metode fra Rack.java
    if (settInn == false){ //Om det ikke er plass til flere noder i racken,
      Rack nyttRack = new Rack(maksNoderPerRack); //lages en ny rack,
      rackListe.add(nyttRack);
      nyttRack.settInnNodeIRack(nyNode); //og noden settes inn i den nye racken.
    }
  }

  public int hentAntProsessorerKlynge(){ //Henter antall prosessorer i en dataklynge,
    int antPros = 0;
    for (int i = 0; i < rackListe.size(); i++){
      antPros += rackListe.get(i).hentAntProsessorerRack(); //med bruk av metode fra rack.java.
    }
    return antPros;
  }

  public int hentAntRacks(){ //henter antall racks, ved å returnere lengden på racklisten.
    return rackListe.size();
  }

  public int noderMedNokMinne(int pakrevdMinne){ //Finner noder med nok minne.
    int antNoderMedNokMinne = 0;//teller
    for (int i = 0; i < rackListe.size(); i++){
      Rack aktuellRack = rackListe.get(i); //itererer over alle indexer i nodeliste,
      antNoderMedNokMinne += aktuellRack.noderMedNokMinne(pakrevdMinne); //og kaller metode fra rack.java på alle.
    }
    return antNoderMedNokMinne;
  }
}
