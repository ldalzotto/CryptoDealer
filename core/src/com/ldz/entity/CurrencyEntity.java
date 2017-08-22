package com.ldz.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.TranformComponent;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencyEntity extends EntityWithId {

    public CurrencyEntity(Vector2 position, Color color, float tradeRate, String currencyType) {
        CurrencyComponent currencyComponent = new CurrencyComponent();
        currencyComponent.currentTradeRate = tradeRate;
        currencyComponent.currencyType = CurrencyComponent.CURRENCY_TYPE.valueOf(currencyType);


        BitmapFontComponent bitmapFontComponent = new BitmapFontComponent();
        bitmapFontComponent.bitmapFont.setColor(color);

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;

        this.add(bitmapFontComponent);
        this.add(currencyComponent);
        this.add(tranformComponent);
    }
}
