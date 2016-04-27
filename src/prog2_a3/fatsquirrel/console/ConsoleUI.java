package prog2_a3.fatsquirrel.console;

import java.util.Scanner;
import prog2_a3.interfaces.*;
import prog2_a3.fatsquirrel.core.*;

public class ConsoleUI implements UI {

    
    @Override
    public MoveCommand getCommand() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        input.hasNext();
        return new MoveCommand(input.next());
    }

    @Override
    public void render(BoardView view) {
        for(int i = 0; i<=view.getSize().getY();i++){
            for(int j = 0;j<=view.getSize().getX();j++){
                switch(view.getEntityType(j,i)){
                    case " ":System.out.print("--"); break;
                    case "Wall":System.out.print("Wa"); break;
                    case "BadBeast":System.out.print("BB");break;
                    case "BadPlant":System.out.print("BP");break;
                    case "GoodBeast":System.out.print("GB");break;
                    case "GoodPlant":System.out.print("GP");break;
                    case "GuidedMasterSquirrel":System.out.print("GS");break;
                    case "MiniSquirrel":System.out.print("MS");break;
                    default:System.out.print("FF");break;
                }
            }
            System.out.println();
        }   
    }
    
}
