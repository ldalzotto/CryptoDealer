package com.ldz.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencyComponent implements Component {

    public float currentValue = 0f;
    public float currentTradeRate = 1f;

    public float scoreToAdd = 0f;

}
