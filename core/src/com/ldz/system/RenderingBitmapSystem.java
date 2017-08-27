package com.ldz.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.TranformComponent;
import com.ldz.system.custom.MyIteratingSystem;
import com.ldz.util.ComponentUtil;

import java.util.Map;

/**
 * Created by Loic on 19/08/2017.
 */
public class RenderingBitmapSystem extends MyIteratingSystem {

    private static final String TAG = RenderingBitmapSystem.class.getSimpleName();

    private OrthographicCamera orthographicCamera;
    private SpriteBatch batch;

    public RenderingBitmapSystem(OrthographicCamera orthographicCamera, SpriteBatch spriteBatch) {
        super(Family.all(BitmapFontComponent.class, TranformComponent.class).get());
        this.orthographicCamera = orthographicCamera;
        this.batch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        Map<String, Component> componentContainer;
        try {
            componentContainer = ComponentUtil.getAllComponentsFromEntity(entity, BitmapFontComponent.class, TranformComponent.class);
        } catch (Exception e) {
            Gdx.app.error(TAG, e.getMessage());
            Gdx.app.error(TAG, e.getCause().toString());
            return;
        }

        //get components
        BitmapFontComponent bitmapFontComponent = (BitmapFontComponent) componentContainer.get(BitmapFontComponent.class.getSimpleName());
        TranformComponent tranformComponent = (TranformComponent) componentContainer.get(TranformComponent.class.getSimpleName());


        //position
        Vector2 position = tranformComponent.position;

        //get score component if available
        CurrencyComponent currencyComponent = entity.getComponent(CurrencyComponent.class);

        if (currencyComponent != null) {
            bitmapFontComponent.bitmapFont.draw(batch, String.valueOf(MathUtils.round(currencyComponent.currentValue)) + " (x " + currencyComponent.currentTradeRate + ")", position.x, position.y);
        } else {
            bitmapFontComponent.bitmapFont.draw(batch, bitmapFontComponent.stringToDisplay, position.x, position.y);
        }

    }

}
