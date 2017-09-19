package com.ldz.config.game.entities.domain;

import java.util.List;

/**
 * Created by Loic on 10/09/2017.
 */
public class GameEntityInstance {

    private String entityId;
    private String instanceId;
    private Instance addChildOnComplete;


    private List<Parameter> constructorArgs;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
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
