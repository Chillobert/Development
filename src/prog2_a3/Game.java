package prog2_a3;

public abstract class Game {

    public static void main(String []args){
		
    };

    public void run(){
	while(true){
            render();
            processInput();
            update();
			}
	}
		//test test test
		
//Darstellung des Spielzustands auf dem Ausgabemedium
    abstract void render();
	
//Verarbeitung von Benutzereingaben
    void processInput(){
        
    };
	
//Ver�nderung des Spielzustandes -> Vorbereitung n�chster Render Vorgang	
    private void update() {
    EntitySet entSet = new EntitySet();
    entSet.nextStepAll();
    }	
	
}

/*
	public static void main(String[] args){
		
			/*
            Boolean play=true;
            EntitySet ent = new EntitySet();

            while(play){
                ent.add("BadBeast", 0, 1, 2);
                ent.add("GoodBeast" ,1,2,3);
                ent.add("GoodPlant",2,3,4);
                ent.add("BadPlant",3,4,5);
                ent.add("GuidedMasterSquirrel",4,5,6);
                ent.add("Wall", 5, 6, 7);
                System.out.println(ent.toString());
                ent.nextStepAll();
                System.out.println(ent.toString());
                ent.delete(3);
                System.out.println(ent.toString());
                play=false;
            }
            */		