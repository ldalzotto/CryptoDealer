package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.CurrencyComponent;
import com.ldz.input.CurrencyInputProcessor;
import com.ldz.input.MyInputMultiplexer;
import com.ldz.system.inter.IAddScore;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencySystem extends EntitySystem implements IAddScore {

    private static CurrencySystem instance = null;

    public static CurrencySystem getInstance() {
        if (instance == null) {
            instance = new CurrencySystem();
        }
        return instance;
    }

    public CurrencySystem() {
        MyInputMultiplexer.getInstance().addProcessor(new CurrencyInputProcessor());
    }

    private ImmutableArray<Entity> currencyEntities;

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
    }

    @Override
    public void addScore(float score) {
        for (Entity entity:
                currencyEntities) {
            CurrencyComponent currencyComponent = entity.getComponent(CurrencyComponent.class);

            if(currencyComponent != null){
                currencyComponent.currentValue += (score*currencyComponent.currentTradeRate);
            }
        }
    }
}
