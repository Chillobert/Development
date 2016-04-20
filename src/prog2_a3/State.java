package prog2_a3;

import prog2_a3.fatsquirrel.core.*;

public class State {
    private int highscore;
    public Board board;
        
    public State(){
        this.board =new Board();
    }

    public int getHighscore(){
        return highscore;
    }
    
    public void update(){
        
    }
    
    public FlattenedBoard FlattenedBoard(){
        return board.flatten();
    }
}
