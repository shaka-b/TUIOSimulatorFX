package de.shakab.tuio.simulator;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Shaka on 10.02.2015.
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
