package prog2_a3.fatsquirrel.console;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;
import java.util.logging.Level;

import prog2_a3.*;
import prog2_a3.Launcher;
import prog2_a3.fatsquirrel.core.EntitySet;
import prog2_a3.fatsquirrel.core.Game;
import prog2_a3.fatsquirrel.core.GameLogger;
import prog2_a3.fatsquirrel.core.MasterSquirrel;
import prog2_a3.fatsquirrel.core.MiniSquirrel;
import prog2_a3.fatsquirrel.core.NotEnoughEnergyException;
import prog2_a3.fatsquirrel.core.XY;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.fatsquirrel.util.ui.console.ScanException;

public class GameImpl extends Game {
    private Command puffer;
    private ConsoleUI ui;
    private Command command;
    private FxUI fxUI;
    private static final GameLogger logger = new GameLogger();
    int aktuelleRunde =0;
    private EntitySet entSet;
    
    public GameImpl(){
        super();
        this.ui = new ConsoleUI();
        logger.log(Level.FINEST, "Object der Klasse GameImpl- ConsoleUI - erstellt");
    }
    
    public GameImpl (FxUI fxui){
        super();
        this.fxUI = fxui;
        logger.log(Level.FINEST, "Object der Klasse GameImpl - FxUI - erstellt");
    }
    
    @Override
    protected void processInput(){
        XY inpWhile = this.input;
        
            switch (Launcher.getMode()) {
                case 1:
                    while (this.input == inpWhile) { // the loop over all commands with one input line for every command
                        
                        command = ui.getCommand();
                        try{
                            if(command==null)
                                throw new ScanException();
                        }
                        catch(ScanException ScEx){
                        	logger.log(Level.WARNING, "Warnung: GameImpl.processInput(); Falsche Eingabe des Spielers");
                            System.out.println("wrong input. Please use 'help' to show commands");
                        }
                        invokeCommand();
                    }       break;
                case 2:
                    while (this.input == inpWhile) { // the loop over all commands with one input line for every command     // && this.getPuffer() != null
                        command = this.getPuffer(); //ui.getCommand();
                        this.setPuffer(null);
                        
                        invokeCommand();
                    }       break;
                case 3:
                    while(fxUI.giveCommand() != null){
                        command = fxUI.giveCommand();
                        fxUI.setCommand(null);
                        
                        invokeCommand();
                    }   break;
                default:
                    break;
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
        this.input = new XY(new int[]{-1,0});
    }
    
    private void right(){
        this.input = new XY(new int[]{1,0});
    }
    
    public void up(){
        this.input = new XY(new int[]{0,-1});
    }
    
    public void down(){
        this.input = new XY(new int[]{0,1});;
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
            logger.log(Level.FINE, "Minisquirrel wurde erstellt");
        }
        catch(NullPointerException NuEx){
        	logger.log(Level.WARNING, "Warnung: Falsche Eingabe des Spielers");
            System.out.println("wrong input. Please refere to \"help\"");
        }
        catch(NotEnoughEnergyException EnEx){
        	logger.log(Level.WARNING, "Warnung: MasterSquirrel hat nicht gen�gend Energie f�r Spawn");
            System.out.println("you don't have this much energy to spare");
        }
    }

    @Override
    protected void update(){
        this.entSet = this.flattenedBoard.getEntitySet();       
        if(this.input==null)
            try {
                entSet.nextStepAll(flattenedBoard,new XY(new int[]{0,0}),flattenedBoard.getBoard().getConfig().getStepsPerRounds());
        } catch (InterruptedException ex) {
        	logger.log(Level.SEVERE, "Fehler: GameImpl.update(); InterrupedException");
        }
        else
            try {
                entSet.nextStepAll(flattenedBoard,this.input,flattenedBoard.getBoard().getConfig().getStepsPerRounds());
        } catch (InterruptedException ex) {
        	logger.log(Level.SEVERE, "Fehler: GameImpl.update(); InterrupedException");
        }
        if(Launcher.getMode() == 2 || Launcher.getMode() == 3){
            this.input = null;}
        
    }

    @Override
    protected void render() {

    	if(Launcher.getMode() == 1 || Launcher.getMode() == 2)
            ui.render(flattenedBoard = state.getBoard().flatten());
        else if(Launcher.getMode() == 3){
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
                logger.log(Level.SEVERE, "Fehler: GameImpl.invokeCommand(); Fehler bei Invoke");
            }
        }
    }

}
