package prog2_a3;


public class Launcher {

	public static void main(String[] args){
            Boolean play=true;
                //EntitySet entSet = new EntitySet();

            while(play){
                //entSet.add("BadBeast",1,2);
                //entSet.add("GoodBeast" ,2,3);
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
                board.toString();
                
                
                System.out.println(board.entSet.toString());
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
