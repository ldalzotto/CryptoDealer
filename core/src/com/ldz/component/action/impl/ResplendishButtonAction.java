package com.ldz.component.action.impl;

import com.badlogic.ashley.core.Entity;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.action.IAction;

/**
 * Created by Loic on 04/09/2017.
 */
public class ResplendishButtonAction implements IAction {

    @Override
    public void apply(Entity entity) {
        //get cost
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        if (parentAndChildComponent != null) {
            Entity parentEntity = parentAndChildComponent.parent;
            BuyableUpgradeComponent buyableUpgradeComponent = parentEntity.getComponent(BuyableUpgradeComponent.class);
            buyableUpgradeComponent.state = BuyableUpgradeComponent.STATE.ASKING_FOR_RESPLENDISH;
        }
    }
}
