package inputs;

public class RestartUpdate extends AbstractInput{
	
	private boolean isRestart;
	
	public RestartUpdate(boolean restart){
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
