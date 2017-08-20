package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.TimeAccumlatorComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class DelayedDisplayerEntity extends TextureDisplayEntity {

    public DelayedDisplayerEntity(Vector2 position, Texture texture) {
        super(position, texture);

        TimeAccumlatorComponent timeAccumlatorComponent = new TimeAccumlatorComponent();
        this.add(timeAccumlatorComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        DisplayStateComponent displayStateComponent = new DisplayStateComponent();
        displayStateComponent.state = DisplayStateComponent.STATE.DELAYED;
        this.add(displayStateComponent);

        BagOfEntitiesComponent bagOfEntitiesComponent = new BagOfEntitiesComponent();
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("upgrade-menu-popup"));
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("exit-upgrade-menu-popup"));
        bagOfEntitiesComponent.entities.add(EntityFactory.getEntityFromId("upgrade-1"));

        this.add(bagOfEntitiesComponent);
    }
}
