package prog2_a4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.fatsquirrel.util.ui.console.CommandScanner;
import prog2_a3.fatsquirrel.util.ui.console.ScanException;


public class MyFavoriteCommandsProcessor {
    private PrintStream outputStream = System.out;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    Command command;
    MyFavoriteCommandType[] commandTypes = MyFavoriteCommandType.values();
    
    public void process() throws IOException, ScanException, NoSuchMethodException{
        Class comType = commandTypes[0].getClass();
        Method[] methods = comType.getMethods();
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
                    try{
                        Method m1 = commandTypes[0].getClass().getDeclaredMethod(command.getCommandType().getName());
                        m1.invoke(commandTypes[0]);
                    }catch(NoSuchMethodException NoSuEx){
                        try{
                            Method m0 = commandTypes[0].getClass().getDeclaredMethod(command.getCommandType().getName(), command.getCommandType().getParamTypes());
                            m0.invoke(commandTypes[0], params);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            System.out.println("Fehler bei Aufruf von m0");
                    }            
                        
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.out.println("Falsches Objekt bei Methodenaufruf m1");
                    }
            }
        }
    }
    
    public static void main(String[] args) throws IOException, ScanException, NoSuchMethodException{
        MyFavoriteCommandsProcessor myFav = new MyFavoriteCommandsProcessor();
        myFav.process();
    }
}
