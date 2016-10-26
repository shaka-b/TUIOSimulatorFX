package de.shakab.tuio.simulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  @author David Bimamisa
 */
public class SimulatorApp extends Application {

    private static final String APPLICATION_TITLE = "TUIO Simulator";
    private static final String VISUALIZER_STYLESHEET = "visualizer.css";

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        TuioSender tuioServer = new TuioSender("127.0.0.1", "FXClientOne");
        TuioFXVisualizerController mainController = new TuioFXVisualizerController(tuioServer);

        Scene scene = new Scene(mainController.getRoot(), 800, 600);

        scene.getStylesheets().add(getClass().getResource(VISUALIZER_STYLESHEET).toExternalForm());

        stage.setScene(scene);

        stage.setTitle(APPLICATION_TITLE);

        stage.setFullScreen(false);
        stage.show();
        stage.setOnCloseRequest((e) -> System.exit(0));
        //ScenicView.show(scene);

    }


}
