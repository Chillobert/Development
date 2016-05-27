package prog2_a3.fatsquirrel.core;
import java.util.Random;

public final class XY {
	private final int[] loc;
        Random r;
        
        public XY(int[]loc){
            this.loc = loc;
            r = new Random();
        }
        
	public XY move(int[] Vector){
            return new XY(new int[]{loc[0] + Vector[0],loc[1] + Vector[1]});
	}

        public XY moveRandom(){
            return move(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
        }
        
        public XY getRandomVector(){
            return new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
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
        
        public XY moveUp(){
            return new XY(new int[]{0,-1});
        }
        
        public XY moveDown(){
            return new XY(new int[]{0,1});
        }
        
        public XY moveLeft(){
            return new XY(new int[]{-1,0});
        }
        
        public XY moveRight(){
            return new XY(new int[]{1,0});
        }
        
        public XY reverse(){
            return new XY(new int[]{-this.getX(),-this.getY()});
        }
        
        @Override
        public String toString(){
            return ("X: "+this.getX()+" Y: "+this.getY());
        }
}
