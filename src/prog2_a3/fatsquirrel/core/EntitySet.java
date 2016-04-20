package prog2_a3.fatsquirrel.core;

import java.util.Arrays;
import java.util.Hashtable;

public class EntitySet {

    private int numbOf=0;
    private Entity[] entArray = new Entity[50];
    private int[] delArray = new int[50];
    private int idColl=0;
    
    public EntitySet(){   
        Arrays.fill(delArray, ' ');
    }
    
    public void add(String entityTyp, int x, int y){
        switch(entityTyp){
            case"BadPlant": entArray[numbOf++] = new BadPlant(numbOf,x,y);break;
            case"GoodBeast":entArray[numbOf++] = new GoodBeast(numbOf,x,y);break;
            case"GoodPlant":entArray[numbOf++] = new GoodPlant(numbOf,x,y);break;
            case"BadBeast":entArray[numbOf++] = new BadBeast(numbOf,x,y);break;
            case"GuidedMasterSquirrel":entArray[numbOf++] = new GuidedMasterSquirrel(numbOf,x,y);break;
            case"Wall":entArray[numbOf++] = new Wall(numbOf,x,y);break;
        }
    }
    
    public void delete(int id){
        Entity[] copyArray = new Entity[50];
        int j=0;
        for(int i=0;entArray[i] != null;i++){
            if(!(entArray[i].getId() == id)){
                copyArray[j] = entArray[i];
                j++;
                //Da immer nur eine ID �bergeben, wird es nie mehr als einen Eintrag weniger geben
            }
            /* for(int k=0;k<copyArray.length;k++){
                if(entArray[k]!=null)
                entArray[k]=copyArray[k];} */
        }
        
        Arrays.fill(entArray, null); //Array leeren !
        for (int k = 0; copyArray[k]!=null; k++){ //Array mit neuen Werten f�llen
        	entArray[k] = copyArray[k];
        }
        
        
        numbOf--;
    }
    
    public int getLatestId(){
        return numbOf;
    }
    
    public Entity[] getEntityArray(){
        return entArray;
    }
    
    @Override
    public String toString(){
        String output="";
        for(int i=0;entArray[i]!=null;i++){
            output += entArray[i].toString() + '\n';
        }
        return(output);
    }

    public void nextStepAll(Hashtable<Integer, XY> vectorList){
        for(int i=0;entArray[i]!=null;i++){
            //wenn Pflanze oder Wall nextStep() aufrufen
            if(("GoodPlant").equals(entArray[i].getName())|"BadPlant".equals(entArray[i].getName())|"Wall".equals(entArray[i].getName()))
                entArray[i].nextStep();
            //falls in der Vectorliste die Id des aktuellen Objekts vorliegt, move(XY vector) aufrufen
            if(vectorList.containsKey(entArray[i].getId()))
                entArray[i].nextStep(vectorList.get(entArray[i].getId()));

             checkCollision(i);
        }
        
        //Nach Rundendurchlauf wird mit jeder ID(siehe mortalCombat) die in delArray gespeichert wurde die Delete Methode aufgerufen
        for(int j = 0;delArray[j] != ' '; j++){
        	delete(delArray[j]);
        	
        }
        
        //Nach delete wird delArray geleert
        Arrays.fill(delArray, ' ');
    }

    private void checkCollision(int arrayPos){
        for(int i=0;entArray[i]!=null;i++){
            if((entArray[arrayPos].getLocation().getX()==entArray[i].getLocation().getX()&(entArray[arrayPos].getLocation().getY()==entArray[i].getLocation().getY()))){
                mortalCombat(arrayPos,i);
                return;
            }
        }
    }
    
    public boolean isInstance(Object o, Class c){
    	
    return c.isInstance(o);

    }

    //equals methode(mit instanceof)?
    private void mortalCombat(int arrayPos,int collPos){
    	if(isInstance(entArray[arrayPos],PlayerEntity.class)){
            if(isInstance(entArray[collPos], GoodPlant.class)){
                entArray[arrayPos].updateEnergy(entArray[collPos].getEnergy());
                entArray[collPos].updateEnergy(entArray[collPos].getEnergy()); //Energie auf 0 setzen
            }

            for(int i = 0; i < delArray.length; i++){ //- Id an CollPos wird in delArray gespeichert
                if(delArray[i] == ' '){
                    delArray[i] = entArray[collPos].getId(); //-
                return;
                }
            }
    	}	
    }
}