package prog2_a3;


public class Wall extends Entity{

	public final static int energy = -10; 	
	
	public Wall(int id, int x, int y) {
		super(id, energy, x, y);
		// TODO Auto-generated constructor stub
	}

    @Override
    public void nextStep() {
    }

}
