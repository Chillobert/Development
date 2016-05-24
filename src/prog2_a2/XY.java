package prog2_a2;
import java.util.Random;

public final class XY {
	private final int[] loc;
        
        public XY(int[]loc){
            this.loc = loc;
        }
        
	public int[] move(int[] Vector){
            return new int[] {loc[0] + Vector[0],loc[1] + Vector[1]};
	}

        public int[] randVect(){
            Random r = new Random();
            return new int[]{r.nextInt(3)-1,r.nextInt(3)-1};
        }
        
        public int getX(){
            return loc[0];
        }
        
        public int getY(){
            return loc[1];
        }
        
        public int[] getPos(){
            return loc;
        }
}
