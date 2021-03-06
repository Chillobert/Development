package de.hsa.games.util.ui.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

import de.hsa.games.fatsquirrel.UI;
import de.hsa.games.fatsquirrel.XY;
import de.hsa.games.fatsquirrel.console.GameCommandType;
import de.hsa.games.fatsquirrel.core.BoardConfig;
import de.hsa.games.fatsquirrel.core.BoardView;
import de.hsa.games.fatsquirrel.core.MasterSquirrelBot;
import de.hsa.games.fatsquirrel.logger.GameLogger;
import de.hsa.games.fatsquirrel.util.ui.console.Command;
import de.hsa.games.fatsquirrel.util.ui.console.CommandScanner;

// TODO: Auto-generated Javadoc
/**
 * The Class FxUI.
 */
public class FxUI extends Scene implements UI {

	/** The cell size. */
	private static int CELL_SIZE = 20;
	
	/** The key event. */
	private KeyEvent keyEvent;
	
	/** The board canvas. */
	private Canvas boardCanvas;
	
	/** The msg label. */
	private Label msgLabel;
	
	/** The input. */
	private String input;
	
	/** The command. */
	private Command command;
	
	/** The command types. */
	private GameCommandType[] commandTypes;
	
	/** The input reader. */
	private BufferedReader inputReader;
	
	/** The health. */
	private static String health;
	
	/** The caster. */
	private String caster;
	
	/** The value property. */
	private static StringProperty valueProperty;
	
	/** The Constant logger. */
	private static final GameLogger logger = new GameLogger();
	
    /**
     * Instantiates a new fx ui.
     *
     * @param parent the parent
     * @param boardCanvas the board canvas
     * @param msgLabel the msg label
     */
    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.commandTypes = GameCommandType.values();
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
        logger.log(Level.FINEST, "Object der Klasse FxUI erstellt");
        Logger.getLogger("java.awt").setLevel(Level.OFF); //gui meldungen deaktivieren
        Logger.getLogger("java.fx").setLevel(Level.OFF);  //gui meldungen deaktivieren

    }
    
    /**
     * Creates the instance.
     *
     * @param boardConfig the board config
     * @return the fx ui
     */
    //Diese Methode erstellt eine FxUI Instanz (GUI) Anschlie�end werden verschiedene Elemente der UI initialisiert
    public static FxUI createInstance(BoardConfig boardConfig) {
	   Canvas boardCanvas = new Canvas(boardConfig.getLength()*(CELL_SIZE + 1), boardConfig.getWidth()*(CELL_SIZE + 1));

       Label statusLabel = new Label();
       Label healthInfo = new Label();
       healthInfo.setText(health);
       VBox top = new VBox();
       
       top.getChildren().add(healthInfo);
       top.getChildren().add(boardCanvas);
       top.getChildren().add(statusLabel);
       
       
       statusLabel.setText("You're the blue Rectangle - try to survive" + "\n" + 
    		   				"Available Commands:" + "\n" + 
    		   				"W,A,S,D for moving your Squirrel" + "\n" +
    		   				"X for Spawn MiniSquirrel" + "\n" +
    		   				"E for Exit" + "\n" +
    		   				"H for more details\n");
        
        
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel); 

        //Dieser Eventhandler behandelt den UserInput und speichert ihn in der Variable input
        //Anschlie�end wird anhand des UserInputs der zugeh�rige Command ermittelt
        //Der ermittelte Command wird lokal in der Variable command gespeichert.
        fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
                   private String input;

				@Override
                   public void handle(KeyEvent keyEvent) {
                	   //Hier auf Input abspringen W;A;S;D
                	   switch (keyEvent.getCode()){
                		   case W:  fxUI.setInput("up");
                		   			break;
                		   case A: fxUI.setInput("left");
		   					   	   break;
                		   case S: fxUI.setInput("down");
       		   					   break;
                		   case D: fxUI.setInput("right");
		   					       break;
                		   case X:  String energyInput = JOptionPane.showInputDialog(null,"Wieviel Energie möchten Sie auf ihr MiniSquirrel übertragen?");
                                            String direction = JOptionPane.showInputDialog(null,"In welche Richtung soll ihr MiniSquirrel Spawnen?(w,a,s,d)");
                                            fxUI.setInput("mini "+energyInput+" "+direction);
                		   		   break;
                		   case H: fxUI.setInput("help");
                		   		   break;
                		   case E: fxUI.setInput("exit");
                		   		   break;	
                	   }
                	                  	   
                	  fxUI.getCommand();                 
                   }
                }
          );
        return fxUI;
    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.UI#render(de.hsa.games.fatsquirrel.core.BoardView)
     */
    //Render Methode aus dem Interface UI
    @Override
    public void render(final BoardView view) {
    	//repaintBoardCanvas(view);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	
               repaintBoardCanvas(view);  
               
            }      
        });  
    }
    
    //Diese Methode rendert die GUI neu, hierf�r wird aus der BoardView das Spielfeld (flattenedBoard) abgescannt und die entsprechennden
    /**
     * Repaint board canvas.
     *
     * @param view the view
     */
    //Entitys mit verschiedenen Grafischen Elementen dargestellt.
    private void repaintBoardCanvas(BoardView view) {//boardview view
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth()*CELL_SIZE, boardCanvas.getHeight()*CELL_SIZE);
        XY viewSize = view.getSize();
        
        // dummy for rendering a board snapshot, TODO: change it!
        
        // FlattenedBoard wird gescannt 
        
        for (int row = 0; row <= view.getSize().getX(); row++) {
            for (int column = 0; column <= view.getSize().getY(); column++) {
            	switch (view.getEntityType(row, column)){
            	
            	case "Wall": 
            		gc.setFill(Color.ORANGE);
            		gc.fillRect(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		break;
            	
            	case "GoodPlant":
            		gc.setFill(Color.GREEN);
            		gc.fillOval(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		break;
            		
            	case "BadPlant":
            		gc.setFill(Color.RED);
            		gc.fillOval(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		break;      
            
            	case "GoodBeast":
            		gc.setFill(Color.GREEN);
            		gc.fillRect(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		break;               		
            		            		
            		
            	case "BadBeast":
            		gc.setFill(Color.RED);
            		gc.fillRect(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		break;   
            		
            		
            	case "GuidedMasterSquirrel":
            		gc.setFill(Color.BLUE);
            		gc.fillRect(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		this.health = String.valueOf(view.getEntity(row, column).getEnergy());
                        //System.out.println("aktuelle Energie ihres Squirrels: "+health);
            		break;               		

            	case "MiniSquirrel":case"MiniSquirrelBot":
            		gc.setFill(Color.BLUE);
            		gc.fillRect(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE*0.5, CELL_SIZE*0.5);
            		break;
                        
                case "MasterSquirrelBot":
                    gc.setFill(Color.BLUE);
            		gc.fillRect(view.getEntity(row, column).getLocation().getX()*CELL_SIZE, view.getEntity(row, column).getLocation().getY()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            		this.health = String.valueOf(view.getEntity(row, column).getEnergy());
                        //System.out.println("aktuelle Energie von "+((MasterSquirrelBot)view.getEntity(row, column)).getImplName()+" ist: "+health);
            		break;        
            	}
            	   
            }
        }

    }
    
    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.UI#message(java.lang.String)
     */
    @Override
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);            
            }      
        });         
    }

    /* (non-Javadoc)
     * @see de.hsa.games.fatsquirrel.UI#getCommand()
     */
    //Diese Methode lie�t anhand der Inputvariable input den dazugeh�rigen Command mithilfe des Commandscanners aus (sofern dieser vorhanden)
	@Override
	public Command getCommand() {
            if(this.input != null){
                try {
                    CommandScanner commandScanner = new CommandScanner(commandTypes, input); //inputReader wird zum Input aus CASE W A S D
                    command = commandScanner.next();
                } catch (IOException ioEx) {
                	//MasterSquirrel falsche EIngabe U NO
                	logger.log(Level.WARNING, "Warnung: FxUI.getCommand(); Ung�ltige Eingabe des Spielers");
                    System.out.println("Das war keine gültige Eingabe. probier es mal mit help");
                }
            return command;
        }
        return null;
	}
	
	/**
	 * Sets the command.
	 *
	 * @param command the new command
	 */
	//Diese Methode erm�glicht es den Command manuell zu setzen (Wird ben�tigt um in der GameImpl.processInput die Variable zu clearen)
	public void setCommand(Command command){
		this.command = command;
	}
	
	/**
	 * Give command.
	 *
	 * @return the command
	 */
	//Diese Methode �bergibt den Command der mithilfe der getCommand() Methode ermittelt wird.
	public Command giveCommand(){
		return command;
	}
	
	/**
	 * Sets the input.
	 *
	 * @param input the new input
	 */
	//Diese Methode speichert die Eingabe des Benutzers in den String input
	private void setInput(String input){
		this.input = input;
	}
	
}