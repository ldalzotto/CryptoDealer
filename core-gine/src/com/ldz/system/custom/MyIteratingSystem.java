package com.ldz.system.custom;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Loic on 27/08/2017.
 */
public abstract class MyIteratingSystem extends IteratingSystem implements IRetrieveAllEntitiesFromSystem {

    public MyIteratingSystem(Family family) {
        super(family);
    }

    public MyIteratingSystem(Family family, int priority) {
        super(family, priority);
    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.getEntities());
        return entities;
    }
}
