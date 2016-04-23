package prog2_a3.fatsquirrel.console;
import java.util.Scanner;
import prog2_a3.interfaces.*;


public class ConsoleUI implements UI {

    @Override
    public String getCommand() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        input.hasNext();
        return input.next();
    }

    @Override
    public void render(BoardView view) {
        for(int i = 0; i<=view.getSize().getX();i++){
            for(int j=0;j<=view.getSize().getY();j++)
                if(!" ".equals(view.getEntityType(i, j)))
                    System.out.println(view.getEntityType(i,j) + ", X: "+i+", Y: "+j);
        }
    }
    
}
