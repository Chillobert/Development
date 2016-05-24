package prog2_a1;

import java.util.Scanner;

public class Interaction {
	
    public int readInput(boolean playerTurn){
        boolean validInput=false;
        Scanner input = new Scanner(System.in);
        int columnIn=0;
                    
        do{
            if(input.hasNextInt()){
                columnIn = input.nextInt();
                validInput=true;
            }
            else{
                System.out.println("please insert valid input.");
                output('I');
                input.nextLine();
                continue;
            }
            if (columnIn < 1 || columnIn > 7) {
                System.out.print("Column should be from 1 to 7\nChoose column (1-7) for a token:");
                validInput=false;
                continue;
            }

            }while(!validInput);
        return columnIn-1;
    }
    
    public void output(char index){
        switch(index){
            case '1': System.out.println("Player 1's turn");break;
            case '2': System.out.println("Player 2's turn");break;
            case 'D': System.out.println("It's a draw!");break;
            case 'X': System.out.println("Player 1 wins!");break;
            case 'O': System.out.println("Player 2 wins!");break;
            case 'F': System.out.println("This column is filled! Choose another one.");break;
            case 'I': System.out.print("Choose column (1-7) for a token:");break;
        }
    }
}