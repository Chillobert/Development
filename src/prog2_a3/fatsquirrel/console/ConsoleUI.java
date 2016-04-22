package prog2_a3.fatsquirrel.console;
import java.util.Scanner;
import prog2_a3.fatsquirrel.core.XY;
import prog2_a3.interfaces.*;


public class ConsoleUI implements UI {

    @Override
    public XY getCommand() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        input.hasNext();
        int[] vector = new int[]{0,0};
        switch(input.next()){
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
        return new XY(vector);
    }

    @Override
    public void render(BoardView view) {
        System.out.println(game.state.board.toString());
    }
    
}
