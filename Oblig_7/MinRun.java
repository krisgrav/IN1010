import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class MinRun implements Runnable{
  Rute rute;
  String vei;

  public MinRun(Rute rute, String vei){ //MinRun tar inn en rute den skal kalles på, og er stirng som innneholder veien så langt
    this.rute = rute;
    this.vei = vei;
  }

  public void run(){ //Kaller på gaa() fra en gitt rute, med veien opp til den ruten.
      rute.gaa(vei);
  }
}
