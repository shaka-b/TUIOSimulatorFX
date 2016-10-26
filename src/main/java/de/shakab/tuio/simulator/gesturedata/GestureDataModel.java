package de.shakab.tuio.simulator.gesturedata;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Shaka on 12.02.2015.
 */
public class GestureDataModel {
    private final SimpleStringProperty event = new SimpleStringProperty();
    private final SimpleStringProperty target = new SimpleStringProperty();
    private final SimpleDoubleProperty sceneX = new SimpleDoubleProperty(Double.NaN);
    private final SimpleDoubleProperty sceneY = new SimpleDoubleProperty(Double.NaN);
    private final SimpleDoubleProperty deltaX = new SimpleDoubleProperty(Double.NaN);
    private final SimpleDoubleProperty deltaY = new SimpleDoubleProperty(Double.NaN);
    private final SimpleDoubleProperty scale = new SimpleDoubleProperty(Double.NaN);
    private final SimpleDoubleProperty angle = new SimpleDoubleProperty(Double.NaN);
    private final SimpleBooleanProperty inertia = new SimpleBooleanProperty(false);
    private final SimpleIntegerProperty swipeTouchCount = new SimpleIntegerProperty();

    //TODO builder

    /**
     * @param event
     * @param target
     * @param sceneX
     * @param sceneY
     * @param deltaX
     * @param deltaY
     * @param scale
     * @param angle
     * @param inertia
     * @param swipeTouchCount
     */
    public GestureDataModel(String event, String target, Double sceneX, Double sceneY, Double
            deltaX, Double deltaY, Double scale, Double angle, Boolean inertia, Integer swipeTouchCount) {
        this.event.set(event);
        this.target.set(target);
        this.sceneX.set(round(sceneX, 2));
        this.sceneY.set(round(sceneY, 2));
        this.deltaX.set(round(deltaX, 2));
        this.deltaY.set(round(deltaY, 2));

        this.scale.set(round(scale, 3));
        this.angle.set(round(angle, 3));
        this.inertia.set(inertia);
        this.swipeTouchCount.set(swipeTouchCount);
    }

    private static Double round(Double value, int places){
        //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places?lq=1
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * @param event
     * @param target
     * @param sceneX
     * @param sceneY
     * @param deltaX
     * @param deltaY
     */
    public GestureDataModel(String event, String target, Double sceneX, Double sceneY, Double deltaX, Double
            deltaY) {
        this(event, target, sceneX, sceneY, deltaX, deltaY, Double.NaN, Double.NaN, false,
                0);
    }

    /**
     * @param event
     * @param target
     * @param sceneX
     * @param sceneY
     */

    public GestureDataModel(String event, String target, double sceneX, double sceneY) {
        this(event, target, sceneX, sceneY, Double.NaN, Double.NaN, Double.NaN, Double.NaN, false, 0);
    }

    /**
     * @param event
     * @param target
     * @param sceneX
     * @param sceneY
     * @param deltaX
     * @param deltaY
     * @param swipeTouchCount
     */
    public GestureDataModel(String event, String target, double sceneX, double sceneY, double
            deltaX, double deltaY, int swipeTouchCount) {
        this(event, target, sceneX, sceneY, deltaX, deltaY, Double.NaN, Double.NaN, false, swipeTouchCount);
    }

    public GestureDataModel(String event, String target, double sceneX, double sceneY, double deltaX, double deltaY, boolean inertia) {
        this(event, target, sceneX, sceneY, deltaX, deltaY, Double.NaN, Double.NaN, inertia, 0);
    }


    public Integer getSwipeTouchCount() {
        return swipeTouchCount.get();
    }

    public SimpleIntegerProperty getSwipeTouchCountProperty() {
        return swipeTouchCount;
    }

    public void setEvent(Integer tuchCount) {
        this.swipeTouchCount.set(tuchCount);
    }


    public String getEvent() {
        return event.get();
    }

    public SimpleStringProperty getEventProperty() {
        return event;
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public String getTarget() {
        return target.get();
    }

    public void getTarget(String target) {
        this.target.set(target);
    }

    public SimpleStringProperty getTargetProperty() {
        return target;
    }

    public double getSceneX() {
        return sceneX.get();
    }

    public void setSceneX(Double sceneX) {
        this.sceneX.set(sceneX);
    }

    public SimpleDoubleProperty getSceneXProperty() {
        return sceneX;
    }

    public double getSceneY() {
        return sceneY.get();
    }

    public void setSceneY(Double sceneY) {
        this.sceneY.set(sceneY);
    }

    public SimpleDoubleProperty getSceneYProperty() {
        return sceneY;
    }

    public double getDeltaX() {
        return deltaX.get();
    }

    public void setDeltaX(Double deltaX) {
        this.deltaX.set(deltaX);
    }

    public SimpleDoubleProperty getDeltaXProperty() {
        return deltaX;
    }

    public double getDeltaY() {
        return deltaY.get();
    }

    public void setDeltaY(Double deltaY) {
        this.deltaY.set(deltaY);
    }

    public SimpleDoubleProperty getDeltaYProperty() {
        return deltaY;
    }


    public double getScale() {
        return scale.get();
    }

    public void setScale(Double scale) {
        this.scale.set(scale);
    }

    public double getAngle() {
        return angle.get();
    }

    public void setAngle(Double angle) {
        this.angle.set(angle);
    }

    public SimpleDoubleProperty getAngleProperty() {
        return angle;
    }

    public boolean getInertia() {
        return inertia.get();
    }

    public void setInertia(boolean isInertia) {
        inertia.set(isInertia);
    }

    public SimpleBooleanProperty getInertiaProperty() {
        return inertia;
    }
}
