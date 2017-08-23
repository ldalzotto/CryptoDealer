package com.ldz.entity;

import com.ldz.config.game.entities.EntityId;
import com.ldz.config.game.entities.GameEntitiesConfig;

/**
 * Created by Loic on 19/08/2017.
 */
public class EntityFactory {

    public static EntityWithId getEntityFromId(EntityId entityId){
        return GameEntitiesConfig.getInstance().buildEntityById(entityId);
    }

}
