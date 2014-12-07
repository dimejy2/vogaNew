package layout;

import inputs.AbstractInput;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import boardObjectModels.ObservableBoardHolder;

public class AuthoringLayout extends AbstractLayout{
	private AuthoringLeft leftside;
	
	public AuthoringLayout(){
		myPane = new LayoutPane(this);
		AuthoringTop topbar		  = new AuthoringTop(this);
		leftside     = new AuthoringLeft(this);
		AuthoringRight right   = new AuthoringRight(this);
		AuthoringCenter centerGrid = new AuthoringCenter(this);
		AuthoringBottom bottom     = new AuthoringBottom(this);
	}
	
	public void updateImages(ImageView img) {
		leftside.newImage(img);
	};
}
