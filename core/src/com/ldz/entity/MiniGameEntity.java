package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.DisplayStateComponent;
import com.ldz.entity.util.EntityUtil;

import java.util.ArrayList;

/**
 * Created by Loic on 17/09/2017.
 */
public class MiniGameEntity extends EntityWithId {

    public MiniGameEntity(Vector2 position, Texture texture, int z) {
        EntityUtil.addParentAndChildEntity(this);
        EntityUtil.addBagOfEntitiesComponent(new ArrayList<String>(), this);
        EntityUtil.addtextureEntityComponents(position, texture, z, this);
        EntityUtil.addMiniGameComponent(this);

        DisplayStateComponent displayStateComponent = new DisplayStateComponent();
        displayStateComponent.state = DisplayStateComponent.STATE.EPHEMER;
        this.add(displayStateComponent);
    }
}
