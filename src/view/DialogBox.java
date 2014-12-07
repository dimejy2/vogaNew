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

public class DialogBox {
	private ResourceBundle myConstants;
	private int xSize, ySize, teams, moves;

	public DialogBox() {
		Stage dialogStage = new Stage();
		final boolean done = false;
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
				xSize = Integer.parseInt(xDim.getText());
				ySize = Integer.parseInt(yDim.getText());
				teams = Integer.parseInt(numTeams.getText());
				moves = Integer.parseInt(numMoves.getText());
				if (xDim.getText() != null && yDim.getText() != null
						&& numMoves.getText() != null
						&& numTeams.getText() != null) {
					dialogStage.close();
				}
			}
		});

		dialogStage.setTitle("Game Conditions");
		dialogStage.setScene(scene);
		dialogStage.show();

		// prevents Authoring Environment from being created without
		// specifications from the user
		while (!done);
	}

	public int getXSize(){
		return xSize;
	}

	public int getYSize(){
		return ySize;
	}

	public int getNumTeams(){
		return teams;
	}

	public int getNumMoves(){
		return moves;
	}
}