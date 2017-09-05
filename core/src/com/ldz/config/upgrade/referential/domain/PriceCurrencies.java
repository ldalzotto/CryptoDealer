package com.ldz.config.upgrade.referential.domain;

import java.util.List;

/**
 * Created by ldalzotto on 05/09/2017.
 */
public class PriceCurrencies {

    private int levelNumber;
    private List<Currency> currencies;

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

}
