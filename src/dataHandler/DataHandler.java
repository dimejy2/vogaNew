package dataHandler;


import gameObjectModel.GameObject;
import inputs.AbstractInput;
import inputs.InputInteract;
import inputs.InputSaveLoad;
import inputs.InputUndoRedo;
import interfaces.IDataHandler;

import java.beans.Introspector;
import java.nio.file.Path;
import java.util.Observable;
import java.util.Observer;

import util.Reflection;
import attributes.Attack;
import boardObjectModels.AbstractBoard;
import boardObjectModels.ObservableBoardHolder;


public class DataHandler implements Observer, IDataHandler, boardUpdate{

	private ObservableBoardHolder myBoardHolder; 
	private Serialiser mySerialiser;
	private DeSerialiser myDeSerialiser;

	private HistoryStack myStates;
	
	


	public DataHandler(ObservableBoardHolder board) {
	    mySerialiser = new Serialiser();
	    myDeSerialiser = new DeSerialiser();
		myStates = new HistoryStack();
		myBoardHolder = board;

	}

	private void writeData(AbstractBoard board, Path p) {
	    
		mySerialiser.serialise(board, p);

	}
	private void readData(Path p) {
	    AbstractBoard b = myDeSerialiser.deserialise(p);
	    myBoardHolder.updateBoard(b);
	}


	@Override
	public void undo() {
		AbstractInput input = myStates.undo();
		try{
		update(null, input);
		}
		catch( NullPointerException e){}
	}
	@Override
	public void redo() {
		AbstractInput input = myStates.redo();
		try{
		update(new Observable(), input); }
		catch(NullPointerException e){}
	}
	
	@Override
	public void update (Observable o, Object arg) {
		String methodName = Introspector.decapitalize(arg.getClass().getSimpleName()); 
		Reflection.callMethod(this, methodName, arg);
	}

	
	@Override
	public void executeMethod() {
	
	}

	
	protected void objectUpdate(Object arg){
	   	revChecker((AbstractInput) arg);
	   	
	}
	
	protected void moveUpdate(Object arg){
        revChecker((AbstractInput) arg);
        
    }
	
	
	protected void interactUpdate(Object arg){
       InputInteract input = (InputInteract) arg;
       GameObject receiver = input.getActor().getGameObject();
       Attack attr = (Attack)input.getAttribute();
       attr.action(receiver);
       myBoardHolder.updateBoard(myBoardHolder.getBoard());
       
        
    }

	private void revChecker(AbstractInput input) {
		myBoardHolder.changeObjectOnBoard(input);
		if(!input.isReverse()){
	   	addState(input);
	   	}
	}
	
	
	protected void removeUpdate(Object arg){
	    revChecker((AbstractInput) arg); 
	}

	public void undoRedoUpdate(Object arg){
	    
		InputUndoRedo input = (InputUndoRedo) arg;
		Boolean undo = input.isUndo();

		if(undo){
			undo();
		}
		else{
			redo();
		}
	}
	
	
	protected void saveloadUpdate(Object arg){
        
        InputSaveLoad input = (InputSaveLoad) arg;
        Boolean save = input.getIsSave();

        if(save){
            writeData(myBoardHolder.getBoard(), input.getPath());
        }
        else{
            readData(input.getPath());
        }
    }
	
	@Override
	public void addState(AbstractInput input) {
		myStates.add(input);
		
	}
	@Override
	public AbstractInput returnCurrentState() {
		return myStates.getCurrInput();
	}



}
