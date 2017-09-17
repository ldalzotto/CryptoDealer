package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.ldz.component.action.IMiniGameUpdate;

/**
 * Created by Loic on 17/09/2017.
 */
public class MiniGameComponent implements Component {

    public final static float EXECUTION_TIME = 5;
    public int goalScore = 0;
    public int currencScore = 0;
    public STATE state = STATE.PENDING;
    public IMiniGameUpdate iMiniGameUpdate;
    public float executionTimeAccumulator = 0;
    //transited from childs
    public BuyableUpgradeComponent.STATE buyableUpgradeCompState;
    public PersistantUpgradeComponent.UpgradeId upgradeId;

    public enum STATE {
        PENDING,
        RUNNING,
        COMPLETED, DESTROY;
    }

}
