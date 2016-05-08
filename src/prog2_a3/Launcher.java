package prog2_a3;

import prog2_a3.fatsquirrel.console.*;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.fatsquirrel.util.ui.console.CommandScanner;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Launcher{
private boolean isRunning;
private static long timestamp_1;
private static long timestamp_2;
private GameImpl game = new GameImpl();
private Calendar calendar = new GregorianCalendar();
private Command commandPuffer;
public static boolean switcher = true; //false = alt; //true = neu;


	
	public static void main(String[] args){
		
		
		if( switcher == true){
            Launcher launcher = new Launcher();
            launcher.startGame();
		}
		if (switcher == false) {
			Launcher launcher = new Launcher();
			launcher.game.run();
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
}
