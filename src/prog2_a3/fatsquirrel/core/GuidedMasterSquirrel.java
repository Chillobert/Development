package prog2_a3.fatsquirrel.core;


public class GuidedMasterSquirrel extends MasterSquirrel {
    
    public String in;
    public GuidedMasterSquirrel(int id, int x, int y){
        super(id,x,y);
    }
    
    @Override
    public void nextStep(){
        int[] vector = new int[]{0,0};
        switch(in){
            case "a":vector[0]=-1;break;
            case "s":vector[1]=1;break;
            case "d":vector[0]=1;break;
            case "w":vector[1]=-1;break;
            case "q":vector[0]=-1;vector[1]=-1;break;
            case "e":vector[0]=1;vector[1]=-1;break;
            case "c":vector[0]=1;vector[1]=1;break;
            case "y":vector[0]=-1;vector[1]=1;break;
            default: System.out.println("Keine gültige Richtung");break;
        }
            this.move(new XY(vector));
    }
}
