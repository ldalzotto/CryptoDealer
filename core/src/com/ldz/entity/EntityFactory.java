package com.ldz.entity;

import com.ldz.config.game.entities.CoreGameEntitiesConfig;
import com.ldz.config.game.entities.EntityId;
import com.ldz.config.game.entities.InstanceEntityId;

/**
 * Created by Loic on 19/08/2017.
 */
public class EntityFactory {

    @Deprecated
    public static EntityWithId getEntityFromId(EntityId entityId) {
        return CoreGameEntitiesConfig.getInstance().buildEntityById(entityId.name());
    }

    public static EntityWithId getEntityFromInstanceId(InstanceEntityId instanceEntityId) {
        return CoreGameEntitiesConfig.getInstance().buildEntityByInstanceEntityid(instanceEntityId.name());
    }

    public static EntityWithId createInstanceFromEntityId(EntityId entityId, Object... arguments) {
        return CoreGameEntitiesConfig.getInstance().buildEntityFromEntityIdAndArguments(entityId.name(), arguments);
    }

}
