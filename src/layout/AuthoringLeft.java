package layout;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.FolderList;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AuthoringLeft {
	private AbstractLayout myLayout;
	private static BankTab imageBank;
	private static Set images;
	public AuthoringLeft(AbstractLayout layout) {
	    myLayout = layout;
		TabPane myTabPane = new TabPane();
		imageBank = new BankTab("Image Bank");
		BankTab objectBank = new BankTab("Object Bank");
	//	initializeImages();
		myTabPane.getTabs().addAll(objectBank.getTab(),imageBank.getTab());
		myTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		myLayout.getPane().setLeft(myTabPane);
	}
	
	public void initializeImages(){
		FolderList folders = new FolderList();
		List<String> imageLocations = folders.getContents("src//images");
		for(String loc : imageLocations){
			ImageView img = new ImageView(new Image("/images/" + loc));
			newImage(img);
		}
	}
	
	public void newImage(ImageView img){
		images = new HashSet<ImageView>();
		images.add(img);
		imageBank.addImage(img);
	}
}
