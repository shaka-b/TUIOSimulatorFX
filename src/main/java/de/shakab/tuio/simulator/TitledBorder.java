package de.shakab.tuio.simulator;

/**
 * Created by Shaka on 07.05.14.
 */

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

@DefaultProperty("children")
public class TitledBorder extends StackPane {
    private final Label titleLabel = new Label();

    public TitledBorder() {
        titleLabel.setText("Default Title");
        titleLabel.getStyleClass().add("bordered-titled-title");
        StackPane.setAlignment(titleLabel, Pos.TOP_LEFT);

        getStyleClass().add("bordered-titled-border");
        getStylesheets().add(TitledBorder.class.getResource("titledBorder.css").toExternalForm());

        //contentContainer.getStyleClass().add("bordered-titled-content");

      //  contentPane.getChildren().add(contentContainer);
        super.getChildren().addAll(titleLabel);

    }


    @Override public ObservableList<Node> getChildren() {
        return super.getChildren();
    }



    /*
    don't work!!
    @Override public ObservableList<Node> getChildren() {

        // Adds children directly into contentContainer instead of TitledBorder (e.g., via scene
        // builder). By doing so, we can comfortably style contentContainer once using class
        // .bordered-titled-content
        return contentContainer.getChildren();
    }  */


    public void setTitle(String title) {
        titleLabel.setText(" " + title + " ");
    }


    public String getTitle() {
        return titleLabel.getText();
    }


}