package layout;

import inputs.AbstractInput;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.ImageView;
import boardObjectModels.*;

public abstract class AbstractLayout extends Observable implements Observer{
	protected LayoutPane myPane;
	protected ObservableBoardHolder myBoardHolder;
	
	public LayoutPane getPane(){
	    return myPane;
	}
	
	public void updateBoard(ObservableBoardHolder board){
	    myBoardHolder = board;
	}
	public Board getBoard(){
	    return myBoardHolder.getBoard();
	}

    @Override
    public void update (Observable o, Object arg) {
        // TODO Render board for view by user
        AbstractCenter p = (AbstractCenter) myPane.getCenter();
        p.update();
    }
    
    public void setChangedAndNotify(AbstractInput in){
        setChanged();
        notifyObservers(in);
    }

	abstract void updateImages(ImageView imageView);
	
}
