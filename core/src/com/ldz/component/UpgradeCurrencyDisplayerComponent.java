package com.ldz.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Loic on 06/09/2017.
 */
public class UpgradeCurrencyDisplayerComponent implements Component {

    public PersistantUpgradeComponent.UpgradeId upgradeId;
    public CurrencyComponent.CURRENCY_TYPE currencyType;

}
