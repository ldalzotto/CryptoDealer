package com.ldz.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 19/08/2017.
 */
public class ParentAndChildUtil {

    public static void removeChildsRecurcsively(Entity entity1, ParentAndChildComponent parentAndChildComponent, BagOfEntitiesComponent bagOfEntitiesComponent, Engine engine) {

        if (parentAndChildComponent == null) {
            parentAndChildComponent = entity1.getComponent(ParentAndChildComponent.class);
        }

        if (bagOfEntitiesComponent == null) {
            bagOfEntitiesComponent = entity1.getComponent(BagOfEntitiesComponent.class);
        }

        if (parentAndChildComponent != null) {
            for (Entity entity :
                    parentAndChildComponent.childs) {
                engine.removeEntity(entity);
                if (entity.getComponent(DisplayStateComponent.class) != null) {
                    entity.getComponent(DisplayStateComponent.class).isDisplayed = false;
                }
                if (entity.getComponent(ParentAndChildComponent.class) != null) {
                    removeChildsRecurcsively(entity, entity.getComponent(ParentAndChildComponent.class), entity.getComponent(BagOfEntitiesComponent.class), engine);
                }
            }
        }

        if (bagOfEntitiesComponent != null) {

            List<Entity> entityToRemove = new ArrayList<>();

            for (Entity entity :
                    bagOfEntitiesComponent.entities) {
                DisplayStateComponent displayStateComponent = entity.getComponent(DisplayStateComponent.class);
                if (displayStateComponent != null && displayStateComponent.state.equals(DisplayStateComponent.STATE.EPHEMER)) {
                    entityToRemove.add(entity);
                }
                removeChildsRecurcsively(entity, entity.getComponent(ParentAndChildComponent.class), entity.getComponent(BagOfEntitiesComponent.class), engine);
            }
            bagOfEntitiesComponent.entities.removeAll(entityToRemove);


        }


        engine.removeEntity(entity1);
    }

    public static void removeEntityComponentRecursively(Entity entity1, ParentAndChildComponent parentAndChildComponent, Class<? extends Component> componentClass) {
        if (parentAndChildComponent == null) {
            parentAndChildComponent = entity1.getComponent(ParentAndChildComponent.class);
        }

        for (Entity entity :
                parentAndChildComponent.childs) {
            entity1.remove(componentClass);
            if (entity.getComponent(DisplayStateComponent.class) != null) {
                entity.getComponent(DisplayStateComponent.class).isDisplayed = false;
            }
            if (entity.getComponent(ParentAndChildComponent.class) != null) {
                removeEntityComponentRecursively(entity, entity.getComponent(ParentAndChildComponent.class), componentClass);
            }
        }

        entity1.remove(componentClass);
    }


}
