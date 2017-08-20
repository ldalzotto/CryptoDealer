package com.ldz.listener;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.engine.MyEngine;
import com.ldz.util.ParentAndChildUtil;

/**
 * Created by Loic on 20/08/2017.
 */
public class EntityRemoveListener implements EntityListener {

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {
        Engine engine = MyEngine.getInstance();

        //get parend and child component
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        if(parentAndChildComponent != null){
            ParentAndChildUtil.removeChildsRecurcsively(entity, parentAndChildComponent, engine);
        }

    }
}
