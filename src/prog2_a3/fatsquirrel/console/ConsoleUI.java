package prog2_a3.fatsquirrel.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import prog2_a3.interfaces.*;
import prog2_a3.fatsquirrel.util.ui.console.*;
public class ConsoleUI implements UI {

    private PrintStream outputStream;
    private BufferedReader inputReader;
    private Command command;
    private GameCommandType[] commandTypes;
    int counter = 0;
    private Command commandPuffer;

    public ConsoleUI() {
        this.outputStream = System.out;
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
        this.commandTypes = GameCommandType.values();
    }
    
    @Override
    public Command getCommand() {
        try {
            CommandScanner commandScanner = new CommandScanner(commandTypes,inputReader);
            command = commandScanner.next();
        } catch (IOException ioEx) {
            outputStream.println("Das war keine gültige Eingabe. probier es mal mit help");
        }
        return command;
    }
    
    public Command savePuffer(){
        try {
        	CommandScanner commandScanner = new CommandScanner(commandTypes,inputReader);
			commandPuffer = commandScanner.next();
		} catch (IOException e) {
			 outputStream.println("Das war keine gültige Eingabe. probier es mal mit help");
			
		}
    	
		return commandPuffer;
    	
    }

    @Override
    public void render(BoardView view) {
    	counter = counter + 1;
    	System.out.println("DurchlaufNr:" + counter); //testing
        for(int i = 0; i<=view.getSize().getY();i++){
            for(int j = 0;j<=view.getSize().getX();j++){
                switch(view.getEntityType(j,i)){
                    case " ":System.out.print("--"); break;
                    case "Wall":System.out.print("WA"); break;
                    case "BadBeast":System.out.print("BB");break;
                    case "BadPlant":System.out.print("BP");break;
                    case "GoodBeast":System.out.print("GB");break;
                    case "GoodPlant":System.out.print("GP");break;
                    case "GuidedMasterSquirrel":System.out.print("GS");break;
                    case "MiniSquirrel":System.out.print("MS");break;
                    case "MasterSquirrelBot":System.out.print("SB");break;
                    default:System.out.print("FF");break;
                }
            }
            System.out.println();
        }   
    }

    @Override
    public void message(String message) {
        System.out.println(message);
    }
    
}
