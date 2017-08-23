package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.config.game.entities.EntityId;
import com.ldz.entity.EntityWithId;
import com.ldz.util.ParentAndChildUtil;

import java.util.function.Function;

/**
 * Created by Loic on 20/08/2017.
 */
public class BuyableUpgradePopupSystem extends IteratingSystem {

    private static final String TAG = BuyableUpgradePopupSystem.class.getSimpleName();

    private static BuyableUpgradePopupSystem instance = null;
    private ImmutableArray<Entity> currencyEntities;

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
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BuyableUpgradeComponent buyableUpgradeComponent = entity.getComponent(BuyableUpgradeComponent.class);

        if (buyableUpgradeComponent != null) {

            ParentAndChildUtil.forEachChildsRecursively(entity, new Function<Entity, Void>() {
                @Override
                public Void apply(Entity entity) {

                    if (entity instanceof EntityWithId) {
                        EntityWithId entityWithId = (EntityWithId) entity;
                        if (entityWithId.getId().equals(EntityId.upgrade_1_cost_display)) {
                            //font component
                            BitmapFontComponent bitmapFontComponent = entity.getComponent(BitmapFontComponent.class);
                            if (bitmapFontComponent != null) {
                                bitmapFontComponent.stringToDisplay = "Cost : " + String.valueOf(buyableUpgradeComponent.objectCost.getCurrencies().get(CurrencyComponent.CURRENCY_TYPE.ITHEREUM_COIN));
                                bitmapFontComponent.bitmapFont.setColor(Color.BLUE);
                            }
                        }
                    }

                    return null;
                }
            });

            switch (buyableUpgradeComponent.state) {
                case PENDING:
                    break;
                case ASKING_FOR_UPGRADE:

                    //check if upgrade is buyable by the player
                    if (checkIfUpgradeIsBuyableByThePlayer(buyableUpgradeComponent)) {
                        for (Entity currencyEntity :
                                this.currencyEntities) {
                            CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
                            if (currencyComponent != null) {
                                currencyComponent.scoreToRemove = (-1) * buyableUpgradeComponent.objectCost.getCurrencies().get(currencyComponent.currencyType);
                            }
                        }
                    }
                    buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.PENDING;
                    break;
            }

        }

        //set the price to font

    }

    private boolean checkIfUpgradeIsBuyableByThePlayer(BuyableUpgradeComponent buyableUpgradeComponent) {

        int nbAwaitedPositive = CurrencyComponent.CURRENCY_TYPE.values().length;

        for (Entity currencyEntity :
                this.currencyEntities) {

            CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
            if (currencyComponent != null) {
                //get appropriate upgradeCostComponent

                if (buyableUpgradeComponent.objectCost.getCurrencies().get(currencyComponent.currencyType) < currencyComponent.currentValue) {
                    nbAwaitedPositive--;
                }
            }

        }

        if (nbAwaitedPositive == 0) {
            Gdx.app.debug(TAG, "Allowing upgrade buy.");
            return true;
        } else {
            Gdx.app.debug(TAG, "Not allowing upgrade buy.");
            return false;
        }

    }


}
