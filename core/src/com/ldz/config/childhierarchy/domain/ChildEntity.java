package com.ldz.config.childhierarchy.domain;

import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class ChildEntity {

    private String id;
    private List<ChildEntity> childs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChildEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildEntity> childs) {
        this.childs = childs;
    }
}
