package de.hsa.games.fatsquirrel.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.Game;
import de.hsa.games.fatsquirrel.Launcher;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.core.EntitySet;
import de.hsa.games.fatsquirrel.core.NotEnoughEnergyException;
import de.hsa.games.fatsquirrel.logger.GameLogger;
import de.hsa.games.fatsquirrel.util.ui.console.Command;
import de.hsa.games.fatsquirrel.util.ui.console.ScanException;
import de.hsa.games.util.ui.gui.FxUI;


// TODO: Auto-generated Javadoc
/**
 * The Class GameImpl.
 */
public class GameImpl extends Game {
    
    /** The puffer. */
    private Command puffer;
    
    /** The ui. */
    private ConsoleUI ui;
    
    /** The command. */
    private Command command;
    
    /** The fx ui. */
    private FxUI fxUI;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /** The aktuelle runde. */
    int aktuelleRunde =0;
    
    /** The ent set. */
    private EntitySet entSet;
    
    /** The current steps. */
    private int currentSteps=0;
    
    /**
     * Instantiates a new game impl.
     */
    public GameImpl(){
        super();
        this.ui = new ConsoleUI();
        logger.log(Level.FINEST, "Object der Klasse GameImpl- ConsoleUI - erstellt");
    }
    
    /**
     * Instantiates a new game impl.
     *
     * @param fxui the fxui
     */
    public GameImpl (FxUI fxui){
        super();
        this.fxUI = fxui;
        logger.log(Level.FINEST, "Object der Klasse GameImpl - FxUI - erstellt");
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Game#processInput()
     */
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
    
    /**
     * Help.
     */
    private void help(){
        for(int i=0;GameCommandType.values().length>i;i++){
            System.out.println(GameCommandType.values()[i].getName()+GameCommandType.values()[i].getHelpText());
        }
    }
    
    /**
     * Exit.
     */
    private void exit(){
    	state.save();
        System.exit(0);
    }
    
    /**
     * All.
     */
    private void all (){
        System.out.println(this.flattenedBoard.getEntitySet().toString());
    }
    
    /**
     * Left.
     */
    private void left(){
        this.input = new XY(new int[]{-1,0});
    }
    
    /**
     * Right.
     */
    private void right(){
        this.input = new XY(new int[]{1,0});
    }
    
    /**
     * Up.
     */
    public void up(){
        this.input = new XY(new int[]{0,-1});
    }
    
    /**
     * Down.
     */
    public void down(){
        this.input = new XY(new int[]{0,1});;
    }
    
    /**
     * Energy.
     */
    public void energy(){
        System.out.println("Die aktuelle Energie unseres Squirrel ist: "+flattenedBoard.getSquirrelEnergy());
    }
    
    /**
     * Mini.
     *
     * @param energy the energy
     * @param direction the direction
     */
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

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Game#update()
     */
    @Override
    protected void update(){
        this.entSet = this.flattenedBoard.getEntitySet();       
        if(this.input==null)
            try {
                entSet.nextStepAll(flattenedBoard,new XY(new int[]{0,0}));
        } catch (InterruptedException ex) {
        	logger.log(Level.SEVERE, "Fehler: GameImpl.update(); InterrupedException");
        }
        else
            try {
                entSet.nextStepAll(flattenedBoard,this.input);
        } catch (InterruptedException ex) {
        	logger.log(Level.SEVERE, "Fehler: GameImpl.update(); InterrupedException");
        }
        if(Launcher.getMode() == 2 || Launcher.getMode() == 3){
            this.input = null;
        }
        currentSteps++;
        if(currentSteps >= this.state.getBoard().getConfig().getStepsPerRounds()){
            state.update();
            currentSteps = 0;
        }
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.Game#render()
     */
    @Override
    protected void render() {

    	if(Launcher.getMode() == 1 || Launcher.getMode() == 2)
            ui.render(flattenedBoard = state.getBoard().flatten());
        else if(Launcher.getMode() == 3){
            fxUI.render(flattenedBoard = state.getBoard().flatten());
    	}

    }

    /**
     * Gets the fps.
     *
     * @return the fps
     */
    public int getFPS(){
    	return this.FPS;
    }

    /**
     * Gets the ui.
     *
     * @return the ui
     */
    public ConsoleUI getUI(){
    	return this.ui;
    }
    
    /**
     * Sets the puffer.
     *
     * @param input the new puffer
     */
    public void setPuffer(Command input){
    	this.puffer = input;
    }
    
    /**
     * Gets the puffer.
     *
     * @return the puffer
     */
    public Command getPuffer(){
    	return this.puffer;
    }

    /**
     * Setfx ui.
     *
     * @param fxUI the fx ui
     * @return the fx ui
     */
    //Method to get the fxUI
    public FxUI setfxUI(FxUI fxUI){
    	return this.fxUI = fxUI;
    	
    }
    
    /**
     * Invoke command.
     */
    private void invokeCommand(){
        if(command!=null){
            GameCommandType commandType = (GameCommandType) command.getCommandType();

            try{
                Method m0 = this.getClass().getDeclaredMethod(command.getCommandType().getName(), command.getCommandType().getParamTypes());
                m0.invoke(this, command.getParams());
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            	ex.getCause();
                logger.log(Level.SEVERE, "Fehler: GameImpl.invokeCommand(); Fehler bei Invoke");
            }
        }
    }

}
