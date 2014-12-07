package inputs;

import java.util.List;

import gameObjectModel.GameObject;

public class InputRemove extends InputObject{
      
    public InputRemove(GameObject g, List<Integer> coord){
        myGameObject = g;
        myCoordinates = coord;
    }
    
     public AbstractInput makeReverse(){
            myOpposite = new InputObject( getGameObject(), getCoordinates()); 
            myOpposite.setReverse(true);
            return myOpposite; 
     }

     @Override 
     public AbstractInput getMyOpposite(){
         makeReverse(); 
         return myOpposite; 
     }
   
}
