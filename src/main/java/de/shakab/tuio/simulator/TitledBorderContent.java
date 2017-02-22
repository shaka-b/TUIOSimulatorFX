/*
 * Copyright (c) 2017 by David Bimamisa.  All rights reserved.
 *
 *  Licensed under the BSD 3-Clause license.
 *  See the file LICENSE.txt in in the project root for more information.
 *
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
