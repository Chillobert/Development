package prog2_a3.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import prog2_a3.Launcher;
import prog2_a3.fatsquirrel.console.GameCommandType;

public class CommandScanner {
    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private Class<?>[] commandTypes = new Class[]{null};
    private String input;
    
    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }
    

	public CommandScanner(GameCommandType[] commandTypeInfos, String input) {
		this.commandTypeInfos = commandTypeInfos;
		this.input = input;
		
	}


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