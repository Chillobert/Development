package prog2_a1;


public class Board {
    private int columnInput;
    private int rowInput;
    private boolean singlePlayer=false;
    private final char[][] field=new char[8][8];
    private boolean winFlag=false;
    Interaction inter;
    
    public Board(boolean singlePlayer){
        inter = new Interaction();
        this.singlePlayer = singlePlayer;
            // Initialize with spaces
        for (int i = 0; i < 7; ++i)
            for (int j = 0; j < 7; ++j)
                field[i][j] = ' ';
        };
    	
    public void print(){
        for (int row = 0; row < 7; ++row) {
        System.out.print("| ");
        for (int col = 0; col < 7; ++col)
            System.out.print(field[row][col] + "| ");
        System.out.println();
        }
 
        // Print bottom line
        for (int col = 0; col < 6; ++col)
            System.out.print("====");
        System.out.println();
    }
                
    private int getWinnerInRowsLeft() {
        	int Counter = 1;
            for (int i = 1; i <=3 ; i++){
                if(columnInput-i >= 0){    		
        		if(field[rowInput][columnInput] == field[rowInput][columnInput-i])
                        Counter++;
        		else{
                        return Counter;
        		}
                }
        	}
            return Counter;
        }
        
    private int getWinnerInRowsRight() {
            int Counter = 1;
            for (int i = 1; i <=3 ; i++){
                if(columnInput+i < 7){    		
        		if(field[rowInput][columnInput] == field[rowInput][columnInput+i])
                        Counter++;
        		else{
                        return Counter;
        		}
                }
        	}
            return Counter;
        }
        
    private int getWinnerinDiagonalsTopLeft(){
        	int Counter = 1;
            for (int i = 1; i <=3 ; i++){
                if(rowInput-i >= 0 && columnInput-i >= 0){    		
        		if(field[rowInput][columnInput] == field[rowInput-i][columnInput-i])
                        Counter++;
        		else{
                        return Counter;
        		}
                }
        	}
            return Counter;
        }
        
    private int getWinnerinDiagonalsBotRight(){
        	int Counter = 1;
        	for (int i = 1; i <=3 ; i++){
                if(rowInput+i < 7 && columnInput+i < 7){
        		if(field[rowInput][columnInput] == field[rowInput+i][columnInput+i])
                        Counter++;
        		else{
                        return Counter;
        		}
                }
        	} 
        	return Counter;
        }
        
    private int getWinnerinDiagonalsBotLeft(){
        	int Counter = 1;
            for (int i = 1; i <=3 ; i++){
                if(rowInput-i >= 0 && columnInput+i < 7){
        		if(field[rowInput][columnInput] == field[rowInput-i][columnInput+i])
                        Counter++;
                        else{
        			return Counter;
                        }
                }
        	}
            return Counter;
        }
        
    private int getWinnerinDiagonalsTopRight(){
        	int Counter = 1;
            for (int i = 1; i <=3 ; i++){
                if(rowInput+i < 7 && columnInput-i >= 0){
                    if(field[rowInput][columnInput] == field[rowInput+i][columnInput-i])
                        Counter++;
                        else{
                            return Counter;
                        }
                }
            }
            return Counter;
        }
        
    private char getWinnerInRows(){
            if (getWinnerInRowsRight() + getWinnerInRowsLeft() >=5){
            	return field[rowInput][columnInput];
            }
            return' ';
        }
           
    private char getWinnerInColumns() {
        	int Counter = 1;
            for (int i = 1; i <=3 ; i++){
                if(rowInput+i < 7){    		
        		if(field[rowInput][columnInput] == field[rowInput+i][columnInput])
                        Counter++;
                }
        	}
            if(Counter>=4)
                return field[rowInput][columnInput];
            return ' ';
        }
            
    private char getWinnerInDiagonals() {
        	
        	if (getWinnerinDiagonalsTopLeft() + getWinnerinDiagonalsBotRight() >=5
                    || getWinnerinDiagonalsTopRight() + getWinnerinDiagonalsBotLeft()>=5)
                    return field[rowInput][columnInput];
        	return ' ';
        }
     
    public char getWinner(int currentRow,int currentColumn) {
            rowInput = currentRow;
            columnInput = currentColumn;
            char playerWin = getWinnerInRows();
            if (playerWin != ' ') 
                return playerWin;
            playerWin = getWinnerInColumns();
            if (playerWin != ' ') 
                return playerWin;
            playerWin = getWinnerInDiagonals();
            if (playerWin != ' ') 
                return playerWin;
     
            //Überprüfe ob leeres Feld vorhanden ist
            for (int i = 0; i < field.length; ++i)
                for (int j = 0; j < field[i].length; ++j)
                    if (field[i][j] == ' ') 
                        return ' ';
     
            return 'D';
        }
    
    public boolean placeToken(int column, char currentToken) {

        if (field[0][column] != ' '){
            inter.output('F');
            return false;
        }
 
        for (int row = 0; row < 7; ++row) {
            if (field[row][column] != ' ') {
                field[row-1][column] = currentToken;      
                rowInput = row-1;
                columnInput =column;
                return true;
            }
        }
        field[6][column] = currentToken;
        rowInput = 6;
        columnInput = column;
        return true;
    }
 
    public int bot(){
        int column = (int)(Math.random()*6);
        placeToken(column,'O');
        return column;
    }
    
    public void play(){
        char result=' ';
        boolean playerTurn = true; //True, Player1

        print();
        int columnIn;
        char a = '1';
        while (!winFlag) {
            if (playerTurn)
                inter.output('1');
            else
                inter.output('2');
            if(!playerTurn&&singlePlayer)
                bot();                
            else{
                for(boolean valid = false; !valid;){
                    inter.output('I');
                    columnIn = inter.readInput(playerTurn);
                    valid = placeToken(columnIn,playerTurn?'X':'O');
                    if(!valid)
                    inter.output('F');
                }
            }
            print();
            result = getWinner(rowInput,columnInput);//Gewinner Abfrage jeden Zug
            if(result!=' ')
                winFlag=true;
            playerTurn = !playerTurn;
        }
            inter.output(result);
    }
}