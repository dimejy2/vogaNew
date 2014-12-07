package boardObjectModels;

import inputs.MoveUpdate;
import inputs.ObjectUpdate;
import inputs.RemoveUpdate;

import java.util.Observable;

public class ObservableBoardHolder extends Observable {
	private Board myBoard; 


 public void addObject(ObjectUpdate input){
     myBoard.addObject(input); 
     setChangedAndNotify();
	}	

	public void changeObjectOnBoard(Object arg){
		if( arg instanceof RemoveUpdate){
			removeObject((RemoveUpdate) arg);
		}
		else if( arg instanceof MoveUpdate){
		 
		    MoveUpdate temp = ( MoveUpdate) arg; 
		    removeObject( temp.getMyRemove()); 
		    addObject(temp.getMyAdd()); 
		}
		
		else if( arg instanceof ObjectUpdate){
			addObject( (ObjectUpdate)arg);
		} 
		
	}
 	
	public void updateBoard(Board board){
		myBoard = board;
		setChangedAndNotify();
	}
	
	public Board getBoard(){
		return myBoard;
	}
	
	public void setBoard(Board board){
		myBoard = board;
	}
	
	private void setChangedAndNotify(){
	    setChanged();
		notifyObservers();
	}
	public void removeObject(RemoveUpdate in){
	    myBoard.deleteGameObjectAtPatch(in.getCoordinates());
	    setChangedAndNotify();
	}
}
