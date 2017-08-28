package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.component.domain.CurrencyInstance;

/**
 * Created by Loic on 27/08/2017.
 */
public class PersistantUpgradeComponent implements Component {

    public UpgradeId upgradeId;
    public CurrencyInstance objectCost;
    public CurrencyInstance objectBonus;

    public float itemPerformances = 1.0f;
    public float decayRatePerSeconds = 0.01f;
    public float timeAccumulator = 0f;
    public int upgradeLevel = 0;

    public STATE state = STATE.PENDING;

    public enum UpgradeId {
        UPGRADE_1,
        UPGRADE_2;
    }

    public enum STATE {
        PENDING,
        UPGRADING;
    }

}
