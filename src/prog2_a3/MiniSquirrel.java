package prog2_a3;

public class MiniSquirrel extends Entity{
	private int patronId;

	public MiniSquirrel(int id, int energy, int x, int y, int patronId) {
            super(id, energy, x, y);
            this.patronId = patronId;
	}
        
        public int getPatronId(){
            return patronId;
        }

        @Override
        public void nextStep(){
            this.randMove();
        }
        
}
