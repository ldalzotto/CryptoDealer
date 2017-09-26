package com.ldz.component.action;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

/**
 * Created by Loic on 17/09/2017.
 */
public interface IMiniGameUpdate {

    public List<Entity> addNewEntityToEngine(Engine engine);

    public void init(Rectangle gameArea);

    public int update(float delta);

}
