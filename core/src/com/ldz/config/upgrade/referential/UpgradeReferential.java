package com.ldz.config.upgrade.referential;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.component.PersistantUpgradeComponent;
import com.ldz.config.upgrade.referential.domain.PriceCurrencies;
import com.ldz.config.upgrade.referential.domain.Upgrade;
import com.ldz.config.upgrade.referential.domain.Upgrades;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ldalzotto on 05/09/2017.
 */
public class UpgradeReferential {

    private static final String TAG = UpgradeReferential.class.getSimpleName();

    private static UpgradeReferential instance;

    private Upgrades upgrades;
    private Json json = new Json();

    private UpgradeReferential() {
        this.upgrades = this.json.fromJson(Upgrades.class, Gdx.files.internal("config/upgrade/UpgradeReferential.json"));
    }

    public static UpgradeReferential getInstance() {
        if (instance == null) {
            instance = new UpgradeReferential();
        }
        return instance;
    }

    public Map<UPGRADE_DETAILS_TYPE, PriceCurrencies> extractUpgradeInfoFromIdAndDesiredLevel(PersistantUpgradeComponent.UpgradeId upgradeId, int desiredLevel) {

        Map<UPGRADE_DETAILS_TYPE, PriceCurrencies> upgrade_details_typePriceCurrenciesMap = null;

        for (Upgrade upgrade :
                this.upgrades.getUpgrades()) {
            if (upgrade.getUpgradeId().equals(upgradeId)) {
                upgrade_details_typePriceCurrenciesMap = this.extractLevelInfoOfUpgrade(upgrade, desiredLevel);
            }
        }

        return upgrade_details_typePriceCurrenciesMap;
    }

    private Map<UPGRADE_DETAILS_TYPE, PriceCurrencies> extractLevelInfoOfUpgrade(Upgrade upgradeDetails, int desiredLevel) {
        Map<UPGRADE_DETAILS_TYPE, PriceCurrencies> upgrade_details_typePriceCurrenciesMap = new HashMap<>();

        for (PriceCurrencies priceCurrencies :
                upgradeDetails.getUpgradeBonuses()) {
            if (priceCurrencies.getLevelNumber() == desiredLevel) {
                upgrade_details_typePriceCurrenciesMap.put(UPGRADE_DETAILS_TYPE.UPGRADE_BONUSES, priceCurrencies);
            }
        }


        for (PriceCurrencies priceCurrencies :
                upgradeDetails.getUpgradesCosts()) {
            if (priceCurrencies.getLevelNumber() == desiredLevel) {
                upgrade_details_typePriceCurrenciesMap.put(UPGRADE_DETAILS_TYPE.UPGRADE_COST, priceCurrencies);
            }
        }

        return upgrade_details_typePriceCurrenciesMap;
    }

    public enum UPGRADE_DETAILS_TYPE {
        UPGRADE_COST, UPGRADE_BONUSES;
    }
}
