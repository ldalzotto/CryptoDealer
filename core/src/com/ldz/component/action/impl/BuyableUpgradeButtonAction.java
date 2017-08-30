package com.ldz.component.action.impl;

import com.badlogic.ashley.core.Entity;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.action.IOnAction;

/**
 * Created by Loic on 30/08/2017.
 */
public class BuyableUpgradeButtonAction implements IOnAction {

    @Override
    public void apply(Entity entity) {
        //get cost
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        if (parentAndChildComponent != null) {
            Entity parentEntity = parentAndChildComponent.parent;
            BuyableUpgradeComponent buyableUpgradeComponent = parentEntity.getComponent(BuyableUpgradeComponent.class);
            buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.ASKING_FOR_UPGRADE;
        }
    }
}
