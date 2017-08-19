package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 19/08/2017.
 */
public class ParentAndChildComponent implements Component {

    public List<Entity> childs = new ArrayList<>();
    public Entity parent;

}
