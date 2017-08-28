package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Loic on 28/08/2017.
 */
public class TappingSystem extends EntitySystem implements IRetrieveAllEntitiesFromSystem {

    private static TappingSystem instance;
    private ImmutableArray<Entity> currencyEntities;
    private ImmutableArray<Entity> persistantUpgradeEntities;

    public static TappingSystem getInstance() {
        if (instance == null) {
            instance = new TappingSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.currencyEntities = engine.getEntitiesFor(Family.all(CurrencyComponent.class).get());
        this.persistantUpgradeEntities = engine.getEntitiesFor(Family.all(PersistantUpgradeComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (Gdx.input.justTouched()) {
            //adding to score
            for (Entity currencyEntity :
                    currencyEntities) {
                CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
                if (currencyComponent != null) {
                    currencyComponent.scoreToAdd = 1.0f;
                }
            }

        }
    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(currencyEntities, persistantUpgradeEntities);
        return entities;
    }
}
