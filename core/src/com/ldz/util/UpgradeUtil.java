package com.ldz.util;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.BuyableUpgradeComponent;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.component.domain.CurrencyInstance;
import com.ldz.config.upgrade.referential.UpgradeReferential;
import com.ldz.config.upgrade.referential.domain.PriceCurrencies;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Map;

/**
 * Created by Loic on 05/09/2017.
 */
public class UpgradeUtil {

    private static final String TAG = UpgradeUtil.class.getSimpleName();

    public static PersistantUpgradeComponent evaluatePersistantUpgradeComponentCostAndBonus(PersistantUpgradeComponent persistantUpgradeComponent) {

        Map<UpgradeReferential.UPGRADE_DETAILS_TYPE, PriceCurrencies> upgradeDetailsTypePriceCurrenciesMap =
                UpgradeReferential.getInstance().extractUpgradeInfoFromIdAndDesiredLevel(persistantUpgradeComponent.upgradeId, persistantUpgradeComponent.upgradeLevel);


        if (upgradeDetailsTypePriceCurrenciesMap != null && upgradeDetailsTypePriceCurrenciesMap.size() != 0) {
            PriceCurrencies upgradeBonusCurrencies = upgradeDetailsTypePriceCurrenciesMap.get(UpgradeReferential.UPGRADE_DETAILS_TYPE.UPGRADE_BONUSES);
            PriceCurrencies upgradeCostCurrencies = upgradeDetailsTypePriceCurrenciesMap.get(UpgradeReferential.UPGRADE_DETAILS_TYPE.UPGRADE_COST);

            if (upgradeBonusCurrencies != null) {
                persistantUpgradeComponent.objectBonus = new CurrencyInstance(upgradeBonusCurrencies.getCurrencies());
            }

            if (upgradeCostCurrencies != null) {
                persistantUpgradeComponent.objectCost = new CurrencyInstance(upgradeCostCurrencies.getCurrencies());
            }
        } else {
            LoggingUtil.DEBUG(TAG, "The upgrade has not been founded for this component", ReflectionToStringBuilder.toString(persistantUpgradeComponent));
        }
        return persistantUpgradeComponent;
    }

    public static PersistantUpgradeComponent retrievePersistantUpgrade(BuyableUpgradeComponent buyableUpgradeComponent, ImmutableArray<Entity> persistantUpgradeEntity) {
        PersistantUpgradeComponent.UpgradeId upgradeId = buyableUpgradeComponent.upgradeId;
        return retrievePersistantUpgrade(upgradeId, persistantUpgradeEntity);
    }

    public static PersistantUpgradeComponent retrievePersistantUpgrade(PersistantUpgradeComponent.UpgradeId upgradeId, ImmutableArray<Entity> persistantUpgradeEntity) {
        for (Entity persistanceEntity :
                persistantUpgradeEntity) {
            PersistantUpgradeComponent persistantUpgradeComponent = persistanceEntity.getComponent(PersistantUpgradeComponent.class);
            if (persistantUpgradeComponent != null) {
                if (persistantUpgradeComponent.upgradeId.equals(upgradeId)) {
                    return persistantUpgradeComponent;
                }
            }
        }
        return null;
    }

}
