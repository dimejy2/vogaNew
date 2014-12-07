package inputs;

public abstract class AbstractInput{
    
    protected AbstractInput myOpposite;
    protected boolean reverse;
    
    public AbstractInput(){
        reverse = false;
    }
    
    public abstract AbstractInput getMyOpposite(); 
    
    
    public boolean isReverse(){
        return reverse;
    }
    
    public void setReverse(boolean b){
        reverse = b;
    }
	public void executeMethod() {
		// TODO Auto-generated method stub
		
	}

	public String toString(){
	    return this.getClass().getSimpleName(); 
	}
	
}
