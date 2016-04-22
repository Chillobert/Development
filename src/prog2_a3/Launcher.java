package prog2_a3;

import prog2_a3.fatsquirrel.core.*;

public class Launcher {

	public static void main(String[] args){
            Boolean play=true;
                //EntitySet entSet = new EntitySet();

            while(play){
                BadBeast meixner = new BadBeast(1,7,3);
                GuidedMasterSquirrel max = new GuidedMasterSquirrel(2,2,2);
                int x0 =meixner.getLocation().getX();
                int x1 = max.getLocation().getX();
                int y0 = meixner.getLocation().getY();
                int y1 = max.getLocation().getY();
                int currentDistance =  (Math.abs(x1-x0))>(Math.abs(y1-y0))?Math.abs(x1-x0):Math.abs(y1-y0);
                System.out.println(currentDistance);
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
                play=false;
            }
	}
}
