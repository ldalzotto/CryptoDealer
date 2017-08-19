package com.ldz.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Loic on 19/08/2017.
 */
public class EntityFactory {

                public enum ENTITY_TYPE{
                    ZIT_COIN_CURRENCY, ITHEREUM_CURRENCY, LOUD_COIN_CURRENCY;
                }

            public static Entity getEntity(ENTITY_TYPE entity_type){
                Entity entity = new Entity();

                switch (entity_type){
                    case ZIT_COIN_CURRENCY:
                        entity = new CurrencyEntity(new Vector2(10,20), Color.ORANGE, 1.0f);
                        break;
                    case ITHEREUM_CURRENCY:
                entity = new CurrencyEntity(new Vector2(10,40), Color.BLUE, 0.8f);
                break;
                case LOUD_COIN_CURRENCY:
                    entity = new CurrencyEntity(new Vector2(10,60), Color.GREEN, 0.2f);
                    break;
        }

        return entity;
    }

}
