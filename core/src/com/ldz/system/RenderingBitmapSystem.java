package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BitmapFontComponent;
import com.ldz.component.CurrencyComponent;
import com.ldz.component.TranformComponent;

/**
 * Created by Loic on 19/08/2017.
 */
public class RenderingBitmapSystem extends IteratingSystem {

    private OrthographicCamera orthographicCamera;
    private SpriteBatch batch;

    public RenderingBitmapSystem(OrthographicCamera orthographicCamera, SpriteBatch spriteBatch) {
        super(Family.all(BitmapFontComponent.class, TranformComponent.class).get());
        this.orthographicCamera = orthographicCamera;
        this.batch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        //get components
        BitmapFontComponent bitmapFontComponent = entity.getComponent(BitmapFontComponent.class);
        TranformComponent tranformComponent = entity.getComponent(TranformComponent.class);

            if(bitmapFontComponent != null && tranformComponent != null){

                //position
                Vector2 position = tranformComponent.position;

            //get score component if available
            CurrencyComponent currencyComponent = entity.getComponent(CurrencyComponent.class);

            if(currencyComponent != null){
                bitmapFontComponent.bitmapFont.draw(batch, String.valueOf(MathUtils.round(currencyComponent.currentValue)) + " (x "+ currencyComponent.currentTradeRate +")", position.x, position.y);
            } else {
                bitmapFontComponent.bitmapFont.draw(batch, bitmapFontComponent.stringToDisplay, position.x, position.y);
            }

        }

    }
}
