package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class BagOfEntitiesComponent implements Component {

    public List<Entity> entities = new ArrayList<>();
    public boolean addEntityToEngine = false;

}
