package com.ldz.config.game.entities.domain;

import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class Constructor {

    private String classname;
    private List<Parameter> parameters;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
