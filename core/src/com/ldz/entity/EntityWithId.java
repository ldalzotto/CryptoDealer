package com.ldz.entity;

import com.badlogic.ashley.core.Entity;

/**
 * Created by Loic on 20/08/2017.
 */
public class EntityWithId extends Entity {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
