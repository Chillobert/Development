package prog2_a2;

public class Launcher {

	public static void main(String[] args){
            Boolean play=true;
            /*
		MasterSquirrel fred = new MasterSquirrel(1, 1, 2);
                MiniSquirrel karl = new MiniSquirrel(3,100,3,4,'a');
                GuidedMasterSquirrel toni = new GuidedMasterSquirrel(2,2,3);
                fred.updateEnergy(-100);
		BadBeast jack = new BadBeast(1, 2, 4);
                System.out.println("X:"+fred.getPos()[0]);
		fred.randMove(new int[]{1,5});
                karl.nextStep();
                jack.nextStep();
		System.out.println(jack.energy);
                System.out.println(fred.toString());
                System.out.println(karl.toString());
                System.out.println(jack.toString());
                EntitySet ent = new EntitySet();
                ent.add("BadPlant",0,1,2);
                System.out.println(ent.outputString());*/
            
                EntitySet entSet = new EntitySet();

            while(play){
                entSet.add("BadBeast", 0,1,2);
                entSet.add("GoodBeast" ,1,2,3);
                entSet.add("GoodPlant",2,3,4);
                entSet.add("BadPlant",3,4,5);
                entSet.add("GuidedMasterSquirrel",4,3,4);
                entSet.add("Wall", 5, 6, 7);
                /*GuidedMasterSquirrel karl = new GuidedMasterSquirrel(6,3,4);
                MiniSquirrel fritz = karl.createDescendant(6, 100, 3, 5);
                BadBeast meixner = new BadBeast(7,4,6);
                Wall Piechler = new Wall(8,5,6);
                Wall fritzl = new Wall(9,6,7);
                System.out.println(Piechler.equals(fritzl));
                System.out.println(karl.checkDescendant(fritz));*/
                System.out.println(entSet.toString());
                entSet.nextStepAll();
                System.out.println(entSet.toString());
                //ent.delete(3);
                for(int i = 0; i<=3;i++){
                    entSet.nextStepAll();
                System.out.println(entSet.toString());
                }
                play=false;
            }
	}
}
