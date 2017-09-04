package com.ldz.util;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.config.game.entities.EntityId;
import com.ldz.system.ParentAndChildSystem;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Loic on 19/08/2017.
 */
public class ParentAndChildUtil {

    public static void removeChildsRecurcsively(Entity entity1, ParentAndChildComponent parentAndChildComponent, Engine engine) {

        if (parentAndChildComponent == null) {
            parentAndChildComponent = entity1.getComponent(ParentAndChildComponent.class);
        }

        engine.removeEntity(entity1);

        for (Entity entity :
                parentAndChildComponent.childs) {
            engine.removeEntity(entity);
            if (entity.getComponent(DisplayStateComponent.class) != null) {
                entity.getComponent(DisplayStateComponent.class).isDisplayed = false;
            }
            if (entity.getComponent(ParentAndChildComponent.class) != null) {
                removeChildsRecurcsively(entity, entity.getComponent(ParentAndChildComponent.class), engine);
            }
        }
    }

    public static void forEachChildsRecursively(Entity entity, Function<Entity, Void> entityVoidFunction) {

        //retireve component
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);

        if (parentAndChildComponent != null) {
            for (Entity entity1 :
                    parentAndChildComponent.childs) {
                entityVoidFunction.apply(entity1);
                forEachChildsRecursively(entity1, entityVoidFunction);
            }
        }

    }

    /**
     * <p>
     * Link each {@link ParentAndChildComponent} between {@code parentEntityId} parent and {@code childEntitiesId} childs.
     * The linking is ensured by setting {@link ParentAndChildComponent#childs} to {@code parentEntityId} entity.
     * This algorithm is based on the static container of entites that are allowed to be linked contained in {@link ParentAndChildSystem#entityById}.
     * </p>
     *
     * @param parentEntityId  the parent entity that are going to be linked
     * @param childEntitiesId all childs entities that are going to be linked to {@code parentEntityId}
     */
    public static void linkParentAndChildOfEntity(EntityId parentEntityId, List<EntityId> childEntitiesId) {
        Map<EntityId, List<Entity>> entityById = ParentAndChildSystem.getInstance().getEntityById();
        if (entityById.containsKey(parentEntityId)) {
            List<Entity> parentEntitys = entityById.get(parentEntityId);
            for (Entity parentEntity :
                    parentEntitys) {

                //get component
                ParentAndChildComponent parentAndChildComponent = parentEntity.getComponent(ParentAndChildComponent.class);
                if (parentAndChildComponent != null) {

                    //setting childs
                    for (EntityId childClassNames :
                            childEntitiesId) {
                        if (entityById.containsKey(childClassNames)) {
                            parentAndChildComponent.childs.addAll(entityById.get(childClassNames));
                            //setting parend
                            for (Entity childEntity :
                                    entityById.get(childClassNames)) {
                                ParentAndChildComponent childParentAndChildComponent = childEntity.getComponent(ParentAndChildComponent.class);
                                if (childParentAndChildComponent != null) {
                                    childParentAndChildComponent.parent = parentEntity;
                                }
                            }
                        }
                    }

                }
            }

        }
    }

}
