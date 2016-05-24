package prog2_a3.fatsquirrel.core;

public class MoveCommand {
    
    String input;
    
    public MoveCommand(String input){
        this.input = input;
    }
    
    public XY convertCommand(){
        int[] vector = new int[]{0,0};
    
         switch(this.input){
            case "a":vector[0]=-1;vector[1]=0;break;
            case "s":vector[0]=0; vector[1]=1;break;
            case "d":vector[0]=1; vector[1]=0;break;
            case "w":vector[0]=0; vector[1]=-1;break;
            default: System.out.println("Keine gültige Richtung");break;
         }
         return new XY(vector);
    }
}
