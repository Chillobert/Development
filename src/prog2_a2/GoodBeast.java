package prog2_a2;

import java.util.Random;

public class GoodBeast extends Entity{

	public final static int energy = 200; 	
	
	public GoodBeast(int id, int x, int y) {
		super(id, energy, x, y);
	}

        @Override
        public void nextStep(){
            this.randMove();
        }
}
