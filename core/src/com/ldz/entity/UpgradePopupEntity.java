package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.config.game.entities.InstanceEntityId;

import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class UpgradePopupEntity extends PopUpEntity {

    public UpgradePopupEntity(Vector2 position, Texture texture, int z, String upgradeId, List<String> bagOfEntities,
                              String miniGameInstanceEntityId) {
        super(position, texture, z, bagOfEntities);

        BuyableUpgradeComponent buyableUpgradeComponent = new BuyableUpgradeComponent();
        buyableUpgradeComponent.upgradeId = PersistantUpgradeComponent.UpgradeId.valueOf(upgradeId);
        buyableUpgradeComponent.miniGameInstanceEntityId = InstanceEntityId.valueOf(miniGameInstanceEntityId);

        this.add(buyableUpgradeComponent);
    }
}
