package de.hsa.games.fatsquirrel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.core.Board;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.FlattenedBoard;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class State.
 */
public class State {
    
    /** The highscore. */
    Map<String,LinkedList<Integer>> highscore;
    
    /** The board. */
    private Board board;
    
    /** The flattened board. */
    private FlattenedBoard flattenedBoard;
    
    /** The config. */
    private BoardConfig config;
    
    /** The input. */
    private XY input;
    
    /** The ent arr. */
    private List<Entity> entArr;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /**
     * Instantiates a new state.
     */
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

    /**
     * Gets the highscore.
     *
     * @return the highscore
     */
    public Map getHighscore(){
        return highscore;
        
    }
    
    /**
     * Update.
     */
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

    
    /**
     * Save.
     */
    //Diese Methode speichert die Highscore der aktuellen Spielrunden in die Highscore.txt (Pfad: RootDirectory)
    public void save(){
    	
        File score = new File("Highscore.txt"); 

        if(!score.exists())
			try {
				score.createNewFile();
				logger.log(Level.INFO, "Created Highscore.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.log(Level.SEVERE, "Error at creating File Highscore.txt");
			}
        
        	try {
				FileWriter fstream = new FileWriter("Highscore.txt",true);
				BufferedWriter out = new BufferedWriter(fstream);
				
				String timeStamp = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
				String helper = this.getHighscore().toString();	
				
				out.newLine();
				out.write("Date: " + timeStamp );
				out.newLine();
				out.write("Result:" + helper );
				out.newLine();
				out.close();
				logger.log(Level.INFO, "Added new Highscore to File Highscore.txt");
				
			} catch (IOException e) {
				e.printStackTrace();
				logger.log(Level.SEVERE, "Error at writing File Highscore.txt");
			}
	
    	
    }
    
    /**
     * Flattened board.
     *
     * @return the flattened board
     */
    public FlattenedBoard flattenedBoard(){
        return this.flattenedBoard = board.flatten();
    }
    
    /**
     * Gets the board.
     *
     * @return the board
     */
    public Board getBoard(){
        return this.board;
    }
}
