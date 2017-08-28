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
import java.util.Map;

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

        if (Gdx.input.justTouched() && !PopupSystem.getInstance(null).popupActives()) {
            //adding to score
            for (Entity currencyEntity :
                    currencyEntities) {
                CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
                if (currencyComponent != null) {
                    currencyComponent.scoreToAdd = 1.0f;
                }
            }

            for (Entity persistantUpgradeNeitty :
                    persistantUpgradeEntities) {
                PersistantUpgradeComponent persistantUpgradeComponent = persistantUpgradeNeitty.getComponent(PersistantUpgradeComponent.class);
                if (persistantUpgradeComponent != null) {
                    //apply decay
                    persistantUpgradeComponent.objectBonus.multiplyAllBy(persistantUpgradeComponent.itemPerformances);
                    for (Map.Entry<CurrencyComponent.CURRENCY_TYPE, Float> entry :
                            persistantUpgradeComponent.objectBonus.getCurrencies().entrySet()) {

                        for (Entity currencyEntity :
                                currencyEntities) {
                            CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
                            if (currencyComponent != null) {
                                if (entry.getKey().equals(currencyComponent.currencyType)) {
                                    currencyComponent.scoreToAdd += entry.getValue();
                                }
                            }
                        }

                    }
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
