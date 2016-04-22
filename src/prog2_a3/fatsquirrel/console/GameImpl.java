package prog2_a3.fatsquirrel.console;

import java.util.Random;
import prog2_a3.fatsquirrel.core.*;

public class GameImpl extends Game {
    ConsoleUI ui = new ConsoleUI();
    XY vector;
    public GameImpl(){
        super();
    }
    
    @Override
    protected void processInput(){
        super.input = ui.getCommand();
    }
    
    @Override
    protected void update(){
        Random r = new Random();
        Entity[] entArray = state.board.entSet.getEntityArray();
        for(int i=0;entArray[i]!=null;i++){
            if("BadBeast".equals(entArray[i].getName()))
                super.flattenedBoard.tryMove((BadBeast)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
            if("GoodBeast".equals(entArray[i].getName()))
                flattenedBoard.tryMove((GoodBeast)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
            if("MiniSquirrel".equals(entArray[i].getName()))
                flattenedBoard.tryMove((MiniSquirrel)entArray[i],new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1}));
            if(state.board.entSet.isInstance(entArray[i], MasterSquirrel.class))
                flattenedBoard.tryMove((MasterSquirrel)entArray[i],this.input);
        }
        super.flattenedBoard.entSet.nextStepAll(flattenedBoard.getVectors());
    }
}
