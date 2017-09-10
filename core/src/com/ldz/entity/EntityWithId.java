package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.ldz.config.game.entities.EntityId;
import com.ldz.config.game.entities.InstanceEntityId;

/**
 * Created by Loic on 20/08/2017.
 */
public class EntityWithId extends Entity {

    private EntityId id;
    private InstanceEntityId istanceId;

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public InstanceEntityId getIstanceId() {
        return istanceId;
    }

    public void setIstanceId(InstanceEntityId istanceId) {
        this.istanceId = istanceId;
    }
}
