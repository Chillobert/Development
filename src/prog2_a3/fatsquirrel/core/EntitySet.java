package prog2_a3.fatsquirrel.core;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import prog2_a3.interfaces.*;

public class EntitySet {

    private int numbOf=0;
    private int latestId=1;
    private Entity[] entArray = new Entity[1000];
    private int idColl=0; 
    private static final GameLogger logger = new GameLogger();
    public EntitySet(XY size){   
    logger.log(Level.FINEST, "Object der Klasse EntitySet erstellt");
    }
    
    public void add(String entityTyp, int x, int y){
        switch(entityTyp){
            case"BadPlant": entArray[numbOf++] = new BadPlant(numbOf,x,y);break;
            case"GoodBeast":entArray[numbOf++] = new GoodBeast(numbOf,x,y);break;
            case"GoodPlant":entArray[numbOf++] = new GoodPlant(numbOf,x,y);break;
            case"BadBeast":entArray[numbOf++] = new BadBeast(numbOf,x,y); break;
            case"GuidedMasterSquirrel":entArray[numbOf++] = new GuidedMasterSquirrel(numbOf,x,y);break;
            case"Wall":entArray[numbOf++] = new Wall(numbOf,x,y);break;
            case"MasterSquirrelBot":entArray[numbOf++] = new MasterSquirrelBot(numbOf,x,y);break;
            default:break;
            
        }
        latestId++;
    }
    
    public void addMini(MasterSquirrel master,int energy, XY direction){
        entArray[numbOf++] = master.createDescendant(numbOf++,energy , direction.getX(), direction.getY());
    }
    
    public void delete(int id){
        Entity[] copyArray = new Entity[1000];
        int j=0;
        for(int i=0;entArray.length>i;i++){
            if(entArray[i]!=null)
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
            output += entArray[i].getName() + "," + entArray[i].toString() + '\n';
        }
        return(output);
    }

    public void nextStepAll(EntityContext entContext, XY input) throws InterruptedException{
        boolean masterExists = false;
        for(int i=0;entArray.length>i;i++){
            if(entArray[i]!=null){
                if(isInstance(entArray[i],MasterSquirrel.class))
                    masterExists = true;
                if(entArray[i].getTimeout()<=0){
                    if(isInstance(entArray[i],GuidedMasterSquirrel.class)){
                    	if( input != null) ///////////////////////
                            ((GuidedMasterSquirrel)entArray[i]).nextStep(entContext,input);
                    }
                    else
                        entArray[i].nextStep(entContext);
                }
                else if(entArray[i].getTimeout()>0)
                    entArray[i].setTimeout(entArray[i].getTimeout()-1);
            }
        }
        if(!masterExists){
        	logger.log(Level.INFO, "Spiel beendet");
            JOptionPane.showMessageDialog(null,"you lost! Press OK to exit");
            System.exit(0);
        }
    }
    
    public boolean isInstance(Object o, Class c){
    return c.isInstance(o);
    }
    
}
