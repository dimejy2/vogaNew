package environment;

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

}
}
