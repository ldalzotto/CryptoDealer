package com.ldz.system.mini.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.engine.functional.AbstractFunctionalEngineUpdate;

import java.util.List;

/**
 * Created by Loic on 28/09/2017.
 */
public class RefreshCoolerEngineUpdate extends AbstractFunctionalEngineUpdate {

    public RefreshCoolerEngineUpdate(Engine associatedEngine) {
        super(associatedEngine);
    }

    @Override
    public void init(Rectangle gameArea, BagOfEntitiesComponent parentBagOfEntitiesComponent) {

    }

    @Override
    public void updateMinigame(float delta) {

    }

    @Override
    public void removeEntityToMinigame(List<Entity> entityToRemove) {

    }
}
