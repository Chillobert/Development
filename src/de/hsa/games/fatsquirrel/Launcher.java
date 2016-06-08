package de.hsa.games.fatsquirrel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.console.*;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.logger.GameLogger;
import de.hsa.games.fatsquirrel.util.ui.console.Command;
import de.hsa.games.util.ui.gui.FxUI;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.stage.Stage;



// TODO: Auto-generated Javadoc
/**
 * The Class Launcher.
 */
public class Launcher extends Application{

/** The timestamp_1. */
private static long timestamp_1;

/** The timestamp_2. */
private static long timestamp_2;

/** The game. */
private GameImpl game;

/** The calendar. */
private Calendar calendar;

/** The command puffer. */
private Command commandPuffer;

/** The game mode. */
private static int gameMode = 3; //gameMode Switcher: 1== konsole_alt; 2== konsole_neu; 3== javafx_gui

/** The level. */
private static Level level; //Level fuer Logging (= Level.FINE)

/** The board config. */
private BoardConfig boardConfig = new BoardConfig();

/** The primary stage. */
private Stage primaryStage;

/** The fx ui. */
private FxUI fxUI;

/** The Constant logger. */
private static final GameLogger logger = new GameLogger();


    /**
     * Instantiates a new launcher.
     */
    public Launcher(){  	
        this.boardConfig = new BoardConfig();
        this.calendar = new GregorianCalendar();
        if (gameMode != 3){
        	this.game = new GameImpl();
        };
        if (gameMode == 3){
        	primaryStage = new Stage();
        }
        logger.log(Level.FINEST, "Object der Klasse Launcher erstellt");
    }
	
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args){
        logger.log(Level.INFO, "Spiel gestartet");

        
       //Konsole_alt    
    	if(gameMode == 1){
    	Launcher launcher = new Launcher();
    	logger.log(level.CONFIG, "Spiel initialisieren: Modus = Konsole_alt");
    	launcher.game.run();
    	}
    	//Konsole_neu
    	else if (gameMode == 2){
    	Launcher launcher = new Launcher();
    	logger.log(level.CONFIG, "Spiel initialisieren: Modus = Konsole_neu");
    	launcher.startGame(null, launcher.game);
    	}
    	//javafx_GUI
    	else if (gameMode == 3){
        logger.log(level.CONFIG, "Spiel initialisieren: Modus = JavaFX_GUI");
    	launch(args);	
    	}
    	
            }
    
	//Diese Methode gibt den Spielablauf wieder. 
    /**
	 * Start game.
	 *
	 * @param fxUI the fx ui
	 * @param game the game
	 */
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

                        Thread.sleep((timestamp_1 + 1000 /game.getFPS()) - timestamp_2); //schlafe Startzeit+1000ms/gew�nschteFPS - Endzeit

                    } catch (InterruptedException e) {
                        logger.log(Level.SEVERE, "Fehler: Launcher.startGame.run(); InterrupedException");
                        e.printStackTrace();
                    }
		}		
	},1000,1 ); // Delay = 1000, period = 1
		if(Launcher.gameMode == 2){ //Launcher.gameMode == 1 || 
                    while(true){
                        commandPuffer = game.getUI().getCommand();
                        if(commandPuffer != null){
                            game.setPuffer(commandPuffer);
                            game.process();
                            game.setPuffer(null); 
                            commandPuffer = null;
                        }
                    }

                }

}

//Diese Methode wird von der Klasse Application �berschrieben (siehe JAVAFX) 
/* (non-Javadoc)
 * @see javafx.application.Application#start(javafx.stage.Stage)
 */
//Hier wird die GUI initalisiert und an die Methode run() �bergeben.
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
    		 logger.log(Level.INFO, "Spiel beendet");
             System.exit(-1);     
         }

     });
     
   primaryStage.show();
   Launcher launcher = new Launcher();
   launcher.startGame(fxUI, game);
}

/**
 * Gets the mode.
 *
 * @return the mode
 */
//Getter f�r gameMode; 1== konsole_alt, 2==konsole_neu, 3==javafx_gui
public static int getMode(){
	return gameMode;
	}

}
