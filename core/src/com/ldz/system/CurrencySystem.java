package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.CurrencyComponent;
import com.ldz.input.CurrencyInputProcessor;
import com.ldz.input.MyInputMultiplexer;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencySystem extends EntitySystem {

    private static CurrencySystem instance = null;
    private ImmutableArray<Entity> currencyEntities;

    public CurrencySystem() {
        MyInputMultiplexer.getInstance().addProcessor(new CurrencyInputProcessor());
    }

    public static CurrencySystem getInstance() {
        if (instance == null) {
            instance = new CurrencySystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        currencyEntities = engine.getEntitiesFor(Family.all(CurrencyComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (Entity entity :
                currencyEntities) {
            CurrencyComponent currencyComponent = entity.getComponent(CurrencyComponent.class);

            if (currencyComponent != null) {
                if (currencyComponent.scoreToAdd != 0.0f) {
                    currencyComponent.currentValue += (currencyComponent.scoreToAdd * currencyComponent.currentTradeRate);
                    currencyComponent.scoreToAdd = 0.0f;
                }
                if (currencyComponent.scoreToRemove != 0.0f) {
                    currencyComponent.currentValue += currencyComponent.scoreToRemove;
                    currencyComponent.scoreToRemove = 0.0f;
                }
            }
        }
    }

    public ImmutableArray<Entity> getCurrencyEntities() {
        return currencyEntities;
    }

}
