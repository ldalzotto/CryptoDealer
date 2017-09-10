package com.ldz.config.game.entities.instance.domain;

import com.ldz.config.game.entities.EntityId;
import com.ldz.config.game.entities.InstanceEntityId;
import com.ldz.config.game.entities.domain.Instance;
import com.ldz.config.game.entities.domain.Parameter;

import java.util.List;

/**
 * Created by Loic on 10/09/2017.
 */
public class GameEntityInstance {

    private EntityId entityId;
    private InstanceEntityId instanceId;
    private Instance addChildOnComplete;


    private List<Parameter> constructorArgs;

    public EntityId getEntityId() {
        return entityId;
    }

    public void setEntityId(EntityId entityId) {
        this.entityId = entityId;
    }

    public InstanceEntityId getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(InstanceEntityId instanceId) {
        this.instanceId = instanceId;
    }

    public List<Parameter> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<Parameter> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Instance getAddChildOnComplete() {
        return addChildOnComplete;
    }

    public void setAddChildOnComplete(Instance addChildOnComplete) {
        this.addChildOnComplete = addChildOnComplete;
    }
}
