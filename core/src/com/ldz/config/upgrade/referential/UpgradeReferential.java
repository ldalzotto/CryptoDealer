package com.ldz.config.upgrade.referential;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.ldz.config.upgrade.referential.domain.PriceCurrencies;
import com.ldz.config.upgrade.referential.domain.Upgrade;
import com.ldz.config.upgrade.referential.domain.Upgrades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ldalzotto on 05/09/2017.
 */
public class UpgradeReferential {

    private static final String TAG = UpgradeReferential.class.getSimpleName();

    private static UpgradeReferential instance;

    private Upgrades upgrades;
    private Json json = new Json();

    public static UpgradeReferential getInstance() {
        if (instance == null) {
            instance = new UpgradeReferential();
        }
        return instance;
    }

    private UpgradeReferential() {
        this.upgrades = this.json.fromJson(Upgrades.class, Gdx.files.internal("config/upgrade/UpgradeReferential.json"));
    }

    public enum UPGRADE_DETAILS_TYPE {
        UPGRADE_COST, UPGRADE_BONUSES;
    }

    private Map<UPGRADE_DETAILS_TYPE, PriceCurrencies> extractLevelInfoOfUpgrade(Upgrade upgradeDetails, int desiredLevel) {
        Map<UPGRADE_DETAILS_TYPE, PriceCurrencies> upgrade_details_typePriceCurrenciesMap = new HashMap<>();

        for (PriceCurrencies priceCurrencies :
                upgradeDetails.getUpgradeBonuses()) {
            if (priceCurrencies.getLevelNumber() == desiredLevel) {
                //TODO
            }
        }
        return null;
    }
}
