package com.ldz.entity;

import com.ldz.config.game.entities.EntityId;
import com.ldz.config.game.entities.GameEntitiesConfig;
import com.ldz.config.game.entities.InstanceEntityId;

/**
 * Created by Loic on 19/08/2017.
 */
public class EntityFactory {

    @Deprecated
    public static EntityWithId getEntityFromId(EntityId entityId) {
        return GameEntitiesConfig.getInstance().buildEntityById(entityId);
    }

    public static EntityWithId getEntityFromInstanceId(InstanceEntityId instanceEntityId) {
        return GameEntitiesConfig.getInstance().buildEntityByInstanceEntityid(instanceEntityId);
    }

    public static EntityWithId createInstanceFromEntityId(EntityId entityId, Object... arguments) {
        //TODO instancier à la volée.
        return null;
    }

}
