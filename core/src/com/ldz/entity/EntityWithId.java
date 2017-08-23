package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.ldz.config.game.entities.EntityId;

/**
 * Created by Loic on 20/08/2017.
 */
public class EntityWithId extends Entity {

    private EntityId id;

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }
}
