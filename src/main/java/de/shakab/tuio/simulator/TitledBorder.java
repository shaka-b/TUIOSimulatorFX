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