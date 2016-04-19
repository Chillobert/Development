package prog2_a3;

import java.util.Arrays;
import prog2_a3.fatsquirrel.core.Board;

public class Launcher {

	public static void main(String[] args){
            Boolean play=true;
                //EntitySet entSet = new EntitySet();

            while(play){
                //BadBeast meixner = new BadBeast(0,7,6);
                //BadBeast otto = new BadBeast(1,5,4);
                //GoodBeast bla = new GoodBeast(2,3,4);
                //entSet.add("GoodPlant",3,4);
                //entSet.add("BadPlant",4,5);
                //entSet.add("GuidedMasterSquirrel",3,4);
                //entSet.add("Wall", 6, 7);
                /*GuidedMasterSquirrel karl = new GuidedMasterSquirrel(6,3,4);
                MiniSquirrel fritz = karl.createDescendant(6, 100, 3, 5);
                BadBeast otto = new BadBeast(7,4,6);
                Wall Piechler = new Wall(8,5,6);
                Wall franz = new Wall(9,6,7);
                System.out.println(Piechler.equals(franz));
                System.out.println(karl.checkDescendant(fritz));*/
                Board board = new Board();
                System.out.println(board.toString());
                
                
                //System.out.println(board.entSet.toString());
                //board.entSet.nextStepAll();
                // System.out.println(board.entSet.toString());
                //ent.delete(3);
                //for(int i = 0; i<=3;i++){
                //  board.entSet.nextStepAll();
                //System.out.println(board.entSet.toString());
                //}
                play=false;
            }
	}
}
