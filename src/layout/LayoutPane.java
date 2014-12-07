package layout;

import inputs.AbstractInput;
import javafx.scene.layout.BorderPane;

public class LayoutPane extends BorderPane{
	private AbstractLayout myLayout;
	public LayoutPane(AbstractLayout layout){
		super();
		myLayout = layout;
	}
	public void notify(AbstractInput in){
		myLayout.setChangedAndNotify(in);
	}
}
