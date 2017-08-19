package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.TranformComponent;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencyEntity extends Entity {

    public CurrencyEntity(Vector2 position, Color color, float tradeRate) {
        CurrencyComponent currencyComponent = new CurrencyComponent();
        currencyComponent.currentTradeRate = tradeRate;

        BitmapFontComponent bitmapFontComponent = new BitmapFontComponent();
        bitmapFontComponent.bitmapFont.setColor(color);

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;

        this.add(bitmapFontComponent);
        this.add(currencyComponent);
        this.add(tranformComponent);
    }
}
