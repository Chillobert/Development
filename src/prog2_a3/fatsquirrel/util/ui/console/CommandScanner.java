package prog2_a3.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;
    
    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }
    
    public Command next() throws IOException{
        String input = inputReader.readLine();
        int indexName = input.indexOf(" ");
        String name = input;
        int indexPar1;
        String param1 = null;
        int indexPar2;
        String param2;
        int parInt1 = 0;
        int parInt2 = 0;
        float parFloat1 = 0;
        float parFloat2 = 0;
        try{
            if(indexName!=-1){
                name = input.substring(0,indexName);
                indexPar1 = input.indexOf(" ",indexName+1);
                param1 = input.substring(indexName+1,indexPar1);
                param2 = input.substring(indexPar1+1,input.length());
                if(name.equals("addf")){
                    parFloat1 = Float.parseFloat(param1);
                    parFloat2 = Float.parseFloat(param2);
                }
                if(name.equals("addi"))
                    parInt1 = Integer.parseInt(param1);
                if(name.equals("addi") | name.equals("echo"))
                    parInt2 = Integer.parseInt(param2);
            }
        }
        catch(ScanException | NullPointerException | NumberFormatException | StringIndexOutOfBoundsException ScEx ){
            System.out.println("wrong input. please refere to \"help\"");
        }
        Command command = null;
        for(int i=0;commandTypeInfos.length>i;i++){
            if(commandTypeInfos[i].getName() == null ? name == null : commandTypeInfos[i].getName().equals(name)){
                if (!"".equals(name)){
                    switch (name) {
                    case "help":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "exit":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "addi":
                            command = new Command(commandTypeInfos[i],new Object[]{parInt1,parInt2});
                            break;
                    case "addf":                      
                            command = new Command(commandTypeInfos[i],new Object[]{parFloat1,parFloat2});
                           break;
                    case "echo":
                           command = new Command(commandTypeInfos[i],new Object[]{param1,parInt2});
                           break;
                    default:System.out.println("Kein gültiges Kommando");
                        break;
                    }
                }
            }
        }
        return command;
    }
}