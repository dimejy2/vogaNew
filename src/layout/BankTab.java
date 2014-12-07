package layout;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;

public class BankTab {
	private String myName;
	private FlowPane myFlowPane;
	private ScrollPane myScrollPane;
	private Tab myTab;
	private int DEFAULT_WIDTH = 1280;
	private int DEFAULT_HEIGHT = 720;
	private int IMG_W = 80;
	private int IMG_H = 80;
	
	public BankTab(String name){
		myName = name;
		createBank();
	}
	
	private void createBank(){
		myFlowPane = new FlowPane();
		myScrollPane = new ScrollPane();
		myTab = new Tab();
		myFlowPane.setStyle("-fx-background-color: DAE6F3;");
		myFlowPane.setPrefWrapLength(DEFAULT_WIDTH/5);
		myFlowPane.setVgap(5);
		myFlowPane.setHgap(5);
		myScrollPane.setPrefSize(DEFAULT_WIDTH/5, ((DEFAULT_HEIGHT)*6)/8);
		myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		myScrollPane.setContent(myFlowPane);
		myTab.setText(myName);
		myTab.setContent(myScrollPane);
	}
	
	public Tab getTab(){
		return myTab;
	}
	
	public void clear(){
		myFlowPane.getChildren().clear();
	}

//	public void addImage(String string) {
////		 BufferedImage bf = null;
////	        try {
////	            bf = ImageIO.read(new File(string));
////	        } catch (IOException ex) {
////	            System.out.println("Image failed to load.");
////	        }
////	 
////	        WritableImage wr = null;
////	        if (bf != null) {
////	            wr = new WritableImage(bf.getWidth(), bf.getHeight());
////	            PixelWriter pw = wr.getPixelWriter();
////	            for (int x = 0; x < bf.getWidth(); x++) {
////	                for (int y = 0; y < bf.getHeight(); y++) {
////	                    pw.setArgb(x, y, bf.getRGB(x, y));
////	                }
////	            }
////	        }
////		BufferedImage bufferedImage = null;
////		try {
////			bufferedImage = ImageIO.read(new File(string));
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//		ImageView newImage = new ImageView(new Image(getClass().getResourceAsStream(string)));
//		newImage.setFitHeight(80);
//		newImage.setFitWidth(80);
//		myFlowPane.getChildren().add(newImage);
//	}
	
	public void addImage(ImageView img){
		img.setFitHeight(IMG_H);
		img.setFitWidth(IMG_W);
		myFlowPane.getChildren().add(img);
	}
	
}
