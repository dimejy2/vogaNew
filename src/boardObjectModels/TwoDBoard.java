package boardObjectModels;

import gameObjectModel.GameObject;

import java.util.List;

public class TwoDBoard extends AbstractBoard {

	public TwoDBoard(List<Integer> Dimensions) {
		super(Dimensions);
		patchList = new PatchHolder(Dimensions); 
	}
	

}
