package com.ldz.util;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;

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

}
