package prog2_a3.fatsquirrel.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class State {
    HashMap<String,LinkedList<Integer>> highscore;
    private Board board;
    private FlattenedBoard flattenedBoard;
    private BoardConfig config;
    private XY input;
    private List<Entity> entArr;
    private static final GameLogger logger = new GameLogger();
    
    public State(){
        this.config = new BoardConfig();
        this.board =new Board(this.config);
        this.entArr = this.board.getEntitySet().getEntityVector();
        logger.log(Level.FINEST, "Objekt der Klasse State wurde erstellt");
        this.highscore = new HashMap();
        //für jeden MasterSquirrel eine LinkedList in der HashMap initialisieren
        for(int i = 0; entArr.size()>i;i++){
            if(GuidedMasterSquirrel.class.isInstance(entArr.get(i)))
                highscore.put(entArr.get(i).getName(), new LinkedList<Integer>());
            if(MasterSquirrelBot.class.isInstance(entArr.get(i)))
                highscore.put(((MasterSquirrelBot)entArr.get(i)).getImplName(), new LinkedList<Integer>());
        }
    }

    public HashMap getHighscore(){
        return highscore;
        
    }
    
    public void update(){
        this.entArr = this.board.getEntitySet().getEntityVector();
        
        LinkedList<Integer> ls = new LinkedList();
            LinkedList<Integer> lsBot = new LinkedList();
            for(int i = 0; i<entArr.size();i++){
                if(GuidedMasterSquirrel.class.isInstance(entArr.get(i))){
                    if(highscore.containsKey(entArr.get(i).getName()))
                        ls = highscore.get(((MasterSquirrel)entArr.get(i)).getName());
                    else
                        ls = new LinkedList<Integer>();
                    
                    ls.add(entArr.get(i).getEnergy());
                    highscore.put(((MasterSquirrel)entArr.get(i)).getName(), ls);
                    ((MasterSquirrel)entArr.get(i)).updateEnergy(1000-(entArr.get(i)).getEnergy());
                }
                if(MasterSquirrelBot.class.isInstance(entArr.get(i))){
                    if(highscore.containsKey(((MasterSquirrelBot)entArr.get(i)).getImplName()))
                        lsBot = highscore.get(((MasterSquirrelBot)entArr.get(i)).getImplName());
                    else
                        lsBot = new LinkedList<Integer>();
                    lsBot.add(entArr.get(i).getEnergy());
                    highscore.put(((MasterSquirrelBot)entArr.get(i)).getImplName(),lsBot);
                    ((MasterSquirrel)entArr.get(i)).updateEnergy(1000-(entArr.get(i)).getEnergy());
                }
                else if(MiniSquirrel.class.isInstance(entArr.get(i))){
                    entArr.remove((entArr.get(i)));
                }
            }
            Object[] names = highscore.keySet().toArray();
            for(int i = 0; i<names.length;i++){
                    ls = highscore.get(names[i]);
                Object[] lsArr = ls.toArray();
                
                System.out.print(names[i]+": ");
                Arrays.sort(lsArr);
                System.out.println(Arrays.toString(lsArr));
                logger.log(Level.INFO, names[i].toString()+": "+ Arrays.toString(lsArr));
            }
    }
    
    public FlattenedBoard flattenedBoard(){
        return this.flattenedBoard = board.flatten();
    }
    
    public Board getBoard(){
        return this.board;
    }
}
