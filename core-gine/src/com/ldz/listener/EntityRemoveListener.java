package com.ldz.listener;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.engine.CoreGine;
import com.ldz.util.ParentAndChildUtil;

/**
 * Created by Loic on 20/08/2017.
 */
public class EntityRemoveListener implements EntityListener {

    CoreGine coreGine;

    public EntityRemoveListener(CoreGine coreGine) {
        this.coreGine = coreGine;
    }

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity) {

        //get parend and child component
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        BagOfEntitiesComponent bagOfEntitiesComponent = entity.getComponent(BagOfEntitiesComponent.class);
        ParentAndChildUtil.removeChildsRecurcsively(entity, parentAndChildComponent, bagOfEntitiesComponent, this.coreGine);

    }
}
