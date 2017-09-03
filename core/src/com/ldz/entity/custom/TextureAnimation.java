package com.ldz.entity.custom;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.ArrayList;

/**
 * Created by Loic on 03/09/2017.
 */
public class TextureAnimation extends Animation<Texture> {

    public TextureAnimation(float frameDuration, ArrayList<Texture> keyFrames) {
        super(frameDuration, keyFrames.toArray(new Texture[]{}));
    }
}
