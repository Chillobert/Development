package prog2_a3.fatsquirrel.core;

import java.util.logging.Level;

public class State {
    private int highscore;
    private Board board;
    private FlattenedBoard flattenedBoard;
    private BoardConfig config;
    private XY input;
    private static final GameLogger logger = new GameLogger();
    public State(){
        this.config = new BoardConfig();
        this.board =new Board(this.config);
        logger.log(Level.FINEST, "Objekt der Klasse State wurde erstellt");
    }

    public int getHighscore(){
        return highscore;
    }
    
    public void update(){
        
    }
    
    public FlattenedBoard flattenedBoard(){
        return this.flattenedBoard = board.flatten();
    }
    
    public Board getBoard(){
        return this.board;
    }
}
