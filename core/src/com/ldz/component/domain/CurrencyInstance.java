package com.ldz.component.domain;

import com.ldz.component.CurrencyComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Loic on 22/08/2017.
 */
public class CurrencyInstance {

    Map<CurrencyComponent.CURRENCY_TYPE, Float> currencies = new HashMap<>();

    public CurrencyInstance(float zitCoinVal, float ithereumVal, float loudCoinVal) {
        this.currencies.put(CurrencyComponent.CURRENCY_TYPE.ITHEREUM_COIN, ithereumVal);
        this.currencies.put(CurrencyComponent.CURRENCY_TYPE.ZIT_COIN, zitCoinVal);
        this.currencies.put(CurrencyComponent.CURRENCY_TYPE.LOUD_COIN, loudCoinVal);
    }

    public Map<CurrencyComponent.CURRENCY_TYPE, Float> getCurrencies() {
        return currencies;
    }


    public CurrencyInstance multiplyAllBy(float multiplier) {
        for (Map.Entry<CurrencyComponent.CURRENCY_TYPE, Float> entry :
                this.currencies.entrySet()) {
            entry.setValue(entry.getValue() * multiplier);
        }
        return this;
    }
}
