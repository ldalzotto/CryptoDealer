package com.ldz.util;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.ldz.component.ParentAndChildComponent;

/**
 * Created by Loic on 19/08/2017.
 */
public class ParentAndChildUtil {

    public static void removeChildsRecurcsively(Entity entity1, ParentAndChildComponent parentAndChildComponent, Engine engine){

        if(parentAndChildComponent == null){
            parentAndChildComponent = entity1.getComponent(ParentAndChildComponent.class);
        }

        engine.removeEntity(entity1);
        for (Entity entity :
                parentAndChildComponent.childs) {
            engine.removeEntity(entity);
            if(entity.getComponent(ParentAndChildComponent.class) != null){
                removeChildsRecurcsively(entity, entity.getComponent(ParentAndChildComponent.class), engine);
            }
        }
    }

}
