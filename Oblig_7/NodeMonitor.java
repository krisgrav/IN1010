import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class NodeMonitor{
  private final Lock lock = new ReentrantLock();
  public Lenkeliste<String> utveierT = new Lenkeliste<>();

  public void add(String string){
    lock.lock();
    try{
      utveierT.leggTil(string);
      //System.out.println("Utvei lagt til i liste: " + string);
    }
    finally{
      lock.unlock();
    }
  }

  public Lenkeliste<String> hentListe(){
    return utveierT;
  }

}
