package prog2_a3.fatsquirrel.console;

import java.util.Random;
import prog2_a3.fatsquirrel.core.*;

public class GameImpl extends Game {
    ConsoleUI ui = new ConsoleUI();
    int[] vector;
    public GameImpl(){
        super();
        this.vector = new int[]{0,0};
    }
    
    @Override
    protected void processInput(){
        switch(ui.getCommand()){
            case "a":vector[0]=-1;vector[1]=0;break;
            case "s":vector[0]=0; vector[1]=1;break;
            case "d":vector[0]=1; vector[1]=0;break;
            case "w":vector[0]=0; vector[1]=-1;break;
            case "q":vector[0]=-1;vector[1]=-1;break;
            case "e":vector[0]=1;vector[1]=-1;break;
            case "c":vector[0]=1;vector[1]=1;break;
            case "y":vector[0]=-1;vector[1]=1;break;
            default: System.out.println("Keine gültige Richtung");break;        
        }
        super.input = new XY(vector);
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

    @Override
    protected void render() {
        ui.render(flattenedBoard = state.board.flatten());
    }
}
