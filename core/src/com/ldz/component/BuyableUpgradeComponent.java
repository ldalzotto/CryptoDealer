package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.component.domain.CurrencyInstance;

/**
 * Created by Loic on 20/08/2017.
 */
public class BuyableUpgradeComponent implements Component {

    public CurrencyInstance objectCost;
    public STATE state = STATE.PENDING;
    public float upgradePerformance = 1.0f;


    public enum STATE {
        PENDING,
        ASKING_FOR_UPGRADE;
    }

}
