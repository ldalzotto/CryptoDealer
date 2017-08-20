package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 20/08/2017.
 */
public class DisplayerEntity extends TextureDisplayEntity {

    public DisplayerEntity(Vector2 position, Texture texture) {
        super(position, texture);

        DisplayStateComponent displayStateComponent = new DisplayStateComponent();
        displayStateComponent.state = DisplayStateComponent.STATE.INSTANT;
        this.add(displayStateComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();

        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("upgrade-1-buy-popup"));
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("upgrade-1-cost-display"));
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("upgrade-1-buy-button"));
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("upgrade-1-exit-button"));



        this.add(bagOfEntitiesComponent);

    }
}
