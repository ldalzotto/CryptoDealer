package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

/**
 * Created by Loic on 19/08/2017.
 */
public class TimeAccumlatorComponent implements Component {

    public float accumulatedTime = 0f;
    public float timeLimit = 0.35f;

    public Entity targetEntity;

}
