/*
 * Copyright (c) 2017 by David Bimamisa.  All rights reserved.
 *
 *  Licensed under the BSD 3-Clause license.
 *  See the file LICENSE.txt in in the project root for more information.
 *
 */

package de.shakab.tuio.simulator;

import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;

/**
 * @author David Bimamisa
 */
public abstract class FXMLController {

    private final URL fxmlURL;
    protected FXMLController(final URL fxmlURL){
        this.fxmlURL = fxmlURL;
    }

    protected <T> T loadFXML(){
        if(fxmlURL == null){
            throw new RuntimeException("URL to FXML should no be null");
        }

        final FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setLocation(fxmlURL);
        try {
           return (T) loader.load();
        } catch (RuntimeException | IOException x) {
            throw new RuntimeException("Failed to load " + fxmlURL.getFile(), x);
        }
    }
}
