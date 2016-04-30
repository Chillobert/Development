package prog2_a3.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;

public class GameCommandScanner {
    
    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    
    public GameCommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }
    
    public Command next() throws IOException{
        String input = inputReader.readLine();
        int indexName = input.indexOf(" ");
        String name = input;
        int indexPar1;
        String param1;
        String param2 = null;
        int parInt1 = 0;
        try{
            if(indexName!=-1){
                name = input.substring(0,indexName);
                indexPar1 = input.indexOf(" ",indexName+1);
                param1 = input.substring(indexName+1,indexPar1);
                param2 = input.substring(indexPar1+1,input.length());
                parInt1 = Integer.parseInt(param1);
            }
        }
        catch(ScanException | NullPointerException | NumberFormatException | StringIndexOutOfBoundsException ScEx ){
            System.out.println("wrong input. please refere to \"help\"");
        }
        Command command = null;
        for(int i=0;commandTypeInfos.length>i;i++){
            if((commandTypeInfos[i].getName() == null ? name == null : commandTypeInfos[i].getName().equals(name)) && !"".equals(name)){
                switch (name) {
                    case "help":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "exit":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "w":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "a":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "s":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "d":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "all":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "energy":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "mini":
                        command = new Command(commandTypeInfos[i],new Object[]{parInt1,param2});
                        break;
                    default:System.out.println("Kein gültiges Kommando");
                    break;
                }
            }
        }
        return command;
    }
}
