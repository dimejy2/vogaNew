package view;

//import inputs.InputRestart;
//import inputs.InputSaveLoad;

import java.nio.file.Path;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import layout.LayoutPane;

public class GUIToolBar {
	private int SCREEN_WIDTH = 1280;
	private int SCREEN_LENGTH = 720;
	public TabPane environment = new TabPane();
	protected ToolBar toolBar;
	protected LoadNSave saver;

	/*
	 * this class creates the toolBar and the tabPane used for the Scene
	 */
	public void edit(FlowPane root, SceneManager s) {
		saver = new LoadNSave();
		toolBar = new ToolBar();
		MenuBar mainMenu = new MenuBar();

		Menu file = new Menu("File");
		Menu edit = new Menu("Edit");
		Menu launch = new Menu("Launch");

		MenuItem openFile = new MenuItem("Open File");
		openFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Path b = saver.read();
//				InputSaveLoad load = new InputSaveLoad(b, false);
//				((LayoutPane) s.getTab().getContent()).notify(load);
			}
		});

		MenuItem saveFile = new MenuItem("Save File");
		saveFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Path b = saver.write();
//				InputSaveLoad save = new InputSaveLoad(b, true);
//				((LayoutPane) s.getTab().getContent()).notify(save);
			}
		});

		MenuItem restart = new MenuItem("Restart");
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				InputRestart restart = new InputRestart(true);
//				((LayoutPane) s.getTab().getContent()).notify(restart);
			}
		});

		MenuItem pause = new MenuItem("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		MenuItem play = new MenuItem("Play");
		play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		MenuItem newTab = new MenuItem("New Tab");
		newTab.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				if (s.isAuthoringScene) {
//					s.createAuthoringEnvironment();
//				} else{
//					s.createPlayingEnvironment();
//				}
			}
		});

		Button switcher = new Button("Switch Environment");
		switcher.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				s.switchScene();
			}
		});

		MenuItem playing = new MenuItem("Start with Play Environment");
		playing.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(s.playing.isEmpty()){
					s.environments.getTabs().clear();
					s.createEnvironment("Playing");
				}
			}
		});

		MenuItem authoring = new MenuItem("Start with Authoring Environment");
		authoring.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(s.authoring.isEmpty()){
					s.environments.getTabs().clear();
					s.createEnvironment("Authoring");
				}
			}
		});

		MenuItem previous = new MenuItem("Previous");

		MenuItem switching = new MenuItem("Switch");
		switching.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				s.switchScene();
			}
		});

		MenuItem forward = new MenuItem("forward");
		
		file.getItems().addAll(openFile, saveFile, restart, pause, play, newTab);
		edit.getItems().addAll(previous, forward);
		launch.getItems().addAll(playing, authoring, switching);

		mainMenu.getMenus().addAll(file, edit, launch);
		toolBar.getItems().addAll(mainMenu);
		toolBar.getItems().addAll(switcher);
		toolBar.setPrefWidth(SCREEN_WIDTH);

		root.setOrientation(Orientation.VERTICAL);
		root.getChildren().addAll(toolBar);
	}

	public void createTab(FlowPane root) {
		root.getChildren().addAll(environment);
	}

	public TabPane getTab() {
		return environment;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}
}
