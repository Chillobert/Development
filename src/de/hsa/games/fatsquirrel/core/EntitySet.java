package de.hsa.games.fatsquirrel.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.BadPlant;
import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.GoodPlant;
import de.hsa.games.fatsquirrel.GuidedMasterSquirrel;
import de.hsa.games.fatsquirrel.MasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.Wall;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.logger.GameLogger;

// TODO: Auto-generated Javadoc
/**
 * The Class EntitySet.
 */
public class EntitySet {

    /** The numb of. */
    private int numbOf=0;
    
    /** The ent array. */
    private LinkedList<Entity> entArray;
    
    /** The id coll. */
    private int idColl=0;
    
    /** The Constant logger. */
    private static final GameLogger logger = new GameLogger();
    
    /** The current steps. */
    private int currentSteps;
    
    /** The highscore. */
    Map<String,LinkedList<Integer>> highscore;
    
    /**
     * Instantiates a new entity set.
     */
    public EntitySet(){  
        this.entArray = new LinkedList();
        this.highscore = new HashMap();
        //für jeden MasterSquirrel eine LinkedList in der HashMap initialisieren
        for(int i = 0; entArray.size()>i;i++){
            if(GuidedMasterSquirrel.class.isInstance(entArray.get(i)))
                highscore.put(entArray.get(i).getName(), new LinkedList<Integer>());
            if(MasterSquirrelBot.class.isInstance(entArray.get(i)))
                highscore.put(((MasterSquirrelBot)entArray.get(i)).getImplName(), new LinkedList<Integer>());
        }
        logger.log(Level.FINEST, "Object der Klasse EntitySet erstellt");
    }
    
    /**
     * Adds the.
     *
     * @param entity the entity
     */
    public void add(Entity entity){
        entArray.add(entity);
//        switch(entityTyp){
//            case"BadPlant": entArray.add(new BadPlant(numbOf++,x,y));break;
//            case"GoodBeast":entArray.add(new GoodBeast(numbOf++,x,y));break;
//            case"GoodPlant":entArray.add(new GoodPlant(numbOf++,x,y));break;
//            case"BadBeast":entArray.add(new BadBeast(numbOf++,x,y)); break;
//            case"GuidedMasterSquirrel":entArray.add(new GuidedMasterSquirrel(numbOf++,x,y));break;
//            case"Wall":entArray.add(new Wall(numbOf++,x,y));break;
//            case"MasterSquirrelBot":entArray.add(new MasterSquirrelBot(numbOf++,x,y));break;
//            case"MiniSquirrel":entArray.add(new MiniSquirrel(numbOf++, 200, x, y, 1)); //Zum testen
//            default:break;
//            
//        }
        numbOf++;
    }
    
    /**
     * Adds the.
     *
     * @param entityTyp the entity typ
     * @param x the x
     * @param y the y
     */
    public void add(String entityTyp, int x, int y){
                switch(entityTyp){
            case"BadPlant": entArray.add(new BadPlant(numbOf++,x,y));break;
            case"GoodBeast":entArray.add(new GoodBeast(numbOf++,x,y));break;
            case"GoodPlant":entArray.add(new GoodPlant(numbOf++,x,y));break;
            case"BadBeast":entArray.add(new BadBeast(numbOf++,x,y)); break;
            case"GuidedMasterSquirrel":entArray.add(new GuidedMasterSquirrel(numbOf++,x,y));break;
            case"Wall":entArray.add(new Wall(numbOf++,x,y));break;
            case"MiniSquirrel":entArray.add(new MiniSquirrel(numbOf++, 200, x, y, 0)); //Zum testen
            default:break;
            
        }
    }
    
    /**
     * Adds the mini.
     *
     * @param master the master
     * @param energy the energy
     * @param direction the direction
     */
    public void addMini(MasterSquirrel master,int energy, XY direction){
         entArray.add(master.createDescendant(numbOf++,energy , direction.getX(), direction.getY()));
    }
    
    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(int id){
        
        for(int i = 0; entArray.size()>i;i++){
            if (entArray.get(i).getId() == id)
                entArray.remove(i);
        }
    }
    
    /**
     * Gets the latest id.
     *
     * @return the latest id
     */
    public int getLatestId(){
        return numbOf;
    }
    
    /**
     * Gets the entity vector.
     *
     * @return the entity vector
     */
    public LinkedList getEntityVector(){
        return entArray;
    }
    
    /**
     * Gets the entity array.
     *
     * @return the entity array
     */
    public Entity[] getEntityArray(){
        Entity[] entArr = new Entity[entArray.size()];
        for(int i =0; i< entArray.size();i++){
            entArr[i] = entArray.get(i);
        }
        return entArr;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        String output="";
        for(int i=0;entArray.size()<i;i++){
            output += entArray.get(i).getName() + "," + entArray.get(i).toString() + '\n';
        }
        return(output);
    }

    /**
     * Next step all.
     *
     * @param entContext the ent context
     * @param input the input
     * @throws InterruptedException the interrupted exception
     */
    public void nextStepAll(EntityContext entContext, XY input) throws InterruptedException{
        for(int i=0;entArray.size()>i;i++){
            if(entArray.get(i).getTimeout()<=0){
                entArray.get(i).nextStep(entContext, input);
            }
            else if(entArray.get(i).getTimeout()>0)
                entArray.get(i).setTimeout(entArray.get(i).getTimeout()-1);
        }
    }
}
