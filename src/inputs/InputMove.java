package inputs;


import gameObjectModel.GameObject;

import java.util.List;

public class InputMove extends InputObject{

	private List<Integer> myFinalPos; 
	private InputRemove myRemove;
	private InputObject myAdd; 
	
	
	public InputMove(GameObject g, List<Integer> coord,List<Integer> finalCoord){
		super(g,coord); 
	myRemove = new InputRemove( g, coord); 
	myAdd = new InputObject(g, finalCoord ); 
	}
	
	@Override
	public AbstractInput getMyOpposite() {
		// TODO Auto-generated method stub
		InputMove myOpposite = new InputMove(myGameObject, myFinalPos, myCoordinates ); 
		myOpposite.setReverse(true);
		return myOpposite;
	}

	
	public InputRemove getMyRemove(){
	    return myRemove; 
	} 
	
	public InputObject getMyAdd(){
	    return myAdd; 
	} 

	
}
