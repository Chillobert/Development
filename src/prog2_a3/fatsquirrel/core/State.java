package prog2_a3.fatsquirrel.core;


public class State {
    private int highscore;
    private Board board;
    private FlattenedBoard flattenedBoard;
    private BoardConfig config;
    private XY input;
        
    public State(){
        this.config = new BoardConfig();
        this.board =new Board(this.config);
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
