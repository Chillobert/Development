package prog2_a3.fatsquirrel.core;


public abstract class Game {
    public State state;
    public FlattenedBoard flattenedBoard;
    public XY input;
    public final int FPS = 1;
    public Game (){
        state = new State();
        input = new XY(new int[]{0,0});
    };

    public void run(){
	//while(true){
            render();
           // processInput();
            update();
	//}
    }
		
//Darstellung des Spielzustands auf dem Ausgabemedium

    protected abstract void render();
	
//Verarbeitung von Benutzereingaben

    protected abstract void processInput();
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang

    protected abstract void update();
}
