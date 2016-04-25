package prog2_a4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;
    
    public CommandScanner(CommandTypeInfo[] commandTypeInfos){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }
    
    public void next(){   //Command als Rückgabewert
        
    }
    
    public void CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader) throws IOException{
        
        String input = inputReader.readLine();
        int indexName = input.indexOf(" ");
        String name = input.substring(0,indexName);
        int indexPar1 = input.indexOf(input.substring(indexName+1,input.length()));
        String param1 = input.substring(indexName+1,indexPar1);
        int indexPar2 = input.indexOf(input.substring(indexPar1+1,input.length()));
        String param2 = input.substring(indexPar1+1,indexPar2);
        Command command;
        for(int i=0;commandTypeInfos[i] != null;i++){
            if(commandTypeInfos[i].getName() == null ? name == null : commandTypeInfos[i].getName().equals(name)){
                if (null != name)switch (name) {
                    case "help":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "exit":
                        command = new Command(commandTypeInfos[i]);
                        break;
                    case "addi":
                        if((param1.getClass().isInstance(Integer.class)) && (param2.getClass().isInstance(Integer.class))){
                            command = new Command(commandTypeInfos[i],new Object[]{param1,param2});
                        }   break;
                    case "addf":
                        if((param1.getClass().isInstance(Float.class)) && (param2.getClass().isInstance(Float.class))){
                            command = new Command(commandTypeInfos[i],new Object[]{param1,param2});
                        }   break;
                    case "echo":
                        if((param1.getClass().isInstance(String.class)) && (param2.getClass().isInstance(Integer.class))){
                            command = new Command(commandTypeInfos[i],new Object[]{param1,param2});
                        }   break;
                    default:System.out.println("Kein gültiges Kommando");
                        break;
                }
            }
                
        }
    }
    
}
