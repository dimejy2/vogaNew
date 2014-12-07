package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import environment.*;

public class SceneManager {
	private Scene myScene;
	private int authoringNumber = 0, playingNumber = 0;
	private int DEFAULT_SCREEN_WIDTH;
	private int DEFAULT_SCREEN_HEIGHT;
	public GUIToolBar topbar;
	public TabPane environments;
	protected List<Tab> playing;
	protected List<Tab> authoring;
	private Stage st;
	private ResourceBundle myConstants;
	public static final String CONSTANTS_PATH = "resources/constants";

	public void createScene(Stage s) {
		myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
		DEFAULT_SCREEN_WIDTH = Integer.parseInt(myConstants.getString("Default_Screen_Width"));
		DEFAULT_SCREEN_HEIGHT = Integer.parseInt(myConstants.getString("Default_Screen_Height"));
		FlowPane root = new FlowPane();
		myScene = new Scene(root, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		st = s;
		s.setScene(myScene);	
		s.setTitle("Environment");
		s.show();
		createToolBar(root);
	}

	private void createToolBar(FlowPane root) {
		topbar = new GUIToolBar();
		topbar.edit(root, this);
		topbar.createTab(root);
		playing = new ArrayList<Tab>();
		authoring = new ArrayList<Tab>();
		environments = topbar.getTab();
	}

	public void createEnvironment(String args) {
		st.setTitle(args+" Environment");
//		DialogBox dimensions = new DialogBox();
		Environment environment = new Environment(args);
//				Arrays.asList(Integer.parseInt(myConstants
//						.getString("Board_Test_Size")), Integer
//						.parseInt(myConstants.getString("Board_Test_Size"))));
		Tab start = new Tab();
		start.setText("Untitled" + " " + authoringNumber);
		authoringNumber++;
		start.setContent(environment.getPane());
		authoring.add(start);
		environments.getTabs().add(start);
	}
	
	public void switchScene() {
//		environments.getTabs().clear();
//		if (isAuthoringScene) {
//			switchScene(playing);
//		} else {			
//			switchScene(authoring);
//		}
	}

	public void switchScene(List<Tab> scene){
//		isAuthoringScene = !isAuthoringScene;
//		if(scene.isEmpty() && isAuthoringScene) createAuthoringEnvironment();
//		if(scene.isEmpty() && !isAuthoringScene) createPlayingEnvironment();
//		for(int i = 0; i < playing.size(); i++){
//			environments.getTabs().add(scene.get(i));
//		}
	}

	public Tab getTab() {
		ObservableList<Tab> itr = topbar.environment.getTabs();
		for (int i = 0; i < itr.size(); i++) {
			if (itr.get(i).isSelected()) {
				return itr.get(i);
			}
		}
		return null;
	}
}
