package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.ParentAndChildComponent;

/**
 * Created by Loic on 20/08/2017.
 */
public class BuyableUpgradePopupSystem extends IteratingSystem {

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

        //get child
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        if (parentAndChildComponent != null && buyableUpgradeComponent != null) {
            for (Entity childEntity :
                    parentAndChildComponent.childs) {

                //font component
                BitmapFontComponent bitmapFontComponent = childEntity.getComponent(BitmapFontComponent.class);
                if (bitmapFontComponent != null) {
                    bitmapFontComponent.stringToDisplay = "Cost : " + String.valueOf(buyableUpgradeComponent.objectCost);
                    bitmapFontComponent.bitmapFont.setColor(Color.BLUE);
                }

                switch (buyableUpgradeComponent.state) {
                    case PENDING:
                        break;
                    case ASKING_FOR_UPGRADE:
                        for (Entity currencyEntity :
                                this.currencyEntities) {
                            CurrencyComponent currencyComponent = currencyEntity.getComponent(CurrencyComponent.class);
                            if (currencyComponent != null) {
                                currencyComponent.scoreToAdd = (-1) * buyableUpgradeComponent.objectCost;
                            }
                            buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.PENDING;
                        }
                        break;
                }

            }
        }

        //set the price to font

    }


}
