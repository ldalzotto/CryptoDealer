package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.system.custom.MyIteratingSystem;
import com.ldz.util.LoggingUtil;
import com.ldz.util.UpgradeUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class BuyableUpgradePopupSystem extends MyIteratingSystem {

    private static final String TAG = BuyableUpgradePopupSystem.class.getSimpleName();

    private static BuyableUpgradePopupSystem instance = null;
    private ImmutableArray<Entity> currencyEntities;
    private ImmutableArray<Entity> persistantUpgradeEntity;

    public BuyableUpgradePopupSystem() {
        super(Family.all(BuyableUpgradeComponent.class).get());
    }

    public static BuyableUpgradePopupSystem getInstance() {
        if (instance == null) {
            instance = new BuyableUpgradePopupSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        currencyEntities = engine.getEntitiesFor(Family.all(CurrencyComponent.class).get());
        persistantUpgradeEntity = engine.getEntitiesFor(Family.all(PersistantUpgradeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BuyableUpgradeComponent buyableUpgradeComponent = entity.getComponent(BuyableUpgradeComponent.class);

        if (buyableUpgradeComponent != null) {
            PersistantUpgradeComponent persistantUpgradeComponent = UpgradeUtil.retrievePersistantUpgrade(buyableUpgradeComponent, this.persistantUpgradeEntity);

            //send data to childs
            ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
            if (parentAndChildComponent != null && persistantUpgradeComponent != null) {
                parentAndChildComponent.dataToTransit.put(ParentAndChildComponent.DATA_TO_TRANSIT_KEY.UPGRADE_ID_KEY, persistantUpgradeComponent.upgradeId);
                parentAndChildComponent.dataToTransit.put(ParentAndChildComponent.DATA_TO_TRANSIT_KEY.UPGRADE_PERFORMANCE, persistantUpgradeComponent.itemPerformances);
            }

            switch (buyableUpgradeComponent.state) {
                case PENDING:
                    break;
                case ASKING_FOR_UPGRADE:
                    //check if upgrade is buyable by the player
                    if (checkIfUpgradeIsBuyableByThePlayer(persistantUpgradeComponent)) {
                        for (Entity currencyEntity :
                                this.currencyEntities) {
                            CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
                            if (currencyComponent != null) {
                                currencyComponent.scoreToRemove = (-1) * persistantUpgradeComponent.objectCost.getCurrencies().get(currencyComponent.currencyType);
                                persistantUpgradeComponent.state = PersistantUpgradeComponent.STATE.UPGRADING; //upgrading persistance component
                            }
                        }
                    }
                    buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.PENDING;
                    break;
                case ASKING_FOR_RESPLENDISH:
                    persistantUpgradeComponent.itemPerformances = 1f;
                    buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.PENDING;
                    break;
            }

        }

        //set the price to font

    }

    private boolean checkIfUpgradeIsBuyableByThePlayer(PersistantUpgradeComponent persistantUpgradeComponent) {

        int nbAwaitedPositive = CurrencyComponent.CURRENCY_TYPE.values().length;

        for (Entity currencyEntity :
                this.currencyEntities) {

            CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
            if (currencyComponent != null) {
                //get appropriate upgradeCostComponent

                if (persistantUpgradeComponent.objectCost.getCurrencies().get(currencyComponent.currencyType) < currencyComponent.currentValue) {
                    nbAwaitedPositive--;
                }
            }

        }

        if (nbAwaitedPositive == 0) {
            LoggingUtil.DEBUG(TAG, "Allowing upgrade buy.");
            return true;
        } else {
            LoggingUtil.DEBUG(TAG, "Not allowing upgrade buy.");
            return false;
        }

    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.getEntities(), this.currencyEntities, this.persistantUpgradeEntity);
        return entities;
    }
}
