package com.ldz.config.upgrade.referential.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ldalzotto on 05/09/2017.
 */
public class Upgrades implements Serializable {
    private List<Upgrade> upgrades;

    public List<Upgrade> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(List<Upgrade> upgrades) {
        this.upgrades = upgrades;
    }
}
