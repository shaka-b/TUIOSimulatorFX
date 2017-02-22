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

import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

@DefaultProperty("children")
public class TitledBorderContent extends StackPane {

    private static final String DEFAULT_STYLE_CLASS = "bordered-titled-content";

    public TitledBorderContent() {
        super();
        getStyleClass().addAll(DEFAULT_STYLE_CLASS);
    }

    @Override
    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }


}
