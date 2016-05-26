package prog2_a3;

import prog2_a3.fatsquirrel.console.*;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.FlattenedBoard;
import prog2_a3.fatsquirrel.core.Game;
import prog2_a3.fatsquirrel.core.GameLogger;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.interfaces.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.Marshaller.Listener;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;


public class Launcher extends Application{
private static long timestamp_1;
private static long timestamp_2;
private GameImpl game;
private Calendar calendar;
private Command commandPuffer;
private static int gameMode = 1; //gameMode Switcher: 1== konsole_alt; 2== konsole_neu; 3== javafx_gui
private static Level level = Level.FINE; //Level für Logging setzen
private BoardConfig boardConfig = new BoardConfig();
private Stage primaryStage;
private FxUI fxUI;
public static GameLogger gameLogger;

    public Launcher(){ 
    	this.gameLogger = new GameLogger(level);
        this.boardConfig = new BoardConfig();
        this.calendar = new GregorianCalendar();
        if (gameMode != 3){
        	this.game = new GameImpl();
        };
        if (gameMode == 3){
        	primaryStage = new Stage();
        }
    }
	
    public static void main(String[] args){

       //Konsole_alt    
    	if(gameMode == 1){
    	Launcher launcher = new Launcher();
    	gameLogger.log(level.INFO, "Spiel initialisieren: Modus = Konsole_alt");
    	launcher.game.run();
    	}
    	//Konsole_neu
    	else if (gameMode == 2){
    	Launcher launcher = new Launcher();
    	gameLogger.log(level.INFO, "Spiel initialisieren: Modus = Konsole_neu");
    	launcher.startGame(null, launcher.game);
    	}
    	//javafx_GUI
    	else if (gameMode == 3){
        gameLogger.log(level.INFO, "Spiel initialisieren: Modus = JavaFX_GUI");
    	launch(args);	
    	}
    	
            }
    
	//Diese Methode gibt den Spielablauf wieder. 
    //Die Methode game.run wird fortlaufend aufgerufen. Mithilfe von game.FPS kann die Geschwindigkeit reguliert werden
    private void startGame(FxUI fxUI, GameImpl game) { 
    	game.setfxUI(fxUI);
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask(){

		@Override
		public void run() {
			try {
				timestamp_1 = calendar.getTimeInMillis();
				game.run();
				timestamp_2 = calendar.getTimeInMillis();	
				
				Thread.sleep((timestamp_1 + 1000 /game.getFPS()) - timestamp_2); //schlafe Startzeit+Durchläufe/Sekunde - Endzeit

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		

		}
		
	},1000,1 );
		if(Launcher.gameMode == 1 || Launcher.gameMode == 2){
				while(true){
						commandPuffer = game.getUI().savePuffer();
							if(commandPuffer != null){
								game.setPuffer(commandPuffer);
								game.process();
								game.setPuffer(null); 
								commandPuffer = null;
							}
		
		if( game.getPuffer() != null)
			System.out.println(game.getPuffer().getCommandType().getName());
		
	}

	}

}

//Diese Methode wird von der Klasse Application überschrieben (siehe JAVAFX) 
//Hier wird die GUI initalisiert und an die Methode run() übergeben.
@Override
public void start(Stage primaryStage) throws Exception {
	this.primaryStage = primaryStage;
	
	FxUI fxUI = FxUI.createInstance(boardConfig);
	final GameImpl game = new GameImpl(fxUI);
	
		primaryStage.setScene(fxUI);
		primaryStage.setTitle("Diligent Squirrel");

     fxUI.getWindow().setOnCloseRequest(new EventHandler() {
    	 @Override
         public void handle(Event evt) {
             System.exit(-1);     
         }

     });
     
   primaryStage.show();   
   Launcher launcher = new Launcher(); 
   launcher.startGame(fxUI, game);    //fxui? 	
	
	
}

//Getter für gameMode; 1== konsole_alt, 2==konsole_neu, 3==javafx_gui
public static int getMode(){
	return gameMode;
	}
//Getter für GameLogger;
public static GameLogger logger(){
	return gameLogger;
}
}
