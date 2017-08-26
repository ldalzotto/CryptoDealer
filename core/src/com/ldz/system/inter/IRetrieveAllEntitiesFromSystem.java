package com.ldz.system.inter;

import com.badlogic.ashley.core.Entity;

import java.util.List;

/**
 * Created by Loic on 26/08/2017.
 */
public interface IRetrieveAllEntitiesFromSystem {

    public List<Iterable<Entity>> getAllEntities();

}
