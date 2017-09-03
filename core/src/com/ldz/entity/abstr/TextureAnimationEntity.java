package com.ldz.entity.abstr;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.AnimationComponent;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.TranformComponent;
import com.ldz.entity.EntityWithId;
import com.ldz.entity.custom.TextureAnimation;

/**
 * Created by Loic on 03/09/2017.
 */
public abstract class TextureAnimationEntity extends EntityWithId {

    public TextureAnimationEntity(Vector2 position, TextureAnimation animation, int z) {

        AnimationComponent animationComponent = new AnimationComponent();
        animation.setPlayMode(Animation.PlayMode.LOOP);
        animationComponent.animation = animation;

        this.add(animationComponent);

        TranformComponent tranformComponent = new TranformComponent();
        tranformComponent.position = position;
        tranformComponent.z = z;

        this.add(tranformComponent);

        BoudingRectangleComponent boudingRectangleComponent = new BoudingRectangleComponent();
        //get the first animation
        Texture firstTesture = ((Texture[]) animation.getKeyFrames())[0];
        boudingRectangleComponent.rectangle = new Rectangle(position.x, position.y, firstTesture.getWidth(), firstTesture.getHeight());

        this.add(boudingRectangleComponent);

    }
}
