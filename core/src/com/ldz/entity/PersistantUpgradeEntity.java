package com.ldz.entity;

import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.util.UpgradeUtil;

/**
 * Created by Loic on 27/08/2017.
 */
public class PersistantUpgradeEntity extends EntityWithId {

    public PersistantUpgradeEntity(String upgradeId, float decayRate) {

        PersistantUpgradeComponent persistantUpgradeComponent = new PersistantUpgradeComponent();
        persistantUpgradeComponent.decayRatePerSeconds = decayRate;
        persistantUpgradeComponent.itemPerformances = 1.0f;
        persistantUpgradeComponent.upgradeId = PersistantUpgradeComponent.UpgradeId.valueOf(upgradeId);
        persistantUpgradeComponent = UpgradeUtil.evaluatePersistantUpgradeComponentCostAndBonus(persistantUpgradeComponent);

        this.add(persistantUpgradeComponent);

    }
}
