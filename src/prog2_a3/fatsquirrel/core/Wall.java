package prog2_a3.fatsquirrel.core;

import prog2_a3.interfaces.EntityContext;


public class Wall extends Entity{

    public final static int energy = -10; 	
	
	public Wall(int id, int x, int y) {
		super(id, energy, x, y);
	}

    @Override
    public void nextStep(EntityContext entCon) {
    } 

}
