package inputs;

import gameObjectModel.GameObject;

import java.util.List;

 

public class ObjectUpdate extends AbstractInput {
	
	protected GameObject myGameObject; 
	protected List<Integer> myCoordinates; 
	
	public ObjectUpdate(){}
	
	public ObjectUpdate(GameObject g, List<Integer> coord){
	    myGameObject = g;
	    myCoordinates = coord; 
	}
	
	
	
	public void setGameObject(GameObject toSet){
		myGameObject = toSet; 
	}

	public void setCoordinates(List<Integer> coord){
		myCoordinates = coord; 
	}

	public GameObject getGameObject(){
		return myGameObject; 
	}
	
	public List<Integer> getCoordinates(){
		return myCoordinates; 
	}

	@Override
	public void executeMethod() {
		
	}
	
	@Override 
	public AbstractInput getMyOpposite(){
	    makeReverse(); 
	    return myOpposite; 
	}

	 public AbstractInput makeReverse(){
	        myOpposite = new RemoveUpdate( getGameObject(), getCoordinates()); 
	        myOpposite.setReverse(true);
	        return myOpposite; 
	 }
}
