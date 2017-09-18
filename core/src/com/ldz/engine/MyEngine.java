package com.ldz.engine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Loic on 20/08/2017.
 */
public class MyEngine extends CoreGine {

    private static final float DEBUG_INTERVAL = 1.0f;
    private static MyEngine instance = null;

    public MyEngine(OrthographicCamera orthographicCamera, SpriteBatch spriteBatch) {
        super(orthographicCamera, spriteBatch);
    }

    public static MyEngine getInstance(OrthographicCamera orthographicCamera, SpriteBatch spriteBatch) {
        if (instance == null) {
            instance = new MyEngine(orthographicCamera, spriteBatch);
        }
        return instance;
    }

}
