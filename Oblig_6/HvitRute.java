import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class HvitRute extends Rute{

  Boolean aapning = false;
  Boolean besokt = false;

  private Lock laas = new ReentrantLock(); //Brukes denne?


  public HvitRute(int kolonne, int rad, Labyrint labyrint){
    super(kolonne, rad, labyrint);
  }

  @Override
  public boolean erHvit(){
    return true;
  }

  @Override
  public char tilTegn(){
    return('.');
  }

  @Override
  public void gaa(String vei){
    Lenkeliste<Rute> gyldigeNaboer = new Lenkeliste<>(); //Lager en liste og legge til naboer som er hvite og ikke tidligere besøkt.
    Lenkeliste<Thread> traader = new Lenkeliste<>();
    for(Rute rute : naboListe){
      if(rute.erHvit() && (!(besokt))){
        gyldigeNaboer.leggTil(rute);
      }
    }
    if(gyldigeNaboer.stoerrelse() == 1){ //Hvis det er en gyldig nabo kalles kun gå
      if(besokt){ //Hvis denne ruten (this) er besøkt skal det ikke skje noe.
        return;
      }
      besokt = true;
      vei += this.toString() + "-->";
      gyldigeNaboer.hent(0).gaa(vei);
    }
    else if(gyldigeNaboer.stoerrelse() > 1){
      if(besokt){
        return;
      }
      besokt = true;
      vei += this.toString() + "-->";
      int teller = gyldigeNaboer.stoerrelse();
      while(teller > 1){
        teller--;
        Runnable task = new MinRun(gyldigeNaboer.hent(teller), vei);
        Thread nyTraad = new Thread(task);
        traader.leggTil(nyTraad);
      }
      for(Thread traad : traader){
        traad.start();
      }
      /**
      Starter opp de nye trådene.
      De nye trådene må startes før programmet fortsetter på den gamle tråden,
      ellers vil nye trådene måtte vente til hovedtråden er ferdig før de startes selv.
      Det vil fjerne en del av poenget med tråder, når de skal kjøre parallelt.
      **/
      gyldigeNaboer.hent(0).gaa(vei);
    }
    for(Thread traad : traader){
      try{
        traad.join();
      }
      catch(Exception e){
      }
    }
  }

}
