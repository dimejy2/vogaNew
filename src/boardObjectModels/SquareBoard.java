package boardObjectModels;

import java.util.List;

public class SquareBoard extends TwoDBoard{
	private int myWidth, myHeight;
	public SquareBoard(List<Integer> Dimensions){
	    super(Dimensions);
		myWidth = Dimensions.get(0);
		myHeight = Dimensions.get(1);
	}
}
