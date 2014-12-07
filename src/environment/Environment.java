package environment;

import java.util.Arrays;

import layout.*;
import dataHandler.*;
import boardObjectModels.*;

public class Environment extends AbstractEnvironment {
	protected boolean isAuthoring;
	protected AbstractLayout playing, authoring;
	
	public Environment(String typeOfEnvironment){
		myBoardHolder = new ObservableBoardHolder();
		myBoardHolder.updateBoard(new Board(Arrays.asList(20,20)));
		myDataHandler = new DataHandler(myBoardHolder);
		playing = new PlayingLayout();
		authoring = new AuthoringLayout();
		if(typeOfEnvironment.equals("playing")){
			myGameView = authoring;
		}else {
			myGameView = playing;
		}
		
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
		isAuthoring = !isAuthoring;		
	}
	public LayoutPane getPane(){
		return myGameView.getPane();
	}
}
