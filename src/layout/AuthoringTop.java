package layout;

import gameObjectModel.GameObject;
import gameObjectModel.Motion;
import inputs.ObjectUpdate;
import inputs.UndoRedoUpdate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import view.DialogBox;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.PassiveAttribute;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class AuthoringTop {
	private AbstractLayout myLayout;
	private ObjectUpdate newIO;

	public AuthoringTop(AbstractLayout layout){
		myLayout = layout;

		Button newObject = new Button("New Object");
		newObject.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				createObject();
			}
		});

		Button addImage = new Button("Add Image");
		addImage.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"));
				fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
				File file = fileChooser.showOpenDialog(new Stage());
				if(file!=null){
					try {
						BufferedImage bufferedImage = ImageIO.read(file);
						Image image = SwingFXUtils.toFXImage(bufferedImage, null);
						myLayout.updateImages(new ImageView(image));
					} catch (IOException ex) {

					}
				}

			}
		});

		Button undo = new Button("Undo");
		undo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				UndoRedoUpdate undoOrder = new UndoRedoUpdate(true);
				myLayout.setChangedAndNotify(undoOrder);
			}
		});

		Button redo = new Button("Redo");
		redo.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				UndoRedoUpdate redoOrder = new UndoRedoUpdate(false);
				myLayout.setChangedAndNotify(redoOrder);
			}
		});

		Button play = new Button("Play");
		play.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){

			}
		});

		Button clearBoard = new Button("Clear Board");
		clearBoard.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				
			}
		});

		Button conditions = new Button("Edit Game Conditions");
		conditions.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				DialogBox dialogBox = new DialogBox();
			}
		});

		ToolBar toolBar1 = new ToolBar();
		toolBar1.getItems().addAll(
				new Separator(),
				newObject,
				addImage,
				clearBoard,
				new Separator(),
				new Separator(),
				undo,
				redo,
				play,
				new Separator(),
				new Separator(),
				conditions,
				new Separator()
				);
		myLayout.getPane().setTop(toolBar1);
	}
	
	private void createObject() {
		newIO = new ObjectUpdate();
		Stage newStage = new Stage();
		VBox editables = new VBox();

		TextField nameField = new TextField("Object Name");
		editables.getChildren().add(nameField);

		CheckBox healthOn = new CheckBox("Health");
		TextField healthField = new TextField();
		editables.getChildren().add(healthOn);
		editables.getChildren().add(healthField);

		CheckBox attackOn = new CheckBox("Attack");
		TextField attackField = new TextField();
		editables.getChildren().add(attackOn);
		editables.getChildren().add(attackField);

		CheckBox horiz = new CheckBox("Horizontal Movement");
		CheckBox vert = new CheckBox("Vertical Movement");
		CheckBox diag = new CheckBox("Diagonal Movement");
		CheckBox canJump = new CheckBox("Can Jump");
		TextField moveField = new TextField("Move Distance");
		editables.getChildren().add(horiz);
		editables.getChildren().add(vert);
		editables.getChildren().add(diag);
		editables.getChildren().add(canJump);
		editables.getChildren().add(moveField);

		TextField xCord = new TextField("X Coordinate");
		TextField yCord = new TextField("Y Coordinate");
		editables.getChildren().add(xCord);
		editables.getChildren().add(yCord);

		Button done = new Button("Done");
		done.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent event){
				IntegerStringConverter conv = new IntegerStringConverter();
				Motion mover = new Motion(horiz.isSelected(), vert.isSelected(), diag.isSelected(), canJump.isSelected(), conv.fromString(moveField.getText()));
				List<AbstractAttribute> attribs = new ArrayList();
				if(healthOn.isSelected())
					attribs.add(new PassiveAttribute("health", Double.parseDouble(healthField.getText())));
				else
					attribs.add(new PassiveAttribute("health", 1.));
				if(attackOn.isSelected())
					attribs.add(new ActiveAttribute("attack", Double.parseDouble(attackField.getText()), "health", true, false));
				else attribs.add(new ActiveAttribute("attack", 1., "health", true, false));
				//attribs.add(new Name(nameField.getText()));
				GameObject go = new GameObject(attribs, mover);
				List<Integer> cords = new ArrayList();
				cords.add(conv.fromString(xCord.getText()));
				cords.add(conv.fromString(yCord.getText()));
				newIO.setCoordinates(cords);
				newIO.setGameObject(go);
				newStage.close();

				myLayout.setChangedAndNotify(newIO);
			}
		});
		editables.getChildren().add(done);
		Scene newObj = new Scene(editables,300,500);
		newStage.setScene(newObj);
		newStage.show();
	}
}