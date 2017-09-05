package com.ldz.entity.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.*;
import com.ldz.config.game.entities.EntityId;
import com.ldz.entity.EntityFactory;
import com.ldz.entity.EntityWithId;

import java.util.ArrayList;

/**
 * Created by Loic on 05/09/2017.
 */
public class EntityUtil {

    public static EntityWithId addtextureEntityComponents(Vector2 position, Texture texture, int z, EntityWithId entity) {

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;
        tranformComponent.z = z;

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.texture = texture;

        BoudingRectangleComponent boudingRectangleComponent = new BoudingRectangleComponent();
        boudingRectangleComponent.rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

        entity.add(tranformComponent);
        entity.add(textureComponent);
        entity.add(boudingRectangleComponent);

        return entity;
    }

    public static EntityWithId addDisplayerEntityComponents(ArrayList<Entity> entityInBag, EntityWithId entityWithId) {

        addDisplayerCoreComponents(entityWithId);

        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();
        bagOfEntitiesComponent.entities.addAll(entityInBag);

        entityWithId.add(bagOfEntitiesComponent);

        return entityWithId;
    }

    public static EntityWithId addDisplayerEntityComponentsFromEntityId(ArrayList<String> entityInBagIds, EntityWithId entityWithId) {

        addDisplayerCoreComponents(entityWithId);

        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();

        for (String entityIdsInBag :
                entityInBagIds) {
            bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId(EntityId.valueOf(entityIdsInBag)));
        }

        entityWithId.add(bagOfEntitiesComponent);

        return entityWithId;
    }

    private static void addDisplayerCoreComponents(EntityWithId entityWithId) {
        DisplayStateComponent displayStateComponent = new DisplayStateComponent();
        displayStateComponent.state = DisplayStateComponent.STATE.INSTANT;
        entityWithId.add(displayStateComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        entityWithId.add(parentAndChildComponent);
    }


}
