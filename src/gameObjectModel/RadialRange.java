package gameObjectModel;

import java.util.List;


public class RadialRange extends AbstractRange{

    private int rangeVal; 
    
    public RadialRange(boolean jump, int distance, String name ){ 
        super(name, jump);
        rangeVal = distance; 
    }
    
    public int getRange(){
        return rangeVal; 
    }

    @Override
    public List<List<Integer>> getDeltas (List<Integer> position) {
        return null;
    }

}
