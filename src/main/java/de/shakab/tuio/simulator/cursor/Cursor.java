package de.shakab.tuio.simulator.cursor;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by Shaka on 11.02.2015.
 */
public class Cursor extends Group {

    public static final int TEXT_X_RELATIVE_TO_TOUCH_POINT = 69;
    public static final int TEXT_Y_RELATIVE_TO_TOUCH_POINT = 15;
    private final int id;
    private Label idLabel;
    private Label xyLabel;
    private final Group cursorGroup = new Group();
    private final Circle cursorInner = new Circle(17);
    private final Circle cursorOuter = new Circle(25);
    private final Group textGroup = new Group();


    public Cursor(int id, double sceneX, double sceneY) {
        super();
        this.id = id;
        setMouseTransparent(true);
        createCircle(sceneX, sceneY);
        createTextGroup(sceneX, sceneY);
    }

    private void createCircle(double centerX, double centerY) {
        cursorInner.setCenterX(centerX);
        cursorInner.setCenterY(centerY);
        cursorInner.setFill(CursorColor.getInstance().getColor(id));
        cursorInner.setMouseTransparent(true);

        cursorOuter.setCenterX(centerX);
        cursorOuter.setCenterY(centerY);
        cursorOuter.setFill(Color.TRANSPARENT);
        cursorOuter.setStroke(CursorColor.getInstance().getColor(id));
        cursorOuter.setStrokeWidth(2);
        cursorOuter.setMouseTransparent(true);


        cursorGroup.getChildren().addAll(cursorOuter, cursorInner);
        getChildren().add(cursorGroup);

    }

    private void createTextGroup(double sceneX, double sceneY) {
        VBox textBox = new VBox();
        textBox.setSpacing(5);

        idLabel = new Label("ID: " + id);
        idLabel.setTextFill(CursorColor.getInstance().getColor(id));

        xyLabel = new Label("X: " + Math.round(sceneX) + "  |  Y: " + Math.round(sceneY));
        xyLabel.setTextFill(CursorColor.getInstance().getColor(id));

        textBox.getChildren().addAll(idLabel, xyLabel);

        textGroup.getChildren().add(textBox);
        textGroup.setLayoutY(sceneY - TEXT_X_RELATIVE_TO_TOUCH_POINT);
        textGroup.setLayoutX(sceneX + TEXT_Y_RELATIVE_TO_TOUCH_POINT);
        textGroup.setStyle("-fx-text-fill:#ededed; -fx-font-size:0.75em");

        getChildren().add(textGroup);
    }

    public void setXY(double sceneX, double sceneY) {
        cursorOuter.setCenterX(sceneX);
        cursorOuter.setCenterY(sceneY);
        cursorInner.setCenterX(sceneX);
        cursorInner.setCenterY(sceneY);

        textGroup.setLayoutY(sceneY - TEXT_X_RELATIVE_TO_TOUCH_POINT);
        textGroup.setLayoutX(sceneX + TEXT_Y_RELATIVE_TO_TOUCH_POINT);

        xyLabel.setText("X: " + Math.round(sceneX) + "  |  Y: " + Math.round(sceneY));

    }


}
