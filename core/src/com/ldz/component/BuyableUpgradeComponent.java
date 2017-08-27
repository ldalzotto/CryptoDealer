package com.ldz.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Loic on 20/08/2017.
 */
public class BuyableUpgradeComponent implements Component {

    public STATE state = STATE.PENDING;

    public PersistantUpgradeComponent.UpgradeId upgradeId;

    public enum STATE {
        PENDING,
        ASKING_FOR_UPGRADE;
    }

}
