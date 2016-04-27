package prog2_a3.fatsquirrel.console;

import prog2_a3.fatsquirrel.core.*;

public class GameImpl extends Game {
    private ConsoleUI ui;
    
    public GameImpl(){
        super();
        this.ui = new ConsoleUI();
    }
    
    @Override
    protected void processInput(){   
        super.input = ui.getCommand().convertCommand();
    }
    
    @Override
    protected void update(){
        super.flattenedBoard.getEntitySet().nextStepAll(flattenedBoard,this.input);
    }

    @Override
    protected void render() {
        ui.render(flattenedBoard = state.getBoard().flatten());
        System.out.println("Die Energie unseres Squirrels beträgt: "+flattenedBoard.getSquirrelEnergy());
    }
}