package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.ExitBoxComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class ExitBoxMenuEntity extends TextureDisplayEntity {

    public ExitBoxMenuEntity(Vector2 position, Texture texture) {
        super(position, texture);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

        ExitBoxComponent exitBoxComponent = new ExitBoxComponent();
        exitBoxComponent.boundingBox = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        this.add(exitBoxComponent);
    }
}
