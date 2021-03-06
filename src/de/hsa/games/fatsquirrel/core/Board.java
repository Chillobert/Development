package de.hsa.games.fatsquirrel.core;

//import com.sun.org.apache.bcel.internal.util.ClassPath;
import java.util.LinkedList;
import java.util.logging.Level;

import de.hsa.games.fatsquirrel.BadBeast;
import de.hsa.games.fatsquirrel.BadPlant;
import de.hsa.games.fatsquirrel.Entity;
import de.hsa.games.fatsquirrel.GoodBeast;
import de.hsa.games.fatsquirrel.GoodPlant;
import de.hsa.games.fatsquirrel.GuidedMasterSquirrel;
import de.hsa.games.fatsquirrel.MiniSquirrel;
import de.hsa.games.fatsquirrel.Wall;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.botapi.*;
import de.hsa.games.fatsquirrel.logger.GameLogger;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 * The Class Board.
 * This Class creates a Gamefield Object depending on a Game.Properties File.
 * If there are Problems with the Properties.File this Class will use a Config Class (BoardConfig_Old) instead.
 * Every Task of the Gamelogic itself gets performed at the Board Object.
 * 
 */
public class Board {
	
/** The length of the Board. */
private final int length;

/** The width of the Board. */
private final int width;

/** The size of the Board (length,width). */
private final XY size;

/** The amount good beasts (preset by the GameConfig). */
private final int amountGoodBeasts;

/** The amount bad beasts (preset by the GameConfig). */
private final int amountBadBeasts;

/** The amount good plants (preset by the GameConfig). */
private final int amountGoodPlants;

/** The amount bad plants (preset by the GameConfig). */
private final int amountBadPlants;

/** The amount walls (preset by the GameConfig). */
private int amountWalls;

/** The ent set contains all existing Entitys in a linked List. */
private EntitySet entSet;

/** The board config contains all presets (size, amount of Entitys...). */
private BoardConfig boardConfig;

/** The Constant logger is there for logging important Informations inside of the Board Class. */
private static final GameLogger logger = new GameLogger();
private String[] masterBotImpls;

    /**
     * Instantiates a new board for the Game.
     *
     * @param config this Object contains the Informations which will be used to create the GameField (Size, Amount of Entitys...)
     */
    public Board(BoardConfig config){
	this.length = config.getLength();
	this.width = config.getWidth();
        this.size = config.getSize();
        this.entSet = new EntitySet();
	this.amountGoodBeasts = config.getAmountGoodBeasts();
	this.amountBadBeasts = config.getAmountBadBeasts();
	this.amountGoodPlants = config.getAmountGoodPlants();
	this.amountBadPlants = config.getAmountBadPlants();
        this.boardConfig = config;
	fillBoard(this.amountGoodBeasts,this.amountBadBeasts,this.amountGoodPlants,this.amountBadPlants,this.amountWalls,config.getMasterBotImpls());
	logger.log(Level.FINEST, "Object der Klasse Board erstellt");
    };
    
    
    /**
     * This Constructor will only be used for JUnit Tests.
     *
     * @param config the config
     * @param masterSquirrel the master squirrel
     * @param masterBot the master bot
     * @param goodBeast the good beast
     * @param badBeast the bad beast
     * @param goodPlant the good plant
     * @param badPlant the bad plant
     * @param miniSquirrel the mini squirrel
     */
    public Board(BoardConfig config, GuidedMasterSquirrel masterSquirrel, MasterSquirrelBot masterBot, GoodBeast goodBeast, BadBeast badBeast, GoodPlant goodPlant, BadPlant badPlant, MiniSquirrel miniSquirrel){
	this.length = config.getLength();
	this.width = config.getWidth();
        this.size = config.getSize();
        this.entSet = new EntitySet();
	this.amountGoodBeasts = 0;
	this.amountBadBeasts = 0;
	this.amountGoodPlants = 0;
	this.amountBadPlants = 0;
	prepBoard(masterSquirrel, masterBot, goodBeast, badBeast, goodPlant, badPlant, miniSquirrel);
	logger.log(Level.FINEST, "Testobject der Klasse Board erstellt");
    };
        
    /** This Method will only be used to create a TestCase Board. 
     * 
     *
     * @param masterSquirrel the master squirrel
     * @param masterBot the master bot
     * @param goodBeast the good beast
     * @param badBeast the bad beast
     * @param goodPlant the good plant
     * @param badPlant the bad plant
     * @param miniSquirrel the mini squirrel
     */
    // Methode um gezielt Entiys auf vorgegebene Stellen zu platzieren um Testcases zu erm�glichen (Ersatz f�r fillBoard)
    private void prepBoard(GuidedMasterSquirrel masterSquirrel, MasterSquirrelBot masterBot, GoodBeast goodBeast, BadBeast badBeast, GoodPlant goodPlant, BadPlant badPlant, MiniSquirrel miniSquirrel){
    	if(masterSquirrel != null){
    		this.entSet.add("GuidedMasterSquirrel", masterSquirrel.getLocation().getX(), masterSquirrel.getLocation().getY());
    	}
    	if(masterBot != null){
    		this.entSet.add("MasterSquirrelBot", masterBot.getLocation().getX(), masterBot.getLocation().getY());
    	}
    	
    	if(goodBeast != null){
    		this.entSet.add("GoodBeast", goodBeast.getLocation().getX(), goodBeast.getLocation().getY());
    	}
    	
    	if(badBeast != null){
    		this.entSet.add("BadBeast", badBeast.getLocation().getX(), badBeast.getLocation().getY());
    	}
    	
    	if(goodPlant != null){
    		this.entSet.add("GoodPlant", goodPlant.getLocation().getX(), goodPlant.getLocation().getY());
    	}
    	
    	if(badPlant != null){
    		this.entSet.add("BadPlant", badPlant.getLocation().getX(), badPlant.getLocation().getY());
    	}
    	if(miniSquirrel != null){
    		this.entSet.add("MiniSquirrel", miniSquirrel.getLocation().getX(), miniSquirrel.getLocation().getY());
    	}

    	//Walls 
        for(int i = 0; i<= this.length;i++){
            this.entSet.add("Wall", i, 0);
            this.entSet.add("Wall", i, this.width);
        }
        
        for(int i = 1; i<this.width;i++){
            this.entSet.add("Wall", 0, i);
            this.entSet.add("Wall", this.length, i);
        }
    }
    
    /**
     * This Method fills the Board with given Entitys (from the Config) on Random Locations.
     *
     * @param amountGoodBeasts the amount good beasts
     * @param amountBadBeasts the amount bad beasts
     * @param amountGoodPlants the amount good plants
     * @param amountBadPlants the amount bad plants
     * @param amountWalls the amount walls
     * @param masterBotImpls the master bot impls
     */
    //Erstellen aller Entitys an zufälligem Ort
    private void fillBoard(int amountGoodBeasts, int amountBadBeasts, int amountGoodPlants, int amountBadPlants, int amountWalls, String[] masterBotImpls){
        
        this.entSet.add(new GuidedMasterSquirrel(entSet.getLatestId(),randLoc()[0],randLoc()[1]));
        
    try {
        //Mit den daten aus der BoardConfig BotFactory aufrufen und mit jedem Bot aus dem Array ein add aufrufen
        //EntitySet.add() eventuell umschreiben, sodass ein Objekt reingegeben wird und hier mit EntitySet.getLatestId() arbeiten
        
        this.masterBotImpls = this.getClasses(this.getConfig().getMasterBotImplsLocation());
    } catch (ClassNotFoundException | IOException ex) {
        logger.log(Level.SEVERE, "Klassen aus botimpls konnten nicht geladen werden");
    }
        BotControllerFactoryImpl botFact = new BotControllerFactoryImpl();
        BotController[] botCon = botFact.createMasterBotController(this.masterBotImpls);
        for(int i=0;i<botCon.length;i++){
            this.entSet.add(new MasterSquirrelBot(entSet.getLatestId(), randLoc()[0], randLoc()[1], botCon[i]));
        }
        
    	for(int i = 0; i!=amountGoodBeasts;i++)
            this.entSet.add(new GoodBeast(entSet.getLatestId(), randLoc()[0], randLoc()[1]));
        for(int i = 0; i!=amountBadBeasts;i++)
            this.entSet.add(new BadBeast(entSet.getLatestId(), randLoc()[0], randLoc()[1]));
        for(int i = 0; i!=amountGoodPlants;i++)
            this.entSet.add(new GoodPlant(entSet.getLatestId(), randLoc()[0], randLoc()[1]));
        for(int i = 0; i!=amountBadPlants;i++)
            this.entSet.add(new BadPlant(entSet.getLatestId(), randLoc()[0], randLoc()[1]));
        for(int i = 0; i!=amountWalls;i++)
            this.entSet.add(new Wall(entSet.getLatestId(), randLoc()[0], randLoc()[1]));
        for(int i = 0; i<= this.length;i++){
            this.entSet.add(new Wall(entSet.getLatestId(), i, 0));
            this.entSet.add(new Wall(entSet.getLatestId(), i, this.width));
        }
        for(int i = 1; i<this.width;i++){
            this.entSet.add(new Wall(entSet.getLatestId(), 0, i));
            this.entSet.add(new Wall(entSet.getLatestId(), this.length, i));
        }
    };

    /**
     * This Method generates a random int Array.
     *
     * @return the random int[].
     */
    public int[] randLoc(){
        int[] randVector = new int[]{((int)((Math.random()*(length-1))+1)),((int)((Math.random()*(width-1))+1))};
        LinkedList<Entity> entArray = entSet.getEntityVector();
        boolean isTaken = false;
            do{
                for(int i = 0; entArray.size()<i; i++){
                    if(entArray.get(i).getLocation().getPos()==randVector){
                        isTaken = true;
                        randVector = new int[]{(int)(((Math.random()*(length-1))+1)),(int)(((Math.random()*(width-1))+1))};
                        break;
                    }
                    else
                        isTaken = false;
                }
            }while(isTaken);
        return randVector;
    }
    
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
    public String toString(){
    	return entSet.toString();
    }

    /**
     * This Method calculates the amount of Entitys on the Board.
     *
     * @return the last ID of the entitySet Array.
     */
    public int getEntityCount() {
	return entSet.getLatestId();
	}
    
    /**
     * This Method generates a second Board. This will be used for the view not for operations.
     *
     * @return the board for the view.
     */
    public FlattenedBoard flatten(){ //Board übergeben und für flattendBoard nutzen
        return new FlattenedBoard(this);
    }
    
    /**
     * This Method returns the EntitySet Object of the Board. 
     * It contains all Entitys of the Game.
     *
     * @return the EntitySet Object.
     */
    public EntitySet getEntitySet(){
        return this.entSet;
    }
    
    /**
     * This Method gets the size of the Board.
     *
     * @return the BoardSize Object.
     */
    public XY getSize(){
        return this.size;
    }
    
    /**
     * This Method gets the configuration of the GameBoard.
     *
     * @return the GameBoard Configuration Object.
     */
    public BoardConfig getConfig(){
        return this.boardConfig;
    }
    
    
    /**
     * This Method gets Classes from a packageName.
     * @param packageName. is the Name of the Package where the classes should be loaded from.
     * @return the ClassNames in a String[] names.
     * @throws ClassNotFoundException gets thrown if you class is not found with the given Package.
     * @throws IOException gets thrown if there are problems with reading from the Path.
     */
    private static String[] getClasses(String packageName) throws ClassNotFoundException, IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assert classLoader != null;
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = classLoader.getResources(path);
    List<File> dirs = new ArrayList<File>();
    while (resources.hasMoreElements()) {
        URL resource = resources.nextElement();
        dirs.add(new File(resource.getFile()));
    }
    ArrayList<Class> classes = new ArrayList<Class>();
    for (File directory : dirs) {
        classes.addAll(findClasses(directory, packageName));
    }
    
     String[] names = new String[classes.size()];
    for (int i=0;i<classes.size();i++){
        names[i]=classes.get(i).getName();
    }
    //return classes.toArray(new Class[classes.size()]);
    return names;
}
    /**
     * This Method saves Classes into a List.
     * @param directory where the classes are located.
     * @param packageName name of the Package where the classes are located.
     * @return the list of classes.
     * @throws ClassNotFoundException gets thrown if there is no Class found by the given Parameters.
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
    List<Class> classes = new ArrayList<Class>();
    if (!directory.exists()) {
        return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
        if (file.isDirectory()) {
            assert !file.getName().contains(".");
            classes.addAll(findClasses(file, packageName + "." + file.getName()));
        } else if (file.getName().endsWith(".class")) {
            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
        }
    }
    return classes;
}
}
