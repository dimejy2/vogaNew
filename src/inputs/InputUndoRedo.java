package inputs;

public class InputUndoRedo extends AbstractInput{
	
	private boolean isUndo; 
	
	public InputUndoRedo(boolean undo){
		isUndo = undo; 
	}

	public boolean isUndo(){
		return isUndo; 
	}

	public boolean isRedo(){
		return !isUndo; 
	}

	@Override
	public AbstractInput getMyOpposite() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
