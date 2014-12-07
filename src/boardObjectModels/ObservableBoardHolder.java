package boardObjectModels;

import inputs.InputMove;
import inputs.InputObject;
import inputs.InputRemove;

import java.util.Observable;

public class ObservableBoardHolder extends Observable {
	private AbstractBoard myBoard; 


 public void addObject(InputObject input){
     myBoard.addObject(input); 
     setChangedAndNotify();
	}	

	public void changeObjectOnBoard(Object arg){
		if( arg instanceof InputRemove){
			removeObject((InputRemove) arg);
		}
		else if( arg instanceof InputMove){
		 
		    InputMove temp = ( InputMove) arg; 
		    removeObject( temp.getMyRemove()); 
		    addObject(temp.getMyAdd()); 
		}
		
		else if( arg instanceof InputObject){
			addObject( (InputObject)arg);
		} 
		
	}
 	
	public void updateBoard(AbstractBoard board){
		myBoard = board;
		setChangedAndNotify();
	}
	
	public AbstractBoard getBoard(){
		return myBoard;
	}
	
	public void setBoard(AbstractBoard board){
		myBoard = board;
	}
	
	private void setChangedAndNotify(){
	    setChanged();
		notifyObservers();
	}
	public void removeObject(InputRemove in){
	    myBoard.deleteGameObjectAtPatch(in.getCoordinates());
	    setChangedAndNotify();
	}
}
