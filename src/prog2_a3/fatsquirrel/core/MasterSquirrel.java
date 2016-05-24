package prog2_a3.fatsquirrel.core;

import prog2_a3.interfaces.EntityContext;

public abstract class MasterSquirrel extends PlayerEntity{

	public static final int energy = 1000; 
	public MasterSquirrel(int id, int x, int y) {
		super(id, energy, x, y);             
	}
	
	// Erstellt ein Minisquirrel
        public MiniSquirrel createDescendant(int latestId, int energy, int x, int y){
            MiniSquirrel Norbert = new MiniSquirrel(latestId+1, energy, getLocation().getX()+x, getLocation().getY()+y, super.getId());
            this.updateEnergy(-energy);
            //entityList.updateEnergy(energy) mit Hashtable. id als key
            return Norbert;
	}
	
	//Pr�fe ob �bergebenes Entity Object ein Nachfahre ist
	public boolean checkDescendant(MiniSquirrel mini){
            return mini.getPatronId()==this.getId();
                    }
 
    public void nextStep(EntityContext entCon,XY direction){
        entCon.tryMove(this, direction);
        
    }
}

