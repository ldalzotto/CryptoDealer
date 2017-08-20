package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.ldz.config.game.entities.GameEntitiesConfig;

/**
 * Created by Loic on 19/08/2017.
 */
public class EntityFactory {

    public static Entity getEntityFromId(String entityId){
        return GameEntitiesConfig.getInstance().buildEntityById(entityId);
    }

}
