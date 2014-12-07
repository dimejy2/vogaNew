package layout;

import gameObjectModel.*;
import inputs.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import attributes.*;
import boardObjectModels.*;

public class AuthoringCenter extends AbstractCenter {

    private GridPane myVisualBoard;
    private Pane myVisualBoardHolder;
    private Pane myPane;
    private Board myBoard;
    private AbstractLayout myLayout;
    private ResourceBundle myConstants;
    public static final String CONSTANTS_PATH = "resources/constants";

    public AuthoringCenter(AbstractLayout layout) {
        myConstants = ResourceBundle.getBundle(CONSTANTS_PATH);
        myLayout = layout;
        myLayout.getPane().setCenter(this);
        myPane = new FlowPane();
        myVisualBoard = new GridPane();
        myVisualBoardHolder = new Pane();
        myVisualBoardHolder.getChildren().add(myVisualBoard);
        Button b = new Button("Create new Patch");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent arg0) {
                ObjectUpdate temp = new ObjectUpdate();
                temp.setGameObject(new GameObject(null, null));
                temp.setCoordinates(Arrays.asList((int) Math.floor(Math.random() * 
                        Integer.parseInt(myConstants.getString("Board_Test_Size"))),
                        (int) Math.floor(Math.random() * 
                                Integer.parseInt(myConstants.getString("Board_Test_Size")))));
                myLayout.setChangedAndNotify(temp);
            }
        });
        myPane.getChildren().add(b);



        myPane.getChildren().add(myVisualBoardHolder);
        addBoardMouseListener();
        this.getChildren().add(myPane);
    }

    public void update () {
        
        myBoard = myLayout.getBoard();
        List<List<Patch>> patchTemp = myBoard.getPatchList();
        for (List<Patch> list : patchTemp) {
        	for(Patch p : list){
            List<Integer> myDims = p.getPosition();
            myVisualBoard.add(new Rectangle(
                    Integer.parseInt(myConstants.getString("Rectangle_Test_Size")), 
                    Integer.parseInt(myConstants.getString("Rectangle_Test_Size")), 
                    Color.BLUE), myDims.get(0), myDims.get(1));
            if (!(p.getGameObject()==null)) {
                myVisualBoard.add(new Rectangle(
                        Integer.parseInt(myConstants.getString("Rectangle_Test_Size")),
                        Integer.parseInt(myConstants.getString("Rectangle_Test_Size")),
                        Color.RED), myDims.get(0), myDims.get(1));
            }
        }
        }

    }

    private void addBoardMouseListener () {
        myVisualBoard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle (MouseEvent arg0) {
                int x = (int) Math.floor(arg0.getX() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                int y = (int) Math.floor(arg0.getY() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                if (arg0.isControlDown()) {
                    Pane popup = new Pane();
                    popup.setPrefSize(100, 100);
                    popup.setStyle("-fx-background-color: white");
                    popup.setLayoutX(arg0.getX());
                    popup.setLayoutY(arg0.getY());
                    GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(x, y));
                    GridPane grid = new GridPane();
                    int i = 0;
                    if (clicked != null) {
                        if (clicked.getAttributes() != null) {
                            for (AbstractAttribute att : clicked.getAttributes()) {
                                grid.add(new Label(att.getClass().getSimpleName()), 0, i);
                                grid.add(new Label(att.getValue()+""), 1, i);
                                i++;
                            }
                        }
                    }
                    FlowPane flow = new FlowPane(Orientation.VERTICAL);
                    Button close = new Button("X");
                    close.setOnAction(event -> myVisualBoardHolder.getChildren().remove(popup));
                    Button edit = new Button("Edit");
                    //Label info = new Label(labelString.toString());
                    
                    edit.setOnAction(new EventHandler<ActionEvent>(){

                        @Override
                        public void handle (ActionEvent arg0) {
                         //  flow.getChildren().remove(info);
                           flow.getChildren().remove(edit);
                           grid.getChildren().clear();
                         //  GridPane grid = new GridPane();
                         //  flow.getChildren().add(grid);
                           int j = 0;
                           Map<AbstractAttribute, TextField> attMap = new HashMap<AbstractAttribute, TextField>();
                           if (clicked != null) {
                               if (clicked.getAttributes() != null) {
                                   for (AbstractAttribute att : clicked.getAttributes()) {
                                       TextField text = new TextField(att.getValue()+"");
                                       attMap.put(att,text);
                                       grid.add(new Label(att.getClass().getSimpleName()), 0, j);
                                       grid.add(text, 1, j);
                                       j++;
                                   }
                               }
                           }
                           Button save = new Button("Save");
                           flow.getChildren().add(save);
                           save.setOnAction(new EventHandler<ActionEvent>(){

                            @Override
                            public void handle (ActionEvent arg0) {
                                int k=0;
                                flow.getChildren().remove(save);
                                grid.getChildren().clear();
                                StringBuilder sb = new StringBuilder();
                                if (clicked != null) {
                                    if (clicked.getAttributes() != null) {
                                        for (AbstractAttribute att : clicked.getAttributes()) {
                                              att.setValue(Double.parseDouble(attMap.get(att).getText()));
                                                  grid.add(new Label(att.getClass().getSimpleName()), 0, k);
                                                  grid.add(new Label(att.getValue()+""), 1, k);
                                                  k++;
                                        }
                                    }
                                }
                                flow.getChildren().add(new Label(sb.toString())); 
                                flow.getChildren().add(edit);
                                myLayout.setChangedAndNotify(new ObjectUpdate(clicked, Arrays.asList(x,y)));
                            }
                               
                           });
                           
                           
                        }
                        
                    });
                    flow.getChildren().add(close);
                    flow.getChildren().add(grid);
                    flow.getChildren().add(edit);
                    popup.getChildren().add(flow);
                    myVisualBoardHolder.getChildren().add(popup);

                } else if(arg0.isAltDown()){
                    GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(x, y));

                    myLayout.setChangedAndNotify(new RemoveUpdate(clicked, Arrays.asList(x,y)));

                }

                else if(arg0.isShiftDown()){
                    ObjectUpdate temp = new ObjectUpdate();

                    Motion heroMotion = new Motion(true, true, false, false, 2);
                    List<AbstractAttribute> heroAttributes = new ArrayList<AbstractAttribute>();
                    heroAttributes.add(new PassiveAttribute("health", 500.));
                    heroAttributes.add(new ActiveAttribute("attack", 50., "health", true, false));
                    GameObject myHero = new GameObject(heroAttributes, heroMotion);

                    temp.setGameObject(myHero);
                    temp.setCoordinates(Arrays.asList(x,y));
                    myLayout.setChangedAndNotify(temp);
                }
                else{}
            }
        });
        
        final Mover dragMover = new Mover();
        myVisualBoard.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle (MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.               
                dragMover.oldX = (int) Math.floor(mouseEvent.getX() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                dragMover.oldY = (int) Math.floor(mouseEvent.getY() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
            }
        });

        
        myVisualBoard.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent mouseEvent) {
                GameObject clicked = myBoard.getGameObjectAt(Arrays.asList(dragMover.oldX, dragMover.oldY));
                System.out.println(clicked);
                dragMover.newX = (int) Math.floor(mouseEvent.getX() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                dragMover.newY = (int) Math.floor(mouseEvent.getY() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                
                myLayout.setChangedAndNotify(
                        new MoveUpdate(clicked, Arrays.asList(dragMover.oldX, dragMover.oldY), 
                                Arrays.asList(dragMover.newX, dragMover.newY)));
            }
        });
        
        
        
    }


            private void addBoardDragListener (Pane p) {
                final Delta dragDelta = new Delta();
                p.setOnMousePressed(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle (MouseEvent mouseEvent) {
                        // record a delta distance for the drag and drop operation.
                        dragDelta.x = p.getLayoutX() - mouseEvent.getSceneX();
                        dragDelta.y = p.getLayoutY() - mouseEvent.getSceneY();
                        p.setCursor(Cursor.MOVE);
                    }
                });
                p.setOnMouseReleased(event -> p.setCursor(Cursor.HAND));

                p.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent mouseEvent) {
                        p.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                        p.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
                    }
                });
                p.setOnMouseEntered(event -> p.setCursor(Cursor.HAND));
            }



            class Delta {
                double x, y;
            }
            class Mover {
                int oldX, oldY, newX, newY;
            }
        }
