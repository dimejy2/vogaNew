package gameObjectModel;

import java.util.List;

public abstract class AbstractRange {
    private boolean canJump;
    private String myType;
    public abstract List<List<Integer>> getDeltas(List<Integer> position);
    public AbstractRange(String s, boolean b){
        canJump = b;
        myType = s;
    }
    public boolean canJump(){
        return canJump;
    };
    public String getType(){
        return myType;
    }
}
