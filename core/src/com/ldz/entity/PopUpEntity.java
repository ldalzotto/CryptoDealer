package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PopUpComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class PopUpEntity extends TextureDisplayEntity {

    public PopUpEntity(Vector2 position, Texture texture, int z) {

        super(position, texture, z);

        PopUpComponent popUpComponent = new PopUpComponent();
        popUpComponent.popupBounding = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        this.add(popUpComponent);

        ParentAndChildComponent parentAndChildComponent = new ParentAndChildComponent();
        this.add(parentAndChildComponent);

    }
}
