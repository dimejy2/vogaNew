package environment;

import layouts.AuthoringLayout;
import layouts.PlayingLayout;
import Data.DataHandler;
import boardObjectModels.ObservableBoardHolder;

public class Environment {
	protected boolean isAuthoring;
	protected AbstractLayout playing, authoring;
	
	public Environment(String typeOfEnvironment){
		myBoardHolder = new ObservableBoardHolder();
		myBoardHolder.updateBoard(new Board(dimensions));
		myDataHandler = new DataHandler(myBoardHolder);
		playing = new PlayingLayout();
		authoring = new AuthoringLayout();
		
		
		myGameView.addObserver(myDataHandler);
		myBoardHolder.addObserver(myGameView);
		myGameView.updateBoard(myBoardHolder);
	}
	
	public void switchEnvironment(){
		if(isAuthoring){
			myGameView = playing;
		} else{
			myGameView = authoring;
		}
		isAuthoring = !isAuthoring		
	}
	public BorderPane getView(){
		return myGameView.getPane();
	}
	
}
