package prog2_a3.fatsquirrel.console;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import prog2_a3.*;
import prog2_a3.Launcher;
import prog2_a3.fatsquirrel.core.*;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.fatsquirrel.util.ui.console.ScanException;

public class GameImpl extends Game {
	private Command puffer;
    private ConsoleUI ui;
    private Command command;
    private FxUI fxUI;

    
    public GameImpl(){
        super();
        this.ui = new ConsoleUI();
    }
    
    public GameImpl (FxUI fxUi){
        super();
        this.fxUI = fxUi;
    }
    
    @Override
    protected void processInput(){
        XY inpWhile = this.input;
        
        if(Launcher.getSwitcher() == false){
            while (this.input == inpWhile) { // the loop over all commands with one input line for every command
            	
            	command = ui.getCommand();
                try{
                    if(command==null)
                        throw new ScanException();
                }
                catch(ScanException ScEx){
                    System.out.println("wrong input. Please use 'help' to show commands");
                }           	
                invokeCommand();
            }
        }     
        else{
            while (this.input == inpWhile && fxUI.giveCommand() != null) { // the loop over all commands with one input line for every command     // && this.getPuffer() != null
        	if (Launcher.getJavaFxMode() == true){
                    command = fxUI.giveCommand();	
                    fxUI.setCommand(null);
        	}
        	else{
                    command = this.getPuffer(); //ui.getCommand();
                    this.setPuffer(null);
        	}
                invokeCommand();
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
    
    public void mini(int energy, String direction){
        XY spawnDirection;
        switch(direction){
            case "w": case "W": spawnDirection = new XY(new int[]{0,-1});break;
            case "s": case "S": spawnDirection = new XY(new int[]{0,1});break;
            case "a": case "A": spawnDirection = new XY(new int[]{-1,0});break;
            case "d": case "D": spawnDirection = new XY(new int[]{1,0});break;
            default: spawnDirection = new XY(new int[]{0,0});break;
        }
        try{
            flattenedBoard.spawnChild(flattenedBoard.getMasterSquirrel(), spawnDirection, energy);
        }
        catch(NullPointerException NuEx){
            System.out.println("wrong input. Please refere to \"help\"");
        }
        catch(NotEnoughEnergyException EnEx){
            System.out.println("you don't have this much energy to spare");
        }
    }
    
    @Override
    protected void update(){
        if(this.input==null)
            super.flattenedBoard.getEntitySet().nextStepAll(flattenedBoard,new XY(new int[]{0,0}));
        else
            super.flattenedBoard.getEntitySet().nextStepAll(flattenedBoard,this.input);
        if(Launcher.getSwitcher() == true){
        this.input = null;}

    }

    @Override
    protected void render() {

    	if(Launcher.getJavaFxMode() == false)
            ui.render(flattenedBoard = state.getBoard().flatten());
    	else{
            fxUI.render(flattenedBoard = state.getBoard().flatten());
    	}

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

    //Method to get the fxUI
    public FxUI setfxUI(FxUI fxUI){
    	return this.fxUI = fxUI;
    	
    }
    
    private void invokeCommand(){
        if(command!=null){
            GameCommandType commandType = (GameCommandType) command.getCommandType();

            try{
                Method m0 = this.getClass().getDeclaredMethod(command.getCommandType().getName(), command.getCommandType().getParamTypes());
                m0.invoke(this, command.getParams());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                //error loggen;
            }
        }
    }

}