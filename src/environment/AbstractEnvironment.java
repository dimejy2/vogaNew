package environment;

import inputs.KeyHandler;
import layouts.AbstractLayout;
import boardObjectModels.ObservableBoardHolder;
import Data.DataHandler;

public abstract class AbstractEnvironment {
    protected ObservableBoardHolder myBoardHolder;
    protected DataHandler myDataHandler;
    public AbstractLayout myGameView;

    public AbstractLayout getView(){
        return myGameView;
    }

    public void addKeyListener (KeyHandler keyHandler) {
    }
}
