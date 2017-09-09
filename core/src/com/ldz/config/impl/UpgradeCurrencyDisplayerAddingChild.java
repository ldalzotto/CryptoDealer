package com.ldz.config.impl;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.config.game.entities.EntityId;
import com.ldz.config.itf.IAddingInstanceChildOnComplete;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.util.EntityUtil;

/**
 * Created by Loic on 09/09/2017.
 */
public class UpgradeCurrencyDisplayerAddingChild implements IAddingInstanceChildOnComplete {

    private static final String TAG = UpgradeCurrencyDisplayerAddingChild.class.getSimpleName();

    @Override
    public void addingChilds(Entity entity) {

        BagOfEntitiesComponent bagOfEntitiesComponent = entity.getComponent(BagOfEntitiesComponent.class);

        float initFloat = 300;
        if (bagOfEntitiesComponent != null) {
            for (CurrencyComponent.CURRENCY_TYPE currency_type :
                    CurrencyComponent.CURRENCY_TYPE.values()) {

                EntityWithId fontDisplayEntity = new EntityWithId();
                fontDisplayEntity = EntityUtil.addFontDisplayComponentsFromEntityId(new Vector2(20f, initFloat), "tess", fontDisplayEntity);
                fontDisplayEntity = EntityUtil.addUpgradeCurrencyDisplayerCompoent(currency_type, fontDisplayEntity);
                fontDisplayEntity.setId(EntityId.upgrade_currency_displayer);

                bagOfEntitiesComponent.entities.add(fontDisplayEntity);

                initFloat += 50;
            }
            bagOfEntitiesComponent.addEntityToEngine = true;
        }

    }
}
