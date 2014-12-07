package layout;

import javafx.scene.image.ImageView;
import environment.*;

public class PlayingLayout extends AbstractLayout{
	public PlayingLayout(){
		myPane = new LayoutPane(this);
		new PlayingTop(this);
		new PlayingLeft(this);
		new PlayingRight(this);
		new PlayingCenter(this);
		new PlayingBottom(this);
	}

	@Override
	void updateImages(ImageView imageView) {
		// TODO Auto-generated method stub
		
	}
}
