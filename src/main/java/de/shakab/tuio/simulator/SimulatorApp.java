/*
 * A TUIO 1.1. simulator for JavaFX
 * Copyright (C) 2017 David Bimamisa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
