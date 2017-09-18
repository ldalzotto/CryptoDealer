package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Loic on 19/08/2017.
 */
public class BitmapFontComponent implements Component {

    public BitmapFont bitmapFont;
    public String stringToDisplay;

    public BitmapFontComponent() {
        this.bitmapFont = new BitmapFont();
        this.bitmapFont.getData().markupEnabled = true;
    }
}
