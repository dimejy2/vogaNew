package boardObjectModels;

import gameObjectModel.GameObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PatchHolder {

    private List<List<Patch>> myPatches; 

    public PatchHolder(List<Integer> Dimensions) {
        myPatches = new ArrayList<List<Patch>>(Dimensions.get(0));

        for(int i = 0; i<Dimensions.get(0); i++){
            List<Patch> patchList = new ArrayList<Patch>();
            for(int j = 0; j<Dimensions.get(1); j++){
                patchList.add(new Patch(Arrays.asList(i,j), 0)); //dummy resource amount here
            }
            myPatches.add(patchList);
        }


    }

    
    public int size() {
        return myPatches.size()*myPatches.get(0).size();
    }

    
    public boolean isEmpty() {
        boolean temp = true; 
        for(List<Patch> patchList : myPatches ) temp = temp && patchList.isEmpty(); 
        return temp;
    }

    
    public boolean contains(GameObject o) {
        for(List<Patch> patchList : myPatches) if (patchList.contains(o) )return true; 
        return false;
    }

    
    public Iterator<Patch> iterator() {
        return new MyIterator();

    }

    public class MyIterator implements Iterator<Patch> {
        private int myXCounter = 0;
        private int myYCounter = 0;

        public boolean hasNext() {
            return (myXCounter< myPatches.size()&&myYCounter<myPatches.get(myXCounter).size()-1);
        }

        public Patch next() { 
            int x = myXCounter;
            int y = myYCounter;
            myXCounter++;
            if(myXCounter>=myPatches.size()){
                myYCounter++;
                myXCounter = 0;
                if(myYCounter>=myPatches.get(myXCounter).size()) throw new NoSuchElementException();
            }
            return myPatches.get(x).get(y);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    
    public void deletePatchAt(List<Integer> location) {
        myPatches.get(location.get(0)).set(location.get(1), null); 
    }

    
    public GameObject getGameObjectAt(List<Integer> location) {
        return myPatches.get(location.get(0)).get(location.get(1)).getGameObject();
    }

    
    public void setGameObject(GameObject g, List<Integer> location) {
        myPatches.get(location.get(0)).get(location.get(1)).setGameObject(g);

    }

    
    public void deleteGameObjectAt(List<Integer> location) {
        myPatches.get(location.get(0)).get(location.get(1)).deleteGameObject(); 
    }

    
    public void clear() {
        for(List<Patch> patchList: myPatches){
            patchList.clear();
        }
    }

    
    public Patch getPatchAt(List<Integer> location) {
        return myPatches.get(location.get(0)).get(location.get(1));
    }

    
    public void add(Patch patch) {
        myPatches.get(patch.getPosition().get(0)).set(patch.getPosition().get(1), patch); 

    }

    public String toString(){
        StringBuilder toReturn = new StringBuilder(); 
        
        for(int i = 0 ; i < myPatches.size()-1; i++ ){
            for( int j = 0 ; j < myPatches.get(i).size(); j ++){
                if ( !myPatches.get(j).get(i).equals(null)) {
                    toReturn.append(myPatches.get(j).get(i).toString() + " " ); 
                }
                else toReturn.append( "null"); 
            }

            toReturn.append("\n"); 
        }
        return toReturn.toString(); 
    }
}
