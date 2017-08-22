package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.domain.CurrencyInstance;

/**
 * Created by Loic on 20/08/2017.
 */
public class UpgradePopupEntity extends PopUpEntity {

    public UpgradePopupEntity(Vector2 position, Texture texture, int z, CurrencyInstance objectCost) {
        super(position, texture, z);

        BuyableUpgradeComponent buyableUpgradeComponent = new BuyableUpgradeComponent();
        buyableUpgradeComponent.objectCost = objectCost;

        this.add(buyableUpgradeComponent);
    }
}
