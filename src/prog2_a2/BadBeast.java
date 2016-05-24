package prog2_a2;
public class BadBeast extends Entity{
	
public final static int energy = -150;

	public BadBeast(int id, int x, int y) {
		super(id, energy, x, y);
                
	}
        
@Override
        public void nextStep(){
            this.randMove();
        }


        
}
