package com.ldz.engine;

import com.badlogic.ashley.core.Engine;

/**
 * Created by Loic on 20/08/2017.
 */
public class MyEngine extends Engine {

    private static MyEngine instance = null;

    public static MyEngine getInstance() {
        if (instance == null) {
            instance = new MyEngine();
        }
        return instance;
    }

}
