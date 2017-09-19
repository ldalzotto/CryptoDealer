package com.ldz.config.game.entities;

import com.ldz.config.game.entities.domain.GameEntities;
import com.ldz.entity.EntityWithId;

/**
 * Created by Loic on 20/08/2017.
 */
public class CoreGameEntitiesConfig extends GameEntitiesConfig {

    private static final String TAG = CoreGameEntitiesConfig.class.getSimpleName();

    private static CoreGameEntitiesConfig instance = null;

    public CoreGameEntitiesConfig() {
        super();
    }

    public static CoreGameEntitiesConfig getInstance() {
        if (instance == null) {
            instance = new CoreGameEntitiesConfig();
        }
        return instance;
    }

    @Override
    public EntityWithId buildEntityFromEntityIdAndArguments(String entityId, Object... args) {
        EntityWithId entityWithId = super.buildEntityFromEntityIdAndArguments(entityId, args);
        entityWithId.setId(EntityId.valueOf(entityId));
        return entityWithId;
    }

    @Override
    public GameEntities getGameEntities() {
        return super.getGameEntities();
    }

    @Override
    public EntityWithId buildEntityByInstanceEntityid(String instanceEntityId) {
        String entityId = super.getEntityIdFromInstanceId(instanceEntityId);
        EntityWithId entityWithId = super.buildEntityByInstanceEntityid(instanceEntityId);
        if (entityId != null) {
            entityWithId.setId(EntityId.valueOf(entityId));
        }
        entityWithId.setIstanceId(InstanceEntityId.valueOf(instanceEntityId));
        return entityWithId;
    }

    @Override
    public EntityWithId buildEntityById(String id) {
        EntityWithId entityWithId = super.buildEntityById(id);
        entityWithId.setId(EntityId.valueOf(id));
        return entityWithId;
    }
}
