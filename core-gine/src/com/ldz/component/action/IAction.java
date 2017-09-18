package com.ldz.component.action;

import com.badlogic.ashley.core.Entity;

/**
 * Created by Loic on 30/08/2017.
 */
public interface IAction {

    public void apply(Entity entity);

}
