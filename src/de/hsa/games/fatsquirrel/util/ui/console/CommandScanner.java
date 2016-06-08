package de.hsa.games.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandScanner.
 */
public class CommandScanner {
    
    /** The command type infos. */
    private CommandTypeInfo[] commandTypeInfos;
    
    /** The input reader. */
    private BufferedReader inputReader;
    
    /** The command types. */
    private Class<?>[] commandTypes = new Class[]{null};
    
    /** The input. */
    private String input;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new command scanner.
     *
     * @param commandTypeInfos the command type infos
     * @param inputReader the input reader
     */
    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
        logger.log(Level.FINEST, "Objekt der Klasse CommandScanner wurde erstellt");
    }

	/**
	 * Instantiates a new command scanner.
	 *
	 * @param commandTypeInfos the command type infos
	 * @param input the input
	 */
	public CommandScanner(GameCommandType[] commandTypeInfos, String input) {
		this.commandTypeInfos = commandTypeInfos;
		this.input = input;	
	}

	/**
	 * Next.
	 *
	 * @return the command
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Command next() throws IOException{
        int numberOfParams = 0;
        String input = null;
        
        if(Launcher.getMode() == 3){
        	input = this.input;
        };
        if(Launcher.getMode() == 1 || Launcher.getMode() == 2){
        	input = inputReader.readLine();
        };
        
        String[] inputArr = input.split(" ");
        String name = inputArr[0];
        Object[] paramsParse = new Object[5];
        Command command = null;
        String[] params = Arrays.copyOfRange(inputArr, 1, inputArr.length);
                //Umwandeln der Parameter in gewünschten Typ
                for(int i=0;commandTypeInfos.length>i;i++){
                    if(name.equals(commandTypeInfos[i].getName())){
                        try{
                        this.commandTypes = commandTypeInfos[i].getParamTypes();
                        for(int j = 0; commandTypes.length>j; j++){
                            try{                                            //parse in private Methode auslagern
                                float d = Float.parseFloat(params[j]);
                                if(commandTypes[j]==int.class || commandTypes[j]==long.class){
                                    paramsParse[j] = (int)d;
                                }
                                else if(commandTypes[j]==float.class || commandTypes[j]==double.class)
                                    paramsParse[j] = d;
                                else
                                    paramsParse[j] = (commandTypes[j].cast(d));
                            }
                            catch(ScanException | NumberFormatException ScEx){
                                paramsParse[j] = params[j];
                            }
                        }
                    }
                        catch(ArrayIndexOutOfBoundsException | NullPointerException ArrEx){
                        	logger.log(Level.SEVERE, "Fehler:CommandScanner.next(); Zugriff auf Array au�erhalb der Addresierung");
                      
                        }
                    for(int j = 0;paramsParse[j]!=null;j++)
                        numberOfParams=j;
                    
                    Object[] returnParams = new Object[numberOfParams+1];
                    for(int j = 0;paramsParse[j]!=null;j++)
                        returnParams[j] = paramsParse[j];
                    
                    command = new Command(commandTypeInfos[i],returnParams);
                }
                }
                
        return command;
    }
}