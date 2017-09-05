package com.ldz.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencyComponent implements Component {

    public float currentValue = 0f;
    public float currentTradeRate = 1f;

    public float scoreToAdd = 0f;
    public float scoreToRemove = 0f;

    public CURRENCY_TYPE currencyType;

    public enum CURRENCY_TYPE {
        ZIT_COIN(Color.ORANGE), ITHEREUM_COIN(Color.BLUE), LOUD_COIN(Color.GREEN);

        private Color color;

        CURRENCY_TYPE(Color color) {
            this.color = color;
        }
    }

}
