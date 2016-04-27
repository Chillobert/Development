package prog2_a3.fatsquirrel.core;

import prog2_a3.interfaces.EntityContext;


public class GoodBeast extends Beast{

    public final static int energy = 200; 	
    public GoodBeast(int id, int x, int y) {
		super(id, energy, x, y);
	}

    @Override
    public void nextStep(EntityContext entCon) {
        entCon.tryMove(this, this.getLocation().getRandomVector());
    } 
}
