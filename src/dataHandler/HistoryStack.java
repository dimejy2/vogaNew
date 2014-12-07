package dataHandler;

import java.util.Stack;

import inputs.AbstractInput;

public class HistoryStack{

	private Stack<AbstractInput> executed; 
	private Stack<AbstractInput> undone; 
	private SimpleDialogFactory popupMaker;
	
	public HistoryStack (){
		executed = new Stack<>(); 
		undone = new Stack<>();
		popupMaker = new SimpleDialogFactory(); 
	}
	
	public void add( AbstractInput toPush){
		executed.push(toPush);
		if(!undone.isEmpty()) undone.clear();
	}
	
	public AbstractInput getCurrInput(){
		return executed.peek(); 
	}
	
	public AbstractInput undo(){
	    
	    AbstractInput input;
	    if(!executed.isEmpty())
	        {input = executed.pop();
	    undone.push(input);
	     return input.getMyOpposite();
	     } 
	   
	    popupMaker.emptyUndo();
	    return null; 
	}
	
	public AbstractInput redo(){
	    if(!undone.isEmpty()){
	    AbstractInput input = undone.pop();
	    executed.push(input);
	    input.setReverse(true);
        return input; 
	    }
	    popupMaker.emptyRedo();
	    return null;
	}
	
	public AbstractInput getLastUndone(){
	    return undone.peek(); 
	}
}