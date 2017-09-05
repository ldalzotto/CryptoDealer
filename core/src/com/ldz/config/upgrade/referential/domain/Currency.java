package com.ldz.config.upgrade.referential.domain;

import com.ldz.component.CurrencyComponent;

/**
 * Created by ldalzotto on 05/09/2017.
 */
public class Currency {
    private CurrencyComponent.CURRENCY_TYPE currency;
    private float value;

    public CurrencyComponent.CURRENCY_TYPE getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyComponent.CURRENCY_TYPE currency) {
        this.currency = currency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
