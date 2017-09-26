package com.ldz.entity.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.*;
import com.ldz.engine.MyEngine;
import com.ldz.system.mini.game.SimpleMiniGameUpdate;
import com.ldz.config.game.entities.InstanceEntityId;
import com.ldz.entity.EntityFactory;
import com.ldz.entity.EntityWithId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 05/09/2017.
 */
public class EntityUtil {

    public static EntityWithId addtextureEntityComponents(Vector2 position, Texture texture, int z, EntityWithId entity) {

        EntityUtil.addTransformComponent(position, z, entity);

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.texture = texture;

        BoudingRectangleComponent boudingRectangleComponent = new BoudingRectangleComponent();
        boudingRectangleComponent.rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

        entity.add(textureComponent);
        entity.add(boudingRectangleComponent);

        return entity;
    }

    public static EntityWithId addDisplayerEntityComponents(ArrayList<Entity> entityInBag, EntityWithId entityWithId) {

        addDisplayerCoreComponents(entityWithId);
        addBagOfEntitiesComponent(entityInBag, entityWithId);

        return entityWithId;
    }

    public static EntityWithId addDisplayerEntityComponentsFromEntityId(ArrayList<String> entityInBagIds, EntityWithId entityWithId) {

        addDisplayerCoreComponents(entityWithId);

        addBagOfEntitiesComponent(entityInBagIds, entityWithId);

        return entityWithId;
    }

    public static EntityWithId addBagOfEntitiesComponent(ArrayList<Entity> entityInBag, EntityWithId entityWithId) {
        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();
        bagOfEntitiesComponent.entities.addAll(entityInBag);

        entityWithId.add(bagOfEntitiesComponent);
        return entityWithId;
    }

    public static void addBagOfEntitiesComponent(List<String> entityInBagIds, EntityWithId entityWithId) {
        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();

        for (String entityIdsInBag :
                entityInBagIds) {
            bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromInstanceId(InstanceEntityId.valueOf(entityIdsInBag)));
        }

        entityWithId.add(bagOfEntitiesComponent);
    }

    public static EntityWithId addParentAndChildEntity(EntityWithId entityWithId) {
        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        entityWithId.add(parentAndChildComponent);

        return entityWithId;
    }

    public static EntityWithId addFontDisplayComponentsFromEntityId(Vector2 position, String stringToDisplay, EntityWithId entityWithId) {
        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;
        entityWithId.add(tranformComponent);

        BitmapFontComponent bitmapFontComponent = new BitmapFontComponent();
        bitmapFontComponent.stringToDisplay = stringToDisplay;
        entityWithId.add(bitmapFontComponent);

        addParentAndChildEntity(entityWithId);

        return entityWithId;
    }


    public static EntityWithId addUpgradeCurrencyDisplayerCompoent(CurrencyComponent.CURRENCY_TYPE currency_type, EntityWithId entityWithId) {
        UpgradeCurrencyDisplayerComponent upgradeCurrencyDisplayerComponent = new UpgradeCurrencyDisplayerComponent();
        upgradeCurrencyDisplayerComponent.currencyType = currency_type;

        entityWithId.add(upgradeCurrencyDisplayerComponent);
        return entityWithId;
    }

    private static void addDisplayerCoreComponents(EntityWithId entityWithId) {
        DisplayStateComponent displayStateComponent = new DisplayStateComponent();
        displayStateComponent.state = DisplayStateComponent.STATE.INSTANT;
        entityWithId.add(displayStateComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        entityWithId.add(parentAndChildComponent);
    }

    private static void addTransformComponent(Vector2 position, int z, EntityWithId entityWithId) {

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;
        tranformComponent.z = z;

        entityWithId.add(tranformComponent);
    }

    public static EntityWithId addMiniGameComponent(EntityWithId entityWithId) {
        MiniGameComponent miniGameComponent = new MiniGameComponent();
        miniGameComponent.iMiniGameUpdate = new SimpleMiniGameUpdate(MyEngine.getInstance(null, null));
        miniGameComponent.state = MiniGameComponent.STATE.PENDING;
        entityWithId.add(miniGameComponent);
        return entityWithId;
    }

    public static EntityWithId addPhysicsMovementCompoentn(Vector2 speed, EntityWithId entityWithId) {
        PhysicsMovementComponent physicsMovementComponent = new PhysicsMovementComponent();
        physicsMovementComponent.constantSpeed = speed;
        entityWithId.add(physicsMovementComponent);
        return entityWithId;
    }

    public static EntityWithId addCollisionCalculationComponent(EntityWithId entityWithId) {
        CollisionCalculationComponent collisionCalculationComponent = new CollisionCalculationComponent();
        entityWithId.add(collisionCalculationComponent);
        return entityWithId;
    }

}
