package prog2_a3.fatsquirrel.console;
import java.util.Scanner;
import prog2_a3.interfaces.*;


public class ConsoleUI implements UI {

    @Override
    public String getCommand() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wählen Sie eine Bewegungsrichtung für ihr Eichhörnchen: ");
        return input.next();
    }

    @Override
    public void render(BoardView view) {
        System.out.println(game.state.board.toString());
    }
    
}
