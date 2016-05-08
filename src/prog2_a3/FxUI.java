package prog2_a3;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import prog2_a3.fatsquirrel.core.BoardConfig;
import prog2_a3.fatsquirrel.core.XY;
import prog2_a3.fatsquirrel.util.ui.console.Command;
import prog2_a3.interfaces.BoardView;
import prog2_a3.interfaces.UI;

public class FxUI extends Scene implements UI {


	private Canvas boardCanvas;
	private Label msgLabel;

	public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;

    }
    
    public static FxUI createInstance(BoardConfig boardConfig) {
		Canvas boardCanvas = new Canvas(boardConfig.getLength(), boardConfig.getWidth());
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("das ist der Label Text");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel); 
        fxUI.setOnKeyPressed(
                new EventHandler() {
				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					
				}
                }
          );
        return fxUI;
    }

    @Override
    public void render(final BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
               repaintBoardCanvas(view);            
            }      
        });  
    }
    
    private void repaintBoardCanvas(BoardView view) {//boardview view
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();
        
        // dummy for rendering a board snapshot, TODO: change it!
        gc.fillText("Where are the beasts?", 100, 100);
        gc.setFill(Color.RED);
        gc.fillOval(150, 150, 50, 50);
       /// 
    }
    

    @Override
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);            
            }      
        });         
    }

	@Override
	public Command getCommand() {
		// TODO Auto-generated method stub
		return null;
	}
}
