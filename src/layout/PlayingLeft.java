package layout;

import java.util.List;

import util.FolderList;
import javafx.geometry.Orientation;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;

public class PlayingLeft {
	private AbstractLayout myLayout;
	public PlayingLeft(AbstractLayout layout){
	    myLayout = layout;
		SplitPane myPane = new SplitPane();
		
		ToggleGroup movement  = new ToggleGroup();
		RadioButton controller = new RadioButton("Controller");
		RadioButton keyboard   = new RadioButton("Keyboard");
		movement.getToggles().addAll(keyboard, controller);
		
		ToggleGroup team = new ToggleGroup();
		RadioButton placeholder = new RadioButton("Buildings");
		RadioButton something   = new RadioButton("Units");
		team.getToggles().addAll(placeholder, something);		
		
		FlowPane movements = new FlowPane();
		movements.getChildren().addAll(controller, keyboard);
		movements.setOrientation(Orientation.VERTICAL);
//		FlowPane teams     = new FlowPane();
//		teams.getChildren().addAll(placeholder, something);
		myPane.getItems().addAll(movements);
		myPane.setOrientation(Orientation.VERTICAL);
		myPane.setPrefWidth(200);

		myLayout.getPane().setLeft(myPane);
	}
}
