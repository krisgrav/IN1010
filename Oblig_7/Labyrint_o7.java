import javafx.application.Application;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.event.Event;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
//import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint_o7 extends Application{
  private static Labyrint labyrint;
  private static int kolonner;
  private static int rader;

  private Lenkeliste<String> utveier;
  private int utveiTeller = 0;
  private Label antUtveier = new Label("Antall utveier: ");

  private boolean naadTopp = false;
  private boolean naadBunn = false;

  private File labyrintFil;
  private FileChooser filvelger;

  private Button filVelgerKnapp;
  private Button resetKnapp;

  private FxRute[][] ruter;
  private static boolean[][] losning;

  private Scene scene;
  private Stage stage1;
  private BorderPane pane;
  private static GridPane gridLabyrint;
  private GridPane grid;

  public static void main(String[] args){
    launch(args);
  }

  @Override
  public void start(Stage stage1){

//Lager scene for filvelger
    filVelgerKnapp = new Button("Velg labyrintfil"); //Lager knapp for filvelger
    filVelgerKnapp.setLayoutX(450); filVelgerKnapp.setLayoutY(350);
    VelgFil velgFil = new VelgFil();
    filVelgerKnapp.setOnAction(velgFil); //Knappen kaller på eventet velgFil

    pane = new BorderPane();
    pane.setPrefSize(1000, 800);
    pane.setCenter(filVelgerKnapp);
    scene = new Scene(pane);

    stage1.setTitle("Labyrintløser");
    stage1.setScene(scene);
    stage1.show();
  }


  class VelgFil implements EventHandler<ActionEvent>{  //Når bruker har valgt en fil dannes resten GUI
    @Override
    public void handle(ActionEvent e){
      filvelger = new FileChooser();
        filvelger.getExtensionFilters().addAll(new ExtensionFilter("Input filer", "*.in")); //tillater kun .in filer
      labyrintFil = filvelger.showOpenDialog(stage1);
      gridLabyrint = lagLabyrint(labyrintFil);

      VBox knappeBoks = new VBox(); //Vertikal boks for knapper
      Button nyLabyrintKnapp = new Button("Ny labyrint");
        VelgFil velgFil2 = new VelgFil();  //Om brukeren vil velge en ny fil opprettes nytt-rekursivt event
        nyLabyrintKnapp.setOnAction(velgFil2);
        nyLabyrintKnapp.setPrefSize(100, 30);
      Button nesteUtveiKnapp = new Button("Neste utvei");
        NesteUtvei nesteUtvei = new NesteUtvei();
        nesteUtveiKnapp.setOnAction(nesteUtvei);
        nesteUtveiKnapp.setPrefSize(100, 30);
      Button resetKnapp = new Button("Reset");
        Reset reset = new Reset();
        resetKnapp.setPrefSize(100, 30);
        resetKnapp.setOnAction(reset);
      knappeBoks.getChildren().addAll(nyLabyrintKnapp, nesteUtveiKnapp, resetKnapp); //Legger knappene inn i vertikal boks

      pane.setLeft(knappeBoks); //Setter elemeter på sin plass i borderpane
      pane.setCenter(gridLabyrint);
      pane.setTop(antUtveier);
    }
  }

/* Metode som lager og returnerer en GridPane */
  public GridPane lagLabyrint(File labyrintFil){
    try{
      labyrint = Labyrint.lesFraFil(labyrintFil);

      grid = new GridPane(); //lager gridpane for labyrinten

      ruter = new FxRute[labyrint.kolonner][labyrint.rader];
      for(int x = 0; x < labyrint.rader; x++){
        for(int y = 0; y < labyrint.kolonner; y++){
          ruter[y][x] = new FxRute(labyrint.hentRute(y, x));
          grid.add(ruter[y][x], y, x);
        }
      }
    }
    catch(FileNotFoundException ex){
    }
    return grid;
  }

/* Event for å finne neste utvei */
  class NesteUtvei implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent e){
      System.out.println("Ant utveier: " + utveier.stoerrelse()); //til terminal for feilsøking
      System.out.println("Utveiteller: " + utveiTeller); //til terminal for feilsøking
      System.out.println("Bunn: " + naadBunn);
      System.out.println("Topp: " + naadTopp);

      reset(); //Tilbakestiller GUI labyrinten

      antUtveier = new Label("Antall utveier: " + utveier.stoerrelse()); //Reset nullstiller antUtveier utskriften,
      pane.setTop(antUtveier); // derfor må denne med.

      if(utveiTeller == (utveier.stoerrelse() - 1)){ //Hvis utvei-telleren har kommet itl den siste utveien i lista over utveier
        naadTopp = true;
        naadBunn = false;
      }
      else if(utveiTeller == 0){ //Hvis den er i bunn av lista
        naadBunn = true;
        naadTopp = false;
      }

      if(utveier.stoerrelse() > 1){ //Hvis det er mer enn en utvei


        if(!(naadTopp)){ //og den ikke har nådd siste utvei i listen
          String utvei = utveier.hent(utveiTeller);
          boolean[][] losning = losningStringTilTabell(utvei, labyrint.kolonner, labyrint.rader);
          for(int x = 0; x < labyrint.rader; x++){
            for(int y = 0; y < labyrint.kolonner; y++){
              if(losning[y][x]){
                ruter[y][x].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
              }
              else if(ruter[y][x].erHvit){
                ruter[y][x].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
              }
            }
          }
          if(utveiTeller == (utveier.stoerrelse() - 1)){ //Når den når siste utvei i listen
            naadTopp = true;
            naadBunn = false;
          }
          else{ //Hvis den enda ikke har nådd toppen
            utveiTeller++;
          }
        }
        else if(naadTopp){ //Hvis toppen er nådd teller programmet fra toppen og ned til bunn
          String utvei = utveier.hent(utveiTeller);
          boolean[][] losning = losningStringTilTabell(utvei, labyrint.kolonner, labyrint.rader); //endre rekkefølge?
          for(int x = 0; x < labyrint.rader; x++){
            for(int y = 0; y < labyrint.kolonner; y++){
              if(losning[y][x]){
                ruter[y][x].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
              }
              else if(ruter[y][x].erHvit){
                ruter[y][x].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
              }
            }
          }
          if(utveiTeller == 0){ //Når bunnen er nådd (lol)
            naadBunn = true;
            naadTopp = false;
          }
          else{
            utveiTeller--; //Hvis ikke bunnen er nådd, fortsett å telle nedover
          }
        }

      }
    }
  }

/* Event for resetKnapp. Kaller kun på metoden reset() */
  class Reset implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent e){
      reset();
    }
  }

/* Metode som resetter labyrinten */
  public void reset(){
    gridLabyrint = lagLabyrint(labyrintFil);
    pane.setCenter(gridLabyrint); //lager en ny gridpane og setter den på scenen
    naadTopp = false;
    naadBunn = false;
    antUtveier = new Label("Antall utveier: ");
    pane.setTop(antUtveier);
  }

  private class FxRute extends Pane{ //Rute som skal inn i labyrinten.
    public boolean erHvit = true;
    Rute rute; //Referanse til rute i labyrinten
    int stoerrelse; //størrelse på ruten i GUI

    public FxRute(Rute rute){
      this.rute = rute;
      this.stoerrelse = 20; //ednre denne for å endre størrelse på rutene
      if(rute.tilTegn() == '#'){ //hvis det er en sort rute
        erHvit = false;
      }
      if(erHvit){
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
      }
      else{
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
      }
      setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
      setMinWidth(stoerrelse);
      setMinHeight(stoerrelse);

      /* Interaksjon for å trykke på en av rutene i labyrinten med musepekeren legges inn i hver rute */

      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
        public void handle(MouseEvent event){
          if(erHvit){
            utveier = labyrint.finnUtveiFra(rute.kolonne, rute.rad);
            if(utveier.stoerrelse() == 0){      //hvis det ikke finnes utveier
              antUtveier = new Label("Ingen utveier");
              pane.setTop(antUtveier);
              for(int x = 0; x < labyrint.rader; x++){
                for(int y = 0; y < labyrint.kolonner; y++){
                  if(ruter[y][x].erHvit){
                    ruter[y][x].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                  }
                }
              }
            }
            else{   //hvis det finnes utveier
              antUtveier = new Label("Antall utveier: " + utveier.stoerrelse());
              pane.setTop(antUtveier);
              String forsteUtvei = utveier.hent(0);  //henter første utvei
              boolean[][] losning = losningStringTilTabell(forsteUtvei, labyrint.kolonner, labyrint.rader); //endre rekkefølge?
              for(int x = 0; x < labyrint.rader; x++){
                for(int y = 0; y < labyrint.kolonner; y++){
                  if(losning[y][x]){      //Om en rute er en del av utveien farges den rød
                    ruter[y][x].setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                  }
                  else if(ruter[y][x].erHvit){
                    ruter[y][x].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                  }
                }
              }
            }

          }
        }
      });//slutt på EventHandler
    }
  }

  static boolean[][] losningStringTilTabell(String losningString, int hoyde, int bredde){ //endre rekkefølge?
      boolean[][] losning = new boolean[hoyde][bredde];
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
      java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
      while (m.find()) {
          int y = Integer.parseInt(m.group(1));
          int x = Integer.parseInt(m.group(2));
          losning[y][x] = true;
      }
      return losning;
  }
}
