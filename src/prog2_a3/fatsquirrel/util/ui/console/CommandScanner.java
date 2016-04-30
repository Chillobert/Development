package prog2_a3.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private Class[] commandTypes = new Class[]{null};
    
    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }
    
    public Command next() throws IOException{
        String input = inputReader.readLine();
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
                            try{
                                Float d = Float.parseFloat(params[j]);
                                if(commandTypes[j]==java.lang.Integer.class || commandTypes[j]==java.lang.Long.class){
                                    paramsParse[j] = d.intValue();
                                }
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
                        command = new Command(commandTypeInfos[i],paramsParse);
                    }
                }
        return command;
    }
}