package prog2_a3;

import prog2_a3.fatsquirrel.console.*;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.Game;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import java.util.Timer;
import java.util.TimerTask;
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


public class Launcher extends Application{
private static long timestamp_1;
private static long timestamp_2;
private GameImpl game;
private Calendar calendar;
private Command commandPuffer;
public static boolean switcher = true; //false = alt; //true = neu;
public static boolean javafxmode = false; //javafxmode
private BoardConfig boardConfig;

private FxUI fxUI;

    public Launcher(){        
        this.boardConfig = new BoardConfig();
        this.calendar = new GregorianCalendar();
        this.game = new GameImpl();
    }
	
    public static void main(String[] args){
            Launcher launcher = new Launcher();
		if(javafxmode == true){
                    launch(args);
		}
                
		else {
					
                    if( switcher == true){
           
                        launcher.startGame();
                    }
                    if (switcher == false) {
			
			launcher.game.run();
                    }
		}
            }
	
    private void startGame() { 
	
	Timer timer = new Timer();
	timer.schedule(new TimerTask(){

		@Override
		public void run() {
			try {
				timestamp_1 = calendar.getTimeInMillis();
				game.run();
				timestamp_2 = calendar.getTimeInMillis();	
				//do{
				//	commandPuffer = game.getUI().getPuffer();								
				//} while(commandPuffer == null || timestamp_1 + 1000 /game.getFPS() - timestamp_2 <= 10);
				Thread.sleep((timestamp_1 + 1000 /game.getFPS()) - timestamp_2); //schlafe Startzeit+Durchl�ufe/Sekunde - Endzeit
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
								
		}
		
	},1000,1 );
	
	while(true){
		commandPuffer = game.getUI().savePuffer();
		if(commandPuffer != null){
			game.setPuffer(commandPuffer);
			game.process();
			game.setPuffer(null); //commandPuffer = null
			commandPuffer = null;
		}
	//	game.setPuffer(game.getUI().savePuffer());
		
		//game.setPuffer(game.getUI().savePuffer());
		
		if( game.getPuffer() != null)
		System.out.println(game.getPuffer().getCommandType().getName());
	}

}

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
	final Game game = new GameImpl(fxUI);
	
     primaryStage.setScene(fxUI);
     primaryStage.setTitle("Game");
     primaryStage.setHeight(500);
     primaryStage.setWidth(500);
     
     
     fxUI.getWindow().setOnCloseRequest(new EventHandler() {
         public void handle(WindowEvent evt) {
             System.exit(-1);     
         }

		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			
		}
     });
     primaryStage.show();   
     
     //startGame();    	
	
	
}
}
