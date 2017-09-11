package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.TimeAccumlatorComponent;
import com.ldz.config.game.entities.InstanceEntityId;
import com.ldz.entity.abstr.TextureDisplayEntity;

import java.util.ArrayList;

/**
 * Created by Loic on 19/08/2017.
 */
public class DelayedDisplayerEntity extends TextureDisplayEntity {

    public DelayedDisplayerEntity(Vector2 position, Texture texture, int z, ArrayList<String> entityInBagIds) {
        super(position, texture, z);

        TimeAccumlatorComponent timeAccumlatorComponent = new TimeAccumlatorComponent();
        this.add(timeAccumlatorComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        DisplayStateComponent displayStateComponent = new DisplayStateComponent();
        displayStateComponent.state = DisplayStateComponent.STATE.DELAYED;
        this.add(displayStateComponent);

        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();

        for (String entityIdsInBag :
                entityInBagIds) {
            bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromInstanceId(InstanceEntityId.valueOf(entityIdsInBag)));
        }

        this.add(bagOfEntitiesComponent);
    }
}
