package com.ldz.config.game.entities.domain;

/**
 * Created by Loic on 20/08/2017.
 */
public class GameEntity {

    private String id;
    private Instance instance;
    private Instance addChildOnComplete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public Instance getAddChildOnComplete() {
        return addChildOnComplete;
    }

    public void setAddChildOnComplete(Instance addChildOnComplete) {
        this.addChildOnComplete = addChildOnComplete;
    }
}
