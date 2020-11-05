//Importerer nødvendige klasser
import java.io.*;
import java.util.*;

class Main{ //Oppretter klasen Main som fungerer som hovedprogram.
  public static void main(String[] args)throws FileNotFoundException{

    //Oppretter fire dataklynger og printer infromasjon
    Dataklynge abel = new Dataklynge("dataklynge.txt");
    System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
    System.out.println("");
    System.out.println("Antall racks:" + abel.hentAntRacks());
    System.out.println("Antall prosessorer: " + abel.hentAntProsessorerKlynge());
    System.out.println("");
    System.out.println("");

    Dataklynge abel2 = new Dataklynge("dataklynge2.txt");
    System.out.println("Noder med minst 32 GB: " + abel2.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel2.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel2.noderMedNokMinne(128));
    System.out.println("");
    System.out.println("Antall racks:" + abel2.hentAntRacks());
    System.out.println("Antall prosessorer: " + abel2.hentAntProsessorerKlynge());
    System.out.println("");
    System.out.println("");

    Dataklynge abel3 = new Dataklynge("dataklynge3.txt");
    System.out.println("Noder med minst 32 GB: " + abel3.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel3.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel3.noderMedNokMinne(128));
    System.out.println("");
    System.out.println("Antall racks:" + abel3.hentAntRacks());
    System.out.println("Antall prosessorer: " + abel3.hentAntProsessorerKlynge());
    System.out.println("");
    System.out.println("");

    Dataklynge abel4 = new Dataklynge("dataklynge4.txt");
    System.out.println("Noder med minst 32 GB: " + abel4.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel4.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel4.noderMedNokMinne(128));
    System.out.println("");
    System.out.println("Antall racks:" + abel4.hentAntRacks());
    System.out.println("Antall prosessorer: " + abel4.hentAntProsessorerKlynge());
    System.out.println("");
    System.out.println("");
}
}
