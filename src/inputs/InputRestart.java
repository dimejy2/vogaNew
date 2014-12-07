package inputs;

public class InputRestart extends AbstractInput{
	
	private boolean isRestart;
	
	public InputRestart(boolean restart){
		isRestart = restart;
	}
	
	public boolean isRestart(){
		return isRestart;
	}

	@Override
	public AbstractInput getMyOpposite() {
		// TODO Auto-generated method stub
		return null;
	}
}
