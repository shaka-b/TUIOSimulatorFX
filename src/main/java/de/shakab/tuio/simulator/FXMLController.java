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
