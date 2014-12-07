package boardObjectModels;

import gameObjectModel.GameObject;
import inputs.InputObject;

import java.util.ArrayList;
import java.util.List;

public class AbstractBoard{
	
	protected List<GameObject> gameObjectList; 
	protected PatchHolder patchList; 
	

	public AbstractBoard( List<Integer> Dimensions){
		gameObjectList = new ArrayList<GameObject>(); 
		
	}
	
	
	private AbstractBoard(List<GameObject> g, PatchHolder t){
		gameObjectList = g; 
		patchList = t; 
		
	}; 
	
	
	public void addPatch(Patch patch) {
		patchList.add(patch);
	}

	
	public void clearBoard() {
		patchList.clear();
	}
	public void setGameObjectList(List<GameObject> objectList){
	    gameObjectList = objectList;
	}
	
	public void setPatchList(PatchHolder patches){
        patchList = patches;
    }
	public PatchHolder getPatchList(){
       return patchList;
    }

	
	public GameObject getPatchGameObject(List<Integer> location) {
		// TODO Auto-generated method stub
		return patchList.getGameObjectAt(location);
	}
	public Patch getPatchAt(List<Integer> location) {
        // TODO Auto-generated method stub
        return patchList.getPatchAt(location);
    }

	
	public void setPatchGameObject(GameObject obj, List<Integer> location) {
		patchList.getPatchAt(location).setGameObject(obj) ;
		
	}

	
	public void deleteGameObjectAtPatch(List<Integer> location) {
		patchList.deleteGameObjectAt(location);
	}

	
	public void deletePatch(List<Integer> location) {
		patchList.deletePatchAt(location);
	}

    
    public void update (Object arg0) {
        // TODO Auto-generated method stub
        
    }

    
    public AbstractBoard addObject (InputObject input) {
        AbstractBoard tempBoard = new AbstractBoard(gameObjectList, patchList);
        
        //InputObject tempIn = new InputObject(tempBoard.getPatchGameObject(input.getCoordinates()),input.getCoordinates());
        
        tempBoard.setPatchGameObject(input.getGameObject(), input.getCoordinates());
        
        //input = tempIn;
        return tempBoard;
    }

    public String toString (){
        return patchList.toString(); 
    }
}
