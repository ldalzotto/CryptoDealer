package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 26/09/2017.
 */
public class CollisionCalculationComponent implements Component {

    public STATE state = STATE.INACTIVE;
    public List<Entity> entityToCompare = new ArrayList<>();

    //setted after collision
    public List<Entity> entityinCollision = new ArrayList<>();
    public boolean isInCollision = false;

    public enum STATE {
        ACTIVE, INACTIVE;
    }

}
