package layout;
import gameObjectModel.GameObject;
import gameObjectModel.Motion;
import inputs.InteractUpdate;
import inputs.MoveUpdate;
import inputs.ObjectUpdate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import attributes.AbstractAttribute;
import attributes.ActiveAttribute;
import attributes.PassiveAttribute;
import boardObjectModels.Board;
import boardObjectModels.Patch;

public class PlayingCenter extends AbstractCenter {

    private GridPane myVisualBoard;
    private Pane myVisualBoardHolder;
    private Pane myPane;
    private Board myBoard;
    private AbstractLayout myLayout;
    private ResourceBundle myConstants;
    public static final String CONSTANTS_PATH = "resources/constants";
    private VisualBoardManager myBoardManager;
    private int myClickCounter;
    private Patch myClickedPatch;

    public PlayingCenter(AbstractLayout layout) {
        myClickCounter = 0;
        myClickedPatch = new Patch();
        myBoardManager = new VisualBoardManager();
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
                temp.setGameObject(new GameObject(null, new Motion(false, false, false, false, 2)));
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
        	for (Patch p : list){
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
                System.out.println(myClickCounter);
                int x = (int) Math.floor(arg0.getX() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                int y = (int) Math.floor(arg0.getY() / 
                        Integer.parseInt(myConstants.getString("Board_Test_Size")));
                 if(arg0.isShiftDown()){
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
                 else if (arg0.isControlDown()) {
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
                                          
                     flow.getChildren().add(close);
                     flow.getChildren().add(grid);
                     popup.getChildren().add(flow);
                     myVisualBoardHolder.getChildren().add(popup);
                 }
                 
                 else{
                if(myClickCounter==0){
                update();
                myClickedPatch = myLayout.getBoard().getPatchAt(Arrays.asList(x, y));
               
                    if(arg0.isAltDown()){ 
                        List<Patch> availPatches = myBoardManager.getAttackablePatches(Arrays.asList(x, y), myLayout.getBoard());
                for (Patch p : availPatches) {
                    List<Integer> myDims = p.getPosition();
                    myVisualBoard.add(new Rectangle(
                            Integer.parseInt(myConstants.getString("Rectangle_Test_Size")), 
                            Integer.parseInt(myConstants.getString("Rectangle_Test_Size")), 
                            Color.BLACK), myDims.get(0), myDims.get(1));
                    if(myClickedPatch.getGameObject()!=null){
                myClickCounter=2;
                }
                    }
                    }
                    else{
                        List<Patch> availPatches = myBoardManager.getAvailablePatches(Arrays.asList(x, y), myLayout.getBoard());
                        for (Patch p : availPatches) {
                            List<Integer> myDims = p.getPosition();
                    myVisualBoard.add(new Rectangle(
                            Integer.parseInt(myConstants.getString("Rectangle_Test_Size")), 
                            Integer.parseInt(myConstants.getString("Rectangle_Test_Size")), 
                            Color.GREEN), myDims.get(0), myDims.get(1));
                    if(myClickedPatch.getGameObject()!=null){
                        myClickCounter=1;
                        }
                    }
                    }
                
                
                }
                else{
                    if(myClickCounter==1){
                    List<Patch> availPatches = myBoardManager.getAvailablePatches(myClickedPatch.getPosition(), myLayout.getBoard());
                    Patch clickedPatch = myLayout.getBoard().getPatchAt(Arrays.asList(x, y));
                    if(availPatches.contains(clickedPatch)){
                        
                        myLayout.setChangedAndNotify(new MoveUpdate(myClickedPatch.getGameObject(), 
                            myClickedPatch.getPosition(), Arrays.asList(x,y)));
                        myClickCounter=0;
                        }
                    }
                        else{
                            List<Patch> attackPatches = myBoardManager.getAttackablePatches(myClickedPatch.getPosition(), myLayout.getBoard());
                            Patch clickedPatch = myLayout.getBoard().getPatchAt(Arrays.asList(x, y));
                            if(attackPatches.contains(clickedPatch)){
                                
                                myLayout.setChangedAndNotify(new InteractUpdate(myBoard.getPatchAt(Arrays.asList(x,y)), 
                                        myBoard.getPatchAt(Arrays.asList(x,y)).getGameObject().getAttributes().get(1), 
                                        myClickedPatch));
                                    
                                myClickCounter=0;
                                }
                        }
                            //myClickCounter=0;
                    
                }
                 }
            }
            });
            
                        }
}
