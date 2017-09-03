package com.ldz.util;

import com.badlogic.ashley.core.Entity;
import com.ldz.component.TranformComponent;

import java.util.Comparator;

/**
 * Created by Loic on 03/09/2017.
 */
public class zComparator implements Comparator<Entity> {

    @Override
    public int compare(Entity o1, Entity o2) {
        TranformComponent tranformComponent1 = o1.getComponent(TranformComponent.class);
        TranformComponent tranformComponent2 = o2.getComponent(TranformComponent.class);
        return (int) Math.signum(tranformComponent1.z - tranformComponent2.z);
    }

}
