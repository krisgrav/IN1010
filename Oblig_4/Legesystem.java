import java.util.*;
import java.io.*;
import java.lang.*;


public class Legesystem{
  static int utskrevneNarkotisk = 0;
  static int utskrevneVanedannende = 0;
  static SortertLenkeliste<Lege> legeListe = new SortertLenkeliste<Lege>();
  static Lenkeliste<Pasient> pasientListe = new Lenkeliste<Pasient>();
  static Lenkeliste<Legemiddel> legemiddelListe = new Lenkeliste<Legemiddel>();
  static Lenkeliste<Resept> reseptListe = new Lenkeliste<Resept>();


    public static void main(String[] args){
      File fil = new File("inndata.txt");
      lesFil(fil);
      int i = 0;
      while(i == 0){
        hovedmeny();
      }
    }


/* METODE FOR INNLESNING FRA FIL */

    private static void lesFil(File filnavn){
      Scanner scanner = null;
      try{
        scanner = new Scanner(filnavn);
      }
      catch(FileNotFoundException e){
        System.out.println("Fant ikke filen.");
        return;
      }

      int teller = 0;
      while(scanner.hasNextLine()){
        String lestLinje = scanner.nextLine();

        if(lestLinje.startsWith("#")){
          teller++;
          lestLinje = scanner.nextLine();
        }

      if(teller == 1){
        String[] info = lestLinje.split(",");
        if(info.length == 2){
          Pasient pasient = new Pasient(info[0], info[1]);
          pasientListe.leggTil(pasient);
        }
        else{
          System.out.println("Feil ved innlesning av pasient fra fil.");
        }
      }

      else if(teller == 2){
        String[] info = lestLinje.split(",");
        Double pris = Double.valueOf(info[2]);
        Double virkestoff = Double.valueOf(info[3]);

        if(info.length == 4){
          legemiddelListe.leggTil(new Vanlig(info[0], pris, virkestoff));
        }
        else if(info.length == 5){
          if(info[1].equals("vanedannende")){
            legemiddelListe.leggTil(new Vanedannende(info[0], pris, virkestoff, Integer.valueOf(info[4])));
          }
          else if(info[1].equals("narkotisk")){
            legemiddelListe.leggTil(new Narkotisk(info[0], pris, virkestoff, Integer.valueOf(info[4])));
          }
          else{
            System.out.println("Feil ved innlesning av legemiddel fra fil.");
          }
        }
        else{
          System.out.println("Feil ved innlesning av legemiddel fra fil.");
        }
      }


      else if(teller == 3){
        String[] info = lestLinje.split(",");
        if(info.length == 2){
          int kontrollId = Integer.valueOf(info[1]);
          String navn = info[0];
          if(kontrollId == 0){
            legeListe.leggTil(new Lege(navn));
          }
          else{
            legeListe.leggTil(new Spesialist(navn, kontrollId));
          }
        }
        else{
          System.out.println("Feil ved innlesning av lege fra fil.");
        }
      }

      else if(teller == 4){
        String[] info = lestLinje.split(",");
        int legemiddelId = Integer.parseInt(info[0]);
        int pasientId = Integer.parseInt(info[2]);
        Legemiddel legemiddel = legemiddelListe.hent(legemiddelId); //Gjør om legemiddelId til referanse for legemiddel.
        Pasient pasient = pasientListe.hent(pasientId); //Gjør om pasientId til referanse for pasient
        Lege lege = null;

        for(Lege x : legeListe){ //Gjør om legenavn til referanse for lege.
          if(x.hentNavn().equals(info[1])){
            lege = x;
          }
        }

        if(info.length == 4){ //Her skal det legges inn try/catch
          try{
            reseptListe.leggTil(lege.skrivPResept(legemiddel, pasient));
          }
          catch(UlovligUtskrift e){
            System.out.println(e);
          }
        }
        else if(info.length == 5){
          if(info[3].equals("blaa")){
            try{
              reseptListe.leggTil(lege.skrivBlaaResept(legemiddel, pasient, Integer.parseInt(info[4])));
            }
            catch(UlovligUtskrift e){
              System.out.println(e);
            }
          }
          else if(info[3].equals("hvit")){
            try{
              reseptListe.leggTil(lege.skrivHvitResept(legemiddel, pasient, Integer.parseInt(info[4])));
            }
            catch(UlovligUtskrift e){
              System.out.println(e);
            }
          }

          else if(info[3].equals("millitaer")){
            try{
              reseptListe.leggTil(lege.skrivMillitaerResept(legemiddel, pasient, Integer.parseInt(info[4])));
            }
            catch(UlovligUtskrift e){
              System.out.println(e);
            }
          }
        }
      }
      }
      scanner.close();
    }



/*  HER STARTER HOVEDMENYEN */

    public static void hovedmeny(){
      // Starter programmet:
      System.out.println("\nHovedmeny:");

      Scanner lokkeScanner  = new Scanner(System.in);
      System.out.println("Hva vil du gjøre? Skriv bokstav(a, b, c, d eller e)");
      System.out.println("a: Oversikt over pasienter, leger, legemidler og resepter.");
      System.out.println("b: Opprette og legge til nye elementer i systemet.");
      System.out.println("c: Bruke en gitt resept fra listen til en pasient.");
      System.out.println("d: Skrive ut statistikk over vanedannende og narkotiske resepter.");
      System.out.println("e: Skrive alle data til fil.");
      System.out.println("f: Avslutte programmet.");
      String svar = lokkeScanner.nextLine();

  // På bakgrunna av svar fra bruker vil en av følgende ting skje:

      switch(svar){
        case "a":
        oversikteAlle();
        break;
        case "b":
        leggTilElement();
        break;
        case "c":
        reseptBruk();
        break;
        case "d":
        statistikk();
        break;
        case "e":
        skrivTilFil();
        break;
        case "f":
        System.out.println("Program avsluttet");
        System.exit(0);

        break;

        default:
          System.out.println("Ugyldig input. Vil du prøve igjen? ja/nei");
          lokkeScanner.nextLine();

          if(lokkeScanner.equals("ja")){
            hovedmeny();
          }
          else{
            System.out.println("Program avsluttet.");
            System.exit(0);
          }
        }
      }






      public static void oversikteAlle(){
        System.out.println("\nPasientliste:");
        pasientListe.skrivListe();
        System.out.println("\nLegemiddelliste: ");
        legemiddelListe.skrivListe();
        System.out.println("\nLegeliste: ");
        legeListe.skrivListe();
        System.out.println("\nReseptliste: ");
        reseptListe.skrivListe();
      }




      public static void leggTilElement(){
        // Oppretter String variabler hvor svar fra bruker kan bli lagret.
        String svar1 = " ";
        String svar2 = " ";
        String svar3 = " ";
        String svar4 = " ";
        String svar5 = " ";

        // Tar input fra bruker.
        Scanner nyScanner = new Scanner(System.in);

        // Valgmuligheter
        System.out.println("Hva vil du legge til? (a, b, c, d eller e.)");
        System.out.println("a. Pasient");
        System.out.println("b. Legemiddel");
        System.out.println("c. Lege");
        System.out.println("d. Resept");
        System.out.println("e. Gå til hovedmeny.");

        svar1 = nyScanner.nextLine(); // leser input.

        // Legger til pasient.
        if(svar1.compareTo("a") == 0){
          System.out.println("Hva er navnet på pasienten?");

        // Tar ny input fra bruker.
          svar2 = nyScanner.nextLine();
          try{
            System.out.println("Hva er fødselsnummeret til pasienten?");
          // Tar inn enda en input fra bruker.
            svar3 = nyScanner.nextLine();
          // Gjør om string til int for å sjekke om bruker har lagt inn tall.
            long svar3tall = Long.parseLong(svar3);

          // Gjør om long til string.
            String stringSvar3 = Long.toString(svar3tall);
        // Oppretter nytt pasientobjekt og legger til i liste.
            Pasient p = new Pasient(svar2, stringSvar3);
            pasientListe.leggTil(p);
            System.out.println("Pasient " + svar2 + " er lagt til i systemet.");
          }
          catch(NumberFormatException e){
            System.out.println("Du må skrive inn tall. Tilbake til hovedmeny");
            hovedmeny();
          }
        }// Slutten på legg til pasient.

        else if(svar1.compareTo("b") == 0){
        // Starter med å ta kartlegge type legemiddel.
          Scanner legemiddelScanner = new Scanner(System.in);
          String legemiddelsvar = " ";
          System.out.println("Hvilken type legemiddel er det? ");
          System.out.println("a. Vanlig, b. Vanedannende, c. Narkotisk.");
          legemiddelsvar = legemiddelScanner.nextLine();

          if(legemiddelsvar.compareTo("c") == 0){//Legger til Narkotisk legemiddel
            System.out.println("Hva er navnet på legemiddelet?");
            svar2 = nyScanner.nextLine();
            System.out.println("Hva er prisen på legemiddelet?");
            svar3 = nyScanner.nextLine();
            System.out.println("Hvor mange milligram virkestoff finnes i legemiddelet?");
            svar4 = nyScanner.nextLine();
            System.out.println("Hva er den narkotiske styrken på legemiddelet?");
            svar5 = nyScanner.nextLine();
          // Gjør om de nødvendige variablene om til riktige verdier.
          //    try{
            double pris = Double.parseDouble(svar3);
            double virkestoff = Double.parseDouble(svar4);
            int styrke = Integer.parseInt(svar5);
            //    }catch(NumberFormatException i){
            //    System.out.println("Feil input. Du må skrive riktige tall.");
              //  hovedmeny()
                //}
              // Oppretter legemiddel og legger til i liste.
            Narkotisk n = new Narkotisk(svar2, pris, virkestoff, styrke);
            legemiddelListe.leggTil(n);
            System.out.println("\nNarkotisk legemiddel " + svar2 + " er lagt til i systemet.");
          }

          else if(legemiddelsvar.compareTo("b") == 0){
            System.out.println("Hva er navnet på legemiddelet? ");
            svar2 = nyScanner.nextLine();
            System.out.println("Hva er prisen på legemiddelet?");
            svar3 = nyScanner.nextLine();
            System.out.println("Hvor mange milligram virkestoff finnes i legemiddelet?");
            svar4 = nyScanner.nextLine();
            System.out.println("Hva er den vanedannende styrken på legemiddelet?");
            svar5 = nyScanner.nextLine();


            // Gjør om variablene til riktig format.
            double pris = Double.parseDouble(svar3);
            double virkestoff = Double.parseDouble(svar4);
            int styrke = Integer.parseInt(svar5);

                // Legger til nytt vanedannende legemiddel i listen.
            Vanedannende n = new Vanedannende(svar2, pris, virkestoff, styrke);
            legemiddelListe.leggTil(n);
            System.out.println("\nVanedannende legemiddel " + svar2 + " er lagt til i systemet.");
          }


          else if(legemiddelsvar.compareTo("a") == 0){
            System.out.println("Hva er navnet på legemiddelet? ");
            svar2 = nyScanner.nextLine();
            System.out.println("Hva er prisen på legemiddelet?");
            svar3 = nyScanner.nextLine();
            System.out.println("Hvor mange milligram virkestoff finnes i legemiddelet?");
            svar4 = nyScanner.nextLine();

            //  try{
            double pris = Double.parseDouble(svar3);
            double virkestoff = Double.parseDouble(svar4);
            //    }catch(NumberFormatException i){
              //    System.out.println("Feil input. Du må skrive riktige tall.");
                //  System.out.println("Prøv igjen:");
                  //hovedmeny();
                  //}
            Vanlig n = new Vanlig(svar2, pris, virkestoff);
            legemiddelListe.leggTil(n);
            System.out.println("\nVanlig legemiddel " + svar2 + " er lagt til i systemet.");
          }

          else{
            System.out.println("Ugyldig input. Vil du prøve igjen? ja/nei");
            svar5 = nyScanner.nextLine();
            if(svar5.compareTo("ja") == 0){
              leggTilElement();
            }
            else{
              hovedmeny();
            //      break;
            }
          }
        } // Slutten på legg til legemidler.

        else if(svar1.compareTo("c") == 0){
          // Legger til Lege
          System.out.println("Hva er navnet på legen du vil legge til?");
            svar2 = nyScanner.nextLine();
            //  try{
          System.out.println("Hva er legens kontroll-Id? (0 for vanlig lege)");
            svar3 = nyScanner.nextLine();
            int kontrollID = Integer.valueOf(svar3);
            //  }
            //  catch(NumberFormatException i){
              //  System.out.println("Du må skrive inn tall.");
              //}
            if(kontrollID == 0){
              Lege l = new Lege(svar2);
              legeListe.leggTil(l);
              System.out.println("\nLegen " + svar2 + " er lagt til i systemet.");
            }
            else{
              int kontrollId = Integer.parseInt(svar3);
              Spesialist sl = new Spesialist(svar2, kontrollId);
              legeListe.leggTil(sl);
              System.out.println("\nSpesialisten " + svar2 + " er lagt til i systemet.");
            }
          }

          else if(svar1.compareTo("d") == 0){
          // Legge til resept.
          // Bruker velger hvilket legemiddel fra listen over legemidler.
            Legemiddel legemiddel = null;
            System.out.println("\nSkriv inn legemiddelID: ");
            for(Legemiddel l : legemiddelListe){
              System.out.println("Id: " + l.hentId() + " Navn: " + l.hentNavn());
            }
            svar1 = nyScanner.nextLine();
            try{
              int nyttLegemiddelSvar = Integer.parseInt(svar1);
              for(Legemiddel l : legemiddelListe){
                if(l.hentId() == nyttLegemiddelSvar){
                  legemiddel = l;
                }
              }
              System.out.println(legemiddel.hentNavn() + " ble valgt. ");
            }
            catch(Exception e){
              System.out.println("Feil nummer.");
              leggTilElement();
            }


              // Bruker velger pasient.
            Pasient pasient = null;
            System.out.println("\nSkriv Id til pasiententen du vil bruke: ");
            for(Pasient p : pasientListe){
              System.out.println("Id: " + p.hentId() + ". Navn: " + p.hentNavn());
            }
            svar2 = nyScanner.nextLine();
            int nyttPasientSvar = Integer.parseInt(svar2);
            try{
              for(Pasient p : pasientListe){
                if(p.hentId() == nyttPasientSvar){
                  pasient = p;
                  System.out.println("Pasienten " + pasient.hentNavn() + " ble valgt.");
                }
              }
            }
            catch(Exception e){
              System.out.println("Id ikke funnet.");
              leggTilElement();
            }




            // Bruker velger Lege:
            Lege lege = null;
            System.out.println("\nVelg mellom følgende leger (skriv navn): ");
            for(Lege l : legeListe){
              System.out.println(l.hentNavn());
            }
            svar3 = nyScanner.nextLine();
            try{
              for(Lege l : legeListe){
                if(l.hentNavn().equalsIgnoreCase(svar3)){
                  lege = l;
                  System.out.println("Valgt lege: " + l.hentNavn());
                }
              }
            }
            catch(Exception e){
              System.out.println("Legen finnes ikke.");
              leggTilElement();
            }

            // Bruker velger reit.
              //  try{
            System.out.println("\nSkriv antall reit: ");
            svar4 = nyScanner.nextLine();
            int reit = Integer.parseInt(svar4);
              //  }
              //  catch(NumberFormatException i){
              //    System.out.println("Feil input. Du må skrive tall.");
              //  }

                // Bruker velger resepttype.

            System.out.println("\nHvordan type resept vil du legge til? ");
            System.out.println("a. Presept, b. Militærresept, c. Hvitresept,");
            System.out.println("d. Blåresept. Svar a, b, c eller d");
            svar5 = nyScanner.nextLine();

            if(svar5.equalsIgnoreCase("a")){
              try{
                reseptListe.leggTil(lege.skrivPResept(legemiddel, pasient));
                System.out.println("Resept på " + legemiddel.hentNavn() + " skrevet.");
                if(legemiddel instanceof Narkotisk){
                  utskrevneNarkotisk++;
                }
                else if(legemiddel instanceof Vanedannende){
                  utskrevneVanedannende++;
                }
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
            }
            else if(svar5.equalsIgnoreCase("b")){
              try{
                reseptListe.leggTil(lege.skrivMillitaerResept(legemiddel, pasient, reit));
                System.out.println("Resept på " + legemiddel.hentNavn() + " skrevet.");
                if(legemiddel instanceof Narkotisk){
                  utskrevneNarkotisk++;
                }
                else if(legemiddel instanceof Vanedannende){
                  utskrevneVanedannende++;
                }
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
            }
            else if(svar5.equalsIgnoreCase("c")){
              try{
                reseptListe.leggTil(lege.skrivHvitResept(legemiddel, pasient, reit));
                System.out.println("Resept på " + legemiddel.hentNavn() + " skrevet.");

              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
            }
            else if(svar5.equalsIgnoreCase("d")){
              try{
                reseptListe.leggTil(lege.skrivBlaaResept(legemiddel, pasient, reit));
                System.out.println("Resept på " + legemiddel.hentNavn() + " skrevet.");
                if(legemiddel instanceof Narkotisk){
                  utskrevneNarkotisk++;
                }
                else if(legemiddel instanceof Vanedannende){
                  utskrevneVanedannende++;
                }
              }
              catch(UlovligUtskrift e){
                System.out.println(e);
              }
            }  // Slutten på legge-til resept.
          }
        } //Slutt på metode


        public static void statistikk(){
          // Henter ut informasjon om legemidler

          for(Resept r : reseptListe){
            if(r.hentLegemiddel() instanceof Narkotisk){
              utskrevneNarkotisk++;
            }
            if(r.hentLegemiddel() instanceof Vanedannende){
              utskrevneVanedannende++;
            }
          }
          System.out.println("\nTotalt antall utskrevne narkotiske resepter: " + utskrevneNarkotisk);
          System.out.println("Totalt antall utskrevne vanedannende resepter: " + utskrevneVanedannende);

          utskrevneNarkotisk = 0;
          utskrevneVanedannende = 0;


          // Sjekker hvilke leger som har skrevet ut narkotiske resepter.
          int antallLege = 0;
          for(Lege l : legeListe){
            antallLege = 0;
            boolean lege = false;
            for(Resept r : l.hentListe()){
              if(r.hentLegemiddel() instanceof Narkotisk){
              antallLege++;
              lege = true;
              }
            }
            if(lege == true){
              System.out.println("Lege: " + l.hentNavn() + " Har skrevet ut " + antallLege + " narkotiske resepter");
            }
          }

          // Sjekker om noen pasienter har resept på narkotiske legemidler.
          int antallPasient = 0;
          for(Pasient p : pasientListe){
            antallPasient = 0;
            boolean pasient = false;
            for(Resept r : p.hentResepter()){
              if(r.hentLegemiddel() instanceof Narkotisk){
                antallPasient++;
                pasient = true;
              }
            }
            if(pasient == true){
              System.out.println("Pasient: " + p.hentNavn() + " Har " + antallPasient + " narkotisk(e) resept(er).");
          }

          }

          }



          public static void skrivTilFil(){
            System.out.println("Skriver til fil.");
              FileWriter nyFil = null;
              try{
                nyFil = new FileWriter("oppdatertIndata.txt");
              }catch(Exception e){
                System.out.println("Kan ikke lage filen");
              }


            for(Pasient p : pasientListe){
              String navn = p.hentNavn();
              String id = Integer.toString(p.hentId());
              try{
                nyFil.write(navn + ", " + id);
              } catch(Exception e){
                System.out.println("Kan ikke lage filen");
              }

            }
            for(Legemiddel l : legemiddelListe){
              try{
                nyFil.write(l.hentNavn() + ", " +  l.hentId() + ", " +  l.hentPris() + ", " +  l.hentVirkestoff());
              }catch(Exception e){
                System.out.println("Kan ikke lage filen");
              }

            }
            for(Lege l : legeListe){
              if(l instanceof Spesialist){
                try{
                  nyFil.write(l.hentNavn() + ", " + l.hentKontrollId());
                }catch(Exception e){
                  System.out.println("Kan ikke lage filen");
                }

              }else{
                try{nyFil.write(l.hentNavn() + ", " +  "0");
              }catch(Exception e){
                System.out.println("Kan ikke lage filen");
              }
            }

            }
            for(Resept r : reseptListe){
              try{
                nyFil.write(r.hentId() + ", "  + r.hentLegemiddel() + ", " + r.hentLege() + ", " + r.hentPasient() + ", " + r.hentReit());
              } catch(Exception e){
                System.out.println("Kan ikke lage filen");
              }

            }
            System.out.println("Alle nye data er lagt til: ");
            System.out.println(" ");
            oversikteAlle();

          }

            public static void reseptBruk(){
              Pasient pasient = null; // plassholder for pasienten bruker vil se på.
              Scanner nyScanner = new Scanner(System.in);
              String svar = " "; String svar1 = " ";

              System.out.println("Hvilken pasient vil du se på? 0, 1, 2 osv");
              pasientListe.skrivListe();
              svar = nyScanner.nextLine();
              pasient = pasientListe.hent(Integer.valueOf(svar));

              // Sjekker om pasienten har noen resepter.
              if(pasient.hentResepter().stoerrelse() == 0){
                System.out.println("Valgt pasient har ingen resepter");
              }
              System.out.println("Hvilken resept vil du bruke? (Skriv Id)");


                for(Resept r : pasient.hentResepter()){
                System.out.println("Id: " + r.hentId() + ". Legemiddel: " + r.hentLegemiddel().hentNavn() + " Reit: " + r.hentReit());
                }

                svar = nyScanner.nextLine();
                int valgtPasient = Integer.parseInt(svar);
                Resept resept = pasient.hentResepter().hent(valgtPasient);

                if(resept.bruk()){ // Hvis true, så printes info om resepten ut.
                  System.out.println("\nResept på " + resept.hentLegemiddel().hentNavn() + " ble brukt.");
                }
                else{
                  System.out.println("Ingen gjenværende reit. Kunne ikke bruke resept");
                }
              }
            }//Slutt class Legesystem
