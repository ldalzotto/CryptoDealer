package com.ldz.config.upgrade.referential.domain;

import com.ldz.component.PersistantUpgradeComponent;

import java.util.List;

/**
 * Created by ldalzotto on 05/09/2017.
 */
public class Upgrade {

    private PersistantUpgradeComponent.UpgradeId upgradeId;
    private List<PriceCurrencies> upgradesCosts;
    private List<PriceCurrencies> upgradeBonuses;

    public PersistantUpgradeComponent.UpgradeId getUpgradeId() {
        return upgradeId;
    }

    public void setUpgradeId(PersistantUpgradeComponent.UpgradeId upgradeId) {
        this.upgradeId = upgradeId;
    }

    public List<PriceCurrencies> getUpgradesCosts() {
        return upgradesCosts;
    }

    public void setUpgradesCosts(List<PriceCurrencies> upgradesCosts) {
        this.upgradesCosts = upgradesCosts;
    }

    public List<PriceCurrencies> getUpgradeBonuses() {
        return upgradeBonuses;
    }

    public void setUpgradeBonuses(List<PriceCurrencies> upgradeBonuses) {
        this.upgradeBonuses = upgradeBonuses;
    }
}
