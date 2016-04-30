package prog2_a3;

import prog2_a3.fatsquirrel.console.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Launcher extends TimerTask{
private static int FPS = 60;
private boolean isRunning;
private static long timestamp_1;
private static long timestamp_2;
private GameImpl game = new GameImpl();


	
	public static void main(String[] args){
            Launcher launcher = new Launcher();
            launcher.game.run();
		/*	
            while(true){
                Boolean play=true;
                //EntitySet entSet = new EntitySet(); 

            TimerTask task = new Launcher();
    		Timer timer = new Timer(true);
    		
    		timer.scheduleAtFixedRate(task, 0, 1000 / FPS);
    		System.out.println("TimerTask begins! :" + new Date());
    		
    		try {
    			Thread.sleep(200);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		timer.cancel();
    		System.out.println("TimerTask cancelled! :" + new Date());
    		try {
    			Thread.sleep(300);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}}*/
    		
         
            /*  while(play){
                GameImpl game = new GameImpl();
                
                game.run();
                BadBeast meixner = new BadBeast(1,7,3);
                GuidedMasterSquirrel max = new GuidedMasterSquirrel(2,2,2);
                int x0 =meixner.getLocation().getX();
                int x1 = max.getLocation().getX();
                int y0 = meixner.getLocation().getY();
                int y1 = max.getLocation().getY();
                int currentDistance =  (Math.abs(x1-x0))>(Math.abs(y1-y0))?Math.abs(x1-x0):Math.abs(y1-y0);
                System.out.println(currentDistance);*/
                //BadBeast otto = new BadBeast(1,5,4);
                //GoodBeast bla = new GoodBeast(2,3,4);
                //entSet.add("GoodPlant",3,4);
                //entSet.add("BadPlant",4,5);
                //entSet.add("GuidedMasterSquirrel",3,4);
                //entSet.add("Wall", 6, 7);

                //Wall franz = new Wall(9,6,7);
               // System.out.println(Piechler.equals(franz));
               // System.out.println(karl.checkDescendant(fritz));
               // Board board = new Board();
               // System.out.println(board.toString());
                //board.entSet.delete(2);
                //System.out.println(board.entSet.getEntityArray()[0].getName().equals("GuidedMasterSquirrel"));
                //System.out.println((board.flatten().toString()));
               
                
                //System.out.println(board.entSet.toString());
                //board.entSet.nextStepAll();
                // System.out.println(board.entSet.toString());
                //ent.delete(3);
                //for(int i = 0; i<=3;i++){
                //  board.entSet.nextStepAll();
                //System.out.println(board.entSet.toString());
                //}
            // }
	}

	@Override
	public void run() {
		System.out.println("Start time:" + new Date());
		startGame();
		System.out.println("End time:" + new Date());
		// TODO Auto-generated method stub
		
	}
	
	private  void startGame(){
		game.run();
		
	}
}
