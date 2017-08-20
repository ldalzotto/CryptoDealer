package com.ldz.entity.abstr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.TextureComponent;
import com.ldz.component.TranformComponent;
import com.ldz.entity.EntityWithId;

/**
 * Created by Loic on 19/08/2017.
 */
public abstract class TextureDisplayEntity extends EntityWithId {

    public TextureDisplayEntity(Vector2 position, Texture texture) {

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;

        TextureComponent textureComponent = new TextureComponent();
        textureComponent.texture = texture;

        BoudingRectangleComponent boudingRectangleComponent = new BoudingRectangleComponent();
        boudingRectangleComponent.rectangle = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());

        this.add(tranformComponent);
        this.add(textureComponent);
        this.add(boudingRectangleComponent);

    }
}
