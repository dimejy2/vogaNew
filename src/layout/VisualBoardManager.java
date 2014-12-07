package layout;

import gameObjectModel.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import boardObjectModels.Board;
import boardObjectModels.Patch;

public class VisualBoardManager {
    private ResourceBundle myConstants;
    public static final String CONSTANTS_PATH = "resources/constants";
    private int myBoardDim;
    public VisualBoardManager(){
        myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
        myBoardDim = Integer.parseInt(myConstants.getString("Board_Test_Size"));
        }
    
    
    
    public List<Patch> getAvailablePatches(List<Integer> coords, Board board){
        GameObject obj = board.getGameObjectAt(coords);
        List<Patch> avail = new ArrayList<Patch>();

        if(obj!=null){
        int radius = obj.getMyMotion().moveDistance();
        for(int i = -radius; i<=radius; i++){
            for(int j = -radius; j<=radius; j++){
                if(!(i==0&&j==0)){
                    int x = coords.get(0)+i;
                    int y = coords.get(1)+j;
                    if(!(x>=myBoardDim||x<0||y>=myBoardDim||y<0)){
                    Patch p = board.getPatchAt(Arrays.asList(x,y));
                    if(p.getGameObject()==null) avail.add(board.getPatchAt(Arrays.asList(x,y)));
                    }
                }
            }
        }
        
        }
        return avail;
    }
    
    public List<Patch> getAttackablePatches(List<Integer> coords, Board board){
        GameObject obj = board.getGameObjectAt(coords);
        List<Patch> attack = new ArrayList<Patch>();

        if(obj!=null){
        int radius = obj.getMyMotion().moveDistance()*2;
        for(int i = -radius; i<=radius; i++){
            for(int j = -radius; j<=radius; j++){
                if(!(i==0&&j==0)){
                    int x = coords.get(0)+i;
                    int y = coords.get(1)+j;
                    if(!(x>=myBoardDim||x<0||y>=myBoardDim||y<0)){
                    Patch p = board.getPatchAt(Arrays.asList(x,y));
                    if(p.getGameObject()!=null) attack.add(board.getPatchAt(Arrays.asList(x,y)));
                    }
                }
            }
        }
        
        }
        return attack;
    }

}
