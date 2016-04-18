package prog2_a3;
import java.util.Random;

public final class XY {
	private final int[] loc;
        
        public XY(int[]loc){
            this.loc = loc;
        }
        
	public XY move(int[] Vector){
            return new XY(new int[]{loc[0] + Vector[0],loc[1] + Vector[1]});
	}

        public XY moveRandom(){
            Random r = new Random();
            return move(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
        }
        //test test test
        public int getX(){
            return loc[0];
        }
        
        public int getY(){
            return loc[1];
        }
        
        public int[] getPos(){
            return loc;
        }
        
        // public XY add(XY xy) {
        //    return new XY(x + xy.x, y + xy.y)
}
