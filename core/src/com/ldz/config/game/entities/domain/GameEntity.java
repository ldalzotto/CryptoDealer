package com.ldz.config.game.entities.domain;

import com.ldz.config.game.entities.EntityId;

/**
 * Created by Loic on 20/08/2017.
 */
public class GameEntity {

    private EntityId id;
    private Instance instance;

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
