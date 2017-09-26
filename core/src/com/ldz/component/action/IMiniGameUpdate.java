package com.ldz.component.action;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.ldz.component.BagOfEntitiesComponent;

import java.util.List;

/**
 * Created by Loic on 17/09/2017.
 */
public interface IMiniGameUpdate {

    public List<Entity> addNewEntityToEngine();

    public void removeEntityToRemoveToEngine();

    public void init(Rectangle gameArea, BagOfEntitiesComponent parentBagOfEntitiesComponent);

    public int update(float delta);

}
