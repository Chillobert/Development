package prog2_a3.fatsquirrel.console;

import prog2_a3.fatsquirrel.core.*;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.fatsquirrel.util.ui.console.MyFavoriteCommandType;
import prog2_a3.fatsquirrel.util.ui.console.ScanException;

public class GameImpl extends Game {
    private ConsoleUI ui;
    
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
            Command command = ui.getCommand();
            try{
                if(command==null)
                    throw new ScanException();
            }
            catch(ScanException ScEx){
                System.out.println("wrong input. Please use 'help' to show commands");
            }
            if(command!=null){
                GameCommandType commandType = (GameCommandType) command.getCommandType();
           
                if(commandType != GameCommandType.EXIT && commandType != GameCommandType.HELP)
                    params = command.getParams();
    
                switch (commandType) {
                case EXIT:
                    System.exit(0);
                case HELP: 
                    for(int i=0;commandTypes.length>i;i++){
                    System.out.println(commandTypes[i].getName()+commandTypes[i].getHelpText());
                    }
                    break;
                case ALL:
                    System.out.println(this.flattenedBoard.getEntitySet().toString());
                    break;
                case LEFT:
                    this.input = flattenedBoard.getMasterSquirrel().getLocation().moveLeft();
                    break;
                case RIGHT:
                    this.input = flattenedBoard.getMasterSquirrel().getLocation().moveRight();
                    break;
                case UP:
                    this.input = flattenedBoard.getMasterSquirrel().getLocation().moveUp();
                    break;
                case DOWN:
                    this.input = flattenedBoard.getMasterSquirrel().getLocation().moveDown();
                    break;
                case MASTER_ENERGY:
                    System.out.println("Die aktuelle Energie unseres Squirrel ist: "+flattenedBoard.getSquirrelEnergy());
                    break;
                case SPAWN_MINI:
                    try{
                    flattenedBoard.spawnChild(flattenedBoard.getMasterSquirrel(), new XY(new int[]{(int)(Math.round((Math.random()*2)-1)),(int)(Math.round((Math.random()*2)-1))}), (int)command.getParams()[0]);
                    }
                    catch(NullPointerException | NotEnoughEnergyException NuEx){
                        System.out.println("wrong input. Please refere to \"help\"");
                    }
                    break;
                default:break;
                }
            }
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

}
