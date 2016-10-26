package de.shakab.tuio.simulator.gesturedata;

import de.shakab.tuio.simulator.FXMLController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Shaka on 11.02.2015.
 */
public class GestureDataController extends FXMLController {
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();
    private final VBox gestureDataPane;

    @FXML
    private CheckBox touchRecognizerEnabled;
    @FXML
    private CheckBox zoomRecognizerEnabled;
    @FXML
    private CheckBox rotateRecognizerEnabled;
    @FXML
    private CheckBox mouseRecognizerEnabled;
    @FXML
    private CheckBox scrollRecognizerEnabled;
    @FXML
    private CheckBox swipeRecognizerEnabled;
    @FXML
    private CheckBox gestureContinuationState;
    @FXML
    private TableView<GestureDataModel> gestureDataTable;
    @FXML
    private TableColumn<GestureDataModel, String> eventColumn;
    @FXML
    private TableColumn<GestureDataModel, String> targetColumn;
    @FXML
    private TableColumn<GestureDataModel, Double> xColumn;
    @FXML
    private TableColumn<GestureDataModel, Double> yColumn;
    @FXML
    private TableColumn<GestureDataModel, Double> dxColumn;
    @FXML
    private TableColumn<GestureDataModel, Double> dyColumn;
    @FXML
    private TableColumn<GestureDataModel, Double> scaleColumn;
    @FXML
    private TableColumn<GestureDataModel, Double> angleColumn;
    @FXML
    private TableColumn<GestureDataModel, Boolean> inertiaColumn;
    @FXML
    private TableColumn<GestureDataModel, Integer> swipeTouchesColumn;
    @FXML
    private Button clearGestureDataButton;
    private final ObservableList<GestureDataModel> data = FXCollections.observableArrayList();


    public GestureDataController() {
        super(GestureDataController.class.getResource("GestureData.fxml"));
        gestureDataPane = loadFXML();
    }

    public VBox getGestureDataPane() {
        return gestureDataPane;
    }

    public boolean isTouchRecognizerEnabled() {
        return touchRecognizerEnabled.isSelected();
    }

    public boolean isZoomRecognizerEnabled() {
        return zoomRecognizerEnabled.isSelected();
    }


    public boolean isRotateRecognizerEnabled() {
        return rotateRecognizerEnabled.isSelected();
    }

    public boolean isMouseRecognizerEnabled() {
        return mouseRecognizerEnabled.isSelected();
    }

    public boolean isScrollRecognizerEnabled() {
        return scrollRecognizerEnabled.isSelected();
    }

    public boolean isSwipeRecognizerEnabled() {
        return swipeRecognizerEnabled.isSelected();
    }

    public boolean isGestureContinuationStateEnabled() {
        return gestureContinuationState.isSelected();
    }


    public void initialize() {
        eventColumn.setCellValueFactory(cellData -> cellData.getValue().getEventProperty());
        targetColumn.setCellValueFactory(cellData -> cellData.getValue().getTargetProperty());
        xColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Double>("sceneX"));
        yColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Double>("sceneY"));
        dxColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Double>("deltaX"));
        dyColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Double>("deltaY"));
        scaleColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Double>
                ("scale"));
        angleColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Double>
                ("angle"));
        inertiaColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Boolean>
                ("inertia"));
        swipeTouchesColumn.setCellValueFactory(new PropertyValueFactory<GestureDataModel, Integer>
                ("swipeTouchCount"));
        gestureDataTable.setItems(data);


        gestureDataTable.getItems().addListener((ListChangeListener<GestureDataModel>) listener -> {
            while (listener.next()) {
                if (listener.wasAdded()) {
                    int size = gestureDataTable.getItems().size();
                    //performance hack: limit rows to make scrollTo obsolete
                    if (size > 16) {
                        int toDelete = size - 16;
                        data.remove(0, toDelete);
                    }
                    //16
                    int index = size - 1;
                    // gestureDataTable.scrollTo(index);
                    //gestureDataTable.requestFocus();
                    gestureDataTable.getSelectionModel().select(index);
                    return;
                }
            }

        });

    }

    public void addTouchEventEntry(TouchEvent event) {
        GestureDataModel eventData = new GestureDataModel(event.getEventType().getName(), event.getTarget()
                .getClass().getSimpleName(), event.getTouchPoint().getSceneX(), event.getTouchPoint().getSceneY());
        data.add(eventData);

    }

    private void runOnPlatformThreadLater(Runnable runnable, long sleepTime) {
        runOnThreadLater(() -> {
            runOnPlatformThread(runnable);
        }, sleepTime);
    }


    private void runOnThread(Runnable runnable) {
        //Executors.newSingleThreadExecutor().execute(runnable);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void runOnThreadLater(Runnable runnable, long sleepTime) {
        //Executors.newSingleThreadExecutor().execute(runnable);
        scheduler.schedule(runnable, sleepTime, TimeUnit.MILLISECONDS);
    }

    public static void runOnPlatformThread(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }


    public void addScrollEventEntry(ScrollEvent event) {
        GestureDataModel eventData = new GestureDataModel(event.getEventType().getName(), event.getTarget()
                .getClass().getSimpleName(),
                event.getSceneX(),
                event.getSceneY(),
                event.getDeltaX(), event.getDeltaY(), event.isInertia());
        data.add(eventData);
    }

    public void addRotateEventEntry(RotateEvent event) {
        GestureDataModel eventData = new GestureDataModel(event.getEventType().getName(), event.getTarget()
                .getClass().getSimpleName(), event.getSceneX(), event.getSceneY(), 0.0, 0.0, 0.0,  event
                .getAngle(),event.isInertia(),  0);
        data.add(eventData);
    }

    public void addZoomEventEntry(ZoomEvent event) {
        GestureDataModel eventData = new GestureDataModel(event.getEventType().getName(), event.getTarget()
                .getClass().getSimpleName(), event.getSceneX(), event.getSceneY(),
                0.0, 0.0, event.getZoomFactor(), 0.0, event.isInertia(), 0);
        data.add(eventData);
    }

    public void addSwipeEventEntry(SwipeEvent event) {
        GestureDataModel eventData = new GestureDataModel(event.getEventType().getName(), event.getTarget()
                .getClass().getSimpleName(),
                event.getSceneX(), event.getSceneY(), 0.0, 0.0,event.getTouchCount());
        data.add(eventData);
    }


    public void addMouseEventEntry(MouseEvent event) {
        if (event.isSynthesized()) {
            GestureDataModel eventData = new GestureDataModel(event.getEventType().getName(), event.getTarget()
                    .getClass().getSimpleName(),
                    event.getSceneX(),
                    event.getSceneY());
            data.add(eventData);
        }
    }

    @FXML
    void handleClearGestureData(ActionEvent event) {
        if (event.getSource().equals(clearGestureDataButton)) {
            data.clear();
        }
    }


}
