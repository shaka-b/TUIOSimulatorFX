package de.shakab.tuio.simulator.cursor;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaka on 14.02.2015.
 */
public final class CursorColor {

    private final List<Color> colors;
    private static final int NUM_COLORS = 10;
    private static final CursorColor INSTANCE = new CursorColor();
    private CursorColor (){
        colors = new ArrayList<>(NUM_COLORS);
        colors.add(0, Color.web("#349ED1")); //349ED1
        colors.add(1, Color.web("#D07822"));  //D07822
        colors.add(2, Color.web("#E04644"));
        colors.add(3, Color.web("#91A601"));
        colors.add(4, Color.web("#E8548E"));
        colors.add(5, Color.web("#FDB008"));
        colors.add(6, Color.web("#23A25D"));
        colors.add(7, Color.web("#6F8DA9"));
        colors.add(8, Color.web("#A05BBC"));
        colors.add(9, Color.web("#C39D6D"));
    }

    public static CursorColor getInstance(){
        return INSTANCE;
    }

    public Color getColor(int id){
        return colors.get(id);
    }
}
