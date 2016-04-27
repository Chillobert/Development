package prog2_a3.fatsquirrel.core;

import prog2_a3.interfaces.EntityContext;


public class BadPlant extends Plant{
	public final static int energy = -100; 

	public BadPlant(int id, int x, int y) {
		super(id, energy, x, y);
		// TODO Auto-generated constructor stub
	}

@Override
    public void nextStep(EntityContext entCon) {
    } 

}
