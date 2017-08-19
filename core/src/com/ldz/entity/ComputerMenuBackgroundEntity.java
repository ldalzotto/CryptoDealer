package com.ldz.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.PopUpComponent;
import com.ldz.entity.abstr.TextureDisplayEntity;

/**
 * Created by Loic on 19/08/2017.
 */
public class ComputerMenuBackgroundEntity extends TextureDisplayEntity {

    public ComputerMenuBackgroundEntity(Vector2 position, Texture texture) {

        super(position, texture);

        PopUpComponent popUpComponent = new PopUpComponent();
        popUpComponent.popupBounding = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

        this.add(popUpComponent);
    }
}
