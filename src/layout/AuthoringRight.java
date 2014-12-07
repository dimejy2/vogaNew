package layout;

import gameObjectModel.GameObject;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import attributes.AbstractAttribute;
import boardObjectModels.Board;

public class AuthoringRight {
    public AbstractLayout myLayout;
    public Board myBoard;
	
    public AuthoringRight(AbstractLayout layout){
	myLayout = layout;
	Pane rightPane = new Pane();
	myLayout.getPane().setRight(rightPane);
	}

    public void displayInfo(GameObject object){
        Pane popup = new Pane();
        popup.setPrefSize(100, 100);
        StringBuilder labelString = new StringBuilder();
        for(AbstractAttribute att : object.getAttributes()){
            labelString.append(att.getClass().getSimpleName()+": "+att.getValue()+"\n");
        }
        FlowPane flow = new FlowPane(Orientation.VERTICAL);
        flow.getChildren().add(new Label(labelString.toString()));
        popup.getChildren().add(flow);
        myLayout.getPane().setRight(popup);
    }
    
}
