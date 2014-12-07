package view;

import java.util.*;

import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DialogBox{
	public String xString, yString, teamString, moveString;
	public List<Integer> gameConditions;

	public DialogBox(){
		Stage dialogStage = new Stage();
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(40, 0, 0, 50));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		
		Scene scene = new Scene(gridPane, 450, 250);
		
		Label xLabel = new Label("Give dimension for width");
		Label yLabel = new Label("Give dimension for height");
		Label teamLabel = new Label("Number of teams");
		Label moveLabel = new Label("Number of moves per turn");
		
		TextField xDim = new TextField();
		TextField yDim = new TextField();
		TextField numTeams = new TextField();
		TextField numMoves = new TextField(); 
		
		Button enter = new Button("Enter");
		
		GridPane.setHalignment(xLabel, HPos.RIGHT);
		GridPane.setHalignment(yLabel, HPos.RIGHT);
		GridPane.setHalignment(teamLabel, HPos.RIGHT);
		GridPane.setHalignment(moveLabel, HPos.RIGHT);
		
		gridPane.add(xLabel, 0, 0);
		gridPane.add(xDim, 1, 0);
		gridPane.add(yLabel, 0, 1);
		gridPane.add(yDim, 1, 1);
		gridPane.add(teamLabel, 0, 2);
		gridPane.add(numTeams, 1, 2);
		gridPane.add(moveLabel, 0, 3);
		gridPane.add(numMoves, 1, 3);
		
		gridPane.add(enter, 1, 4);
		
		enter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				xString = xDim.getText();
				yString = yDim.getText();
				teamString = numTeams.getText();
				moveString = numMoves.getText();
				gameConditions = new ArrayList<Integer>();
				gameConditions.add(Integer.parseInt(xString)); 
				gameConditions.add(Integer.parseInt(yString));
				gameConditions.add(Integer.parseInt(teamString));
				gameConditions.add(Integer.parseInt(moveString));
				System.out.println(gameConditions);
				dialogStage.close();
			}
		});
		
		dialogStage.setTitle("Game Conditions");
		dialogStage.setScene(scene);
		dialogStage.show();
	}
}