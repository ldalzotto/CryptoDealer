package com.ldz.config.childhierarchy.domain;

import com.ldz.config.game.entities.EntityId;

import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class ChildEntity {

    private EntityId id;
    private List<ChildEntity> childs;

    public EntityId getId() {
        return id;
    }

    public void setId(EntityId id) {
        this.id = id;
    }

    public List<ChildEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildEntity> childs) {
        this.childs = childs;
    }
}
