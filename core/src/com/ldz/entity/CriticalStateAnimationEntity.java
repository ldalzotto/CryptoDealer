package com.ldz.entity;

import com.badlogic.gdx.math.Vector2;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.entity.abstr.TextureAnimationEntity;
import com.ldz.entity.custom.TextureAnimation;

/**
 * Created by Loic on 03/09/2017.
 */
public class CriticalStateAnimationEntity extends TextureAnimationEntity {

    public CriticalStateAnimationEntity(Vector2 position, TextureAnimation animation, int z) {
        super(position, animation, z);
        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);
    }
}
