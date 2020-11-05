public class HvitRute extends Rute{

  protected boolean besokt = false; //det er bare mulig å besøke hvite ruter

  public HvitRute(int kolonne, int rad, Labyrint labyrint){
    super(kolonne, rad, labyrint);
  }

  @Override
  public char tilTegn(){
    return('.');
  }

  @Override
  public void gaa(Lenkeliste<String> vei){
    Lenkeliste<String> naboer;
    for(Rute nabo : naboListe){
      if(nabo == null){
        return;
      }
      else if(nabo.besokt){
        return;
      }
      else{
        naboer.leggTil(nabo);
      }
    }
    if(naboer.length() == 1){ //Hvis ruten bare har en hvit nabo
      String nyVei = naboer[0].toString();
      if(vei.length() > 0){
        nyVei = (vei + "-->" + nyVei);
      }
      naboer[0].besokt = true;
      naboer[0].gaa(nyVei);
    }
    else if(naboer.lenght() == 2){
      Lenkeliste<String> veienSaaLangt = vei;

      Runnable minRun1 = new MinRun(naboer[0], veienSaaLangt);
      Thread traad1 = new Thread(minRun1);
      Runnable minRun2 = new MinRun(naboer[1], veienSaaLangt);
      Thread traad2 = new Thread(minRun2);
      traad1.start();
      traad2.start();


    }
    else if(naboer.lenght() == 3){
      Lenkeliste<String> veienSaaLangt = vei;
      //lager tre tråder
    }
    else{
      System.out.println("Noe er veldig veldig galt her.");
    }


  }
}
