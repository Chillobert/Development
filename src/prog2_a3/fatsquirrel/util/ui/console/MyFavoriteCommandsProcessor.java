package prog2_a3.fatsquirrel.util.ui.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class MyFavoriteCommandsProcessor {
    private PrintStream outputStream = System.out;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    Command command;
    MyFavoriteCommandType[] commandTypes = MyFavoriteCommandType.values();
    
    public void process() throws IOException, ScanException{
        CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader);
        Object[] params = null;
        while (true) { // the loop over all commands with one input line for every command
            command = commandScanner.next();
            try{
                if(command==null)
                    throw new ScanException();
            }
            catch(ScanException ScEx){
                System.out.println("wrong input. Please use 'help' to show commands");
            }
            if(command!=null){
                MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();
           
                if(commandType != MyFavoriteCommandType.EXIT && commandType != MyFavoriteCommandType.HELP)
                    params = command.getParams();
    
                switch (commandType) {
                case EXIT:
                    System.exit(0);
                case HELP: 
                    help();
                    break;
                case ADDI:
                    addi((int)params[0],(int)params[1]);
                    break;
                case ADDF:
                    addf((float)params[0],(float)params[1]);
                    break;
                case ECHO:
                    echo((String)params[0],(int)params[1]);
                    break;
                default:break;
                }
            }
        }
    }
    
    private void help(){
        for(int i=0;commandTypes.length>i;i++){
            outputStream.println(commandTypes[i].getName()+commandTypes[i].getHelpText());
        }
    }
    
    private void addi(int int1, int int2){
        int sum=0;
            sum = int1+int2;
            outputStream.println("Ihre Zahl lautet: "+(sum));
    }
    
    private void addf(float float1, float float2){
        outputStream.println("Ihre Zahl lautet: "+(float1+float2));
    }
    
    private void echo(String string, int times){
        while(times-->0)
            outputStream.println(string);
    }
    public static void main(String[] args) throws IOException{
        MyFavoriteCommandsProcessor myFav = new MyFavoriteCommandsProcessor();
        myFav.process();
    }
}
