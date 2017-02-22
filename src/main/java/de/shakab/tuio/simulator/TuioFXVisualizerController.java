/*
 * Copyright (c) 2017 by David Bimamisa.  All rights reserved.
 *
 *  Licensed under the BSD 3-Clause license.
 *  See the file LICENSE.txt in in the project root for more information.
 *
 */

package de.shakab.tuio.simulator;

import de.shakab.tuio.simulator.cursor.Cursor;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author David Bimamisa
 */
public class TuioFXVisualizerController extends FXMLController {

    private final HashMap<Integer, Cursor> activeTrackers;
    private final TuioSender server;
    private AnchorPane root;

    @FXML
    private Pane touchPointView;

    private final List<TuioPoint> tuioTouchPoints = new ArrayList<>();
    private int sessionId = 0;

    public TuioFXVisualizerController(TuioSender server) {
        super(TuioFXVisualizerController.class.getResource("TuioFXVisualizer.fxml"));
        this.server = server;

        activeTrackers = new HashMap<>();
        root = loadFXML();

        root.addEventFilter(MouseEvent.ANY, e -> handleMouseEvent(e));
    }


    private void handleMouseEvent(MouseEvent mouseEvent) {
        long timeStamp = System.currentTimeMillis();

        boolean relevantAction = false;
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            addCursor(mouseEvent, timeStamp);
            relevantAction = true;
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
            updateCursor(mouseEvent, timeStamp);
            relevantAction = true;
        } else if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            removeCursor(mouseEvent);
            relevantAction = true;

        }

        if (relevantAction) {
            server.sendCursorMessage(new ArrayList<>(tuioTouchPoints));
        }
    }


    private void addCursor(MouseEvent event, long timeStamp) {
        int id = (event.isControlDown()) ? 1 : 0;
        float x = (float) (event.getSceneX() / root.getScene().getWidth());
        float y = (float) (event.getSceneY() / root.getScene().getHeight());

        // add new Point
        tuioTouchPoints.add(new TuioPoint(sessionId, id, x, y, timeStamp));
        sessionId++;

        Cursor cursor = activeTrackers.get(id);
        if (cursor == null) {
            cursor = new Cursor(id, event.getSceneX(), event.getSceneY());
            activeTrackers.put(id, cursor);
            addNodeToRoot(cursor);
        } else {
            cursor.setXY(event.getSceneX(), event.getSceneY());
            cursor.setVisible(true);
        }

    }


    private void updateCursor(MouseEvent event, long timeStamp) {
        int id = (event.isControlDown()) ? 1 : 0;
        float x = (float) (event.getSceneX() / root.getScene().getWidth());
        float y = (float) (event.getSceneY() / root.getScene().getHeight());

        for (TuioPoint touchPoint : tuioTouchPoints) {
            if (touchPoint.getTouchId() == id) {
                touchPoint.update(x, y, timeStamp);
                break;
            }
        }

        Cursor cursor = activeTrackers.get(id);
        if (cursor != null) {
            cursor.setXY(event.getSceneX(), event.getSceneY());
        }


    }


    private void removeCursor(MouseEvent event) {
        sessionId--;

        int id = (event.isControlDown()) ? 1 : 0;

        Iterator<TuioPoint> iter = tuioTouchPoints.iterator();

        while (iter.hasNext()) {
            TuioPoint touchPoint = iter.next();
            if (touchPoint.getTouchId() == id) {
                iter.remove();
            }
        }

        Cursor cursor = activeTrackers.get(id);
        if (cursor != null) {
            cursor.setVisible(false);
        }


    }

    private void addNodeToRoot(Node node) {
        if (getRoot() != null) {
            getRoot().getChildren().add(node);
        }
    }


    public AnchorPane getRoot() {
        return root;
    }
}
