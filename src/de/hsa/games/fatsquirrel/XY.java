package de.hsa.games.fatsquirrel;
import java.util.Random;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class XY.
 */
public final class XY {
	
	/** The loc. */
	private final int[] loc;
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
        
        /** The r. */
        Random r;
        
        /**
         * Instantiates a new xy.
         *
         * @param loc the loc
         */
        public XY(int[]loc){
            this.loc = loc;
            r = new Random();
            logger.log(Level.FINEST, "Objekt der XY Game wurde erstellt");
        }
        
	/**
	 * Move.
	 *
	 * @param Vector the vector
	 * @return the xy
	 */
	public XY move(int[] Vector){
            return new XY(new int[]{loc[0] + Vector[0],loc[1] + Vector[1]});
	}

        /**
         * Move random.
         *
         * @return the xy
         */
        public XY moveRandom(){
            return move(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
        }
        
        /**
         * Gets the random vector.
         *
         * @return the random vector
         */
        public XY getRandomVector(){
            return new XY(new int[]{r.nextInt(3)-1,r.nextInt(3)-1});
        }
        
        /**
         * Gets the x.
         *
         * @return the x
         */
        public int getX(){
            return loc[0];
        }
        
        /**
         * Gets the y.
         *
         * @return the y
         */
        public int getY(){
            return loc[1];
        }
        
        /**
         * Gets the pos.
         *
         * @return the pos
         */
        public int[] getPos(){
            return loc;
        }
        
        /**
         * Move up.
         *
         * @return the xy
         */
        public XY moveUp(){
            return new XY(new int[]{loc[0],loc[1]-1});
        }
        
        /**
         * Move down.
         *
         * @return the xy
         */
        public XY moveDown(){
            return new XY(new int[]{loc[0],loc[1]+1});
        }
        
        /**
         * Move left.
         *
         * @return the xy
         */
        public XY moveLeft(){
            return new XY(new int[]{loc[0]-1,loc[1]});
        }
        
        /**
         * Move right.
         *
         * @return the xy
         */
        public XY moveRight(){
            return new XY(new int[]{loc[0]+1,loc[1]});
        }
        
        /**
         * Reverse.
         *
         * @return the xy
         */
        public XY reverse(){
            return new XY(new int[]{-this.getX(),-this.getY()});
        }
        
        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString(){
            return ("X: "+this.getX()+" Y: "+this.getY());
        }
}
