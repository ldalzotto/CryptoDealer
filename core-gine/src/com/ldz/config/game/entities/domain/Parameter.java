package com.ldz.config.game.entities.domain;

/**
 * Created by Loic on 20/08/2017.
 */
public class Parameter {

    private String classname;
    private String value;
    private Constructor constructor;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }
}
