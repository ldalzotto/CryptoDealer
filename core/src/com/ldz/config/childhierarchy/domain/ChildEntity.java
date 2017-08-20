package com.ldz.config.childhierarchy.domain;

import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class ChildEntity {

    private String classname;
    private List<ChildEntity> childs;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<ChildEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildEntity> childs) {
        this.childs = childs;
    }
}
