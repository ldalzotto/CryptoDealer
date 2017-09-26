package com.ldz.component.action;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.ldz.component.BagOfEntitiesComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 26/09/2017.
 */
public abstract class AbstractMiniGameUpdate implements IMiniGameUpdate {

    protected List<Entity> entityToAddToEngine = new ArrayList<>();
    protected List<Entity> entityToRemoveToEngine = new ArrayList<>();
    protected BagOfEntitiesComponent parentBagOfEntitiesContainer;
    private Engine associatedEngine;

    public AbstractMiniGameUpdate(Engine associatedEngine) {
        this.associatedEngine = associatedEngine;
    }

    @Override
    public List<Entity> addNewEntityToEngine() {
        List<Entity> returnList = new ArrayList<>();
        for (Entity entity :
                this.entityToAddToEngine) {
            this.associatedEngine.addEntity(entity);
            if (this.parentBagOfEntitiesContainer != null) {
                this.parentBagOfEntitiesContainer.entities.add(entity);
            }
            returnList.add(entity);
        }
        this.entityToAddToEngine.clear();
        return returnList;
    }

    @Override
    public void removeEntityToRemoveToEngine() {
        for (Entity entity :
                this.entityToRemoveToEngine) {
            this.associatedEngine.removeEntity(entity);
            if (this.parentBagOfEntitiesContainer != null) {
                this.parentBagOfEntitiesContainer.entities.remove(entity);
            }
        }
        this.entityToRemoveToEngine.clear();
    }


    @Override
    public int update(float delta) {
        this.addNewEntityToEngine();

        this.updateMinigame(delta);

        this.removeEntityToMinigame(this.entityToRemoveToEngine);
        this.removeEntityToRemoveToEngine();
        return 0;
    }


    public abstract void updateMinigame(float delta);

    public abstract void removeEntityToMinigame(List<Entity> entityToRemove);

}
