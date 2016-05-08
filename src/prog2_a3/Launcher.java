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
private GameImpl game = new GameImpl();
private Calendar calendar = new GregorianCalendar();
private Command commandPuffer;
public static boolean switcher = true; //false = alt; //true = neu;
public static boolean javafxmode = true; //javafxmode
private BoardConfig boardConfig = new BoardConfig();
private Stage primaryStage = new Stage();
private FxUI fxUI;


	
	public static void main(String[] args){
		if(javafxmode == true){
		launch(args);
			
		}
		else {
					
		if( switcher == true){
            Launcher launcher = new Launcher();
            launcher.startGame();
		}
		if (switcher == false) {
			Launcher launcher = new Launcher();
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
				Thread.sleep((timestamp_1 + 1000 /game.getFPS()) - timestamp_2); //schlafe Startzeit+Durchläufe/Sekunde - Endzeit
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
	this.primaryStage = primaryStage;
	// TODO Auto-generated method stub
	FxUI fxUI = FxUI.createInstance(boardConfig);
	final Game game = new GameImpl();
	
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
