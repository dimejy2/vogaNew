package environment;

import layout.*;
import boardObjectModels.ObservableBoardHolder;
import dataHandler.*;

public abstract class AbstractEnvironment {
	protected ObservableBoardHolder myBoardHolder;
	protected DataHandler myDataHandler;
	public AbstractLayout myGameView;

	public AbstractLayout getView(){
		return myGameView;
	}
}
