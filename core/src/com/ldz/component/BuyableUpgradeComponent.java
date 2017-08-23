package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.component.domain.CurrencyInstance;

/**
 * Created by Loic on 20/08/2017.
 */
public class BuyableUpgradeComponent implements Component {

    public CurrencyInstance objectCost;
    public STATE state = STATE.PENDING;

    public float itemPerformances = 1.0f;
    public float decayRatePerSeconds = 0.01f;
    public float timeAccumulator = 0f;

    public enum STATE {
        PENDING,
        ASKING_FOR_UPGRADE;
    }

}
