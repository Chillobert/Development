package prog2_a3.fatsquirrel.core;


public class GoodBeast extends Entity{

    public final static int energy = 200; 	
    public GoodBeast(int id, int x, int y) {
		super(id, energy, x, y);
	}

    @Override
    public void nextStep(){

    }
        
    @Override
    public void nextStep(XY vector){
        move(vector);
    }
}
