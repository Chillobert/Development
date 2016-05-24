package prog2_a2;

import java.util.Scanner;

public class GuidedMasterSquirrel extends MasterSquirrel {
    
    public GuidedMasterSquirrel(int id, int x, int y){
        super(id,x,y);
    }
    
    public void moveSquirrel(){
        Scanner input = new Scanner(System.in);
        String in;
        int[] vector = new int[]{0,0};
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        in = input.next();
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
            this.move(vector);
    }
    
    private void move(int[] vector){
        loc = new XY(vector);
    }
}
