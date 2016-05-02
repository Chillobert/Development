package prog2_a3.fatsquirrel.console;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import prog2_a3.fatsquirrel.core.*;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.fatsquirrel.util.ui.console.ScanException;

public class GameImpl extends Game {
	private Command puffer;
    private ConsoleUI ui;
    private Command command;
    
    public GameImpl(){
        super();
        this.ui = new ConsoleUI();
    }
    
 @Override
    protected void processInput(){
        GameCommandType[] commandTypes = GameCommandType.values();
        Object[] params = null;
        XY inpWhile = this.input;
        while (this.input == inpWhile) { // the loop over all commands with one input line for every command
            command = this.getPuffer(); //ui.getCommand();
           // try{
             //   if(command==null)
               //     throw new ScanException();
           // }
           // catch(ScanException ScEx){
           //     System.out.println("wrong input. Please use 'help' to show commands");
           // }
            if(command!=null){
                GameCommandType commandType = (GameCommandType) command.getCommandType();
           
                if(commandType != GameCommandType.EXIT && commandType != GameCommandType.HELP)
                    params = command.getParams();
    
                try{
                        Method m1 = this.getClass().getDeclaredMethod(command.getCommandType().getName());
                        m1.invoke(this);
                    }catch(NoSuchMethodException NoSuEx){
                        try{
                            Method m0 = this.getClass().getDeclaredMethod(command.getCommandType().getName(), command.getCommandType().getParamTypes());
                            m0.invoke(this, params);
                        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            System.out.println("Fehler bei Aufruf von m0");
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Logger.getLogger(GameImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }            
                        
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        System.out.println("Falsches Objekt bei Methodenaufruf m1");
                    }
                }
            }
        }
    
    private void help(){
        for(int i=0;GameCommandType.values().length>i;i++){
            System.out.println(GameCommandType.values()[i].getName()+GameCommandType.values()[i].getHelpText());
        }
    }
    
    private void exit(){
        System.exit(0);
    }
    
    private void all (){
        System.out.println(this.flattenedBoard.getEntitySet().toString());
    }
    
    private void left(){
        this.input = flattenedBoard.getMasterSquirrel().getLocation().moveLeft();
    }
    
    private void right(){
        this.input = flattenedBoard.getMasterSquirrel().getLocation().moveRight();
    }
    
    public void up(){
        this.input = flattenedBoard.getMasterSquirrel().getLocation().moveUp();
    }
    
    public void down(){
        this.input = flattenedBoard.getMasterSquirrel().getLocation().moveDown();
    }
    
    public void energy(){
        System.out.println("Die aktuelle Energie unseres Squirrel ist: "+flattenedBoard.getSquirrelEnergy());
    }
    
    public void mini(){
         try{
            flattenedBoard.spawnChild(flattenedBoard.getMasterSquirrel(), new XY(new int[]{(int)(Math.round((Math.random()*2)-1)),(int)(Math.round((Math.random()*2)-1))}), (int)command.getParams()[0]);
            }
            catch(NullPointerException | NotEnoughEnergyException NuEx){
                System.out.println("wrong input. Please refere to \"help\"");
            }
    }
    
    @Override
    protected void update(){
        super.flattenedBoard.getEntitySet().nextStepAll(flattenedBoard,this.input);
    }

    @Override
    protected void render() {
        ui.render(flattenedBoard = state.getBoard().flatten());
    }
    public int getFPS(){
    	return this.FPS;
    }
    public ConsoleUI getUI(){
    	return this.ui;
    }
    public void setPuffer(Command input){
    	this.puffer = input;
    }
    public Command getPuffer(){
    	return this.puffer;
    }
}
