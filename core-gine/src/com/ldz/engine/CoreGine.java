package com.ldz.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.listener.EntityRemoveListener;
import com.ldz.system.*;

/**
 * Created by Loic on 19/09/2017.
 */
public abstract class CoreGine extends Engine {

    public CoreGine(OrthographicCamera orthographicCamera, SpriteBatch spriteBatch) {

        //set all systems
        this.addSystem(new RenderingSystem(orthographicCamera, spriteBatch));
        this.addSystem(DelayedDisplayerSystem.getInstance(orthographicCamera));
        this.addSystem(PopupSystem.getInstance(orthographicCamera));
        this.addSystem(ExitBoxSystem.getInstance(orthographicCamera));
        this.addSystem(ParentAndChildSystem.getInstance());
        this.addSystem(BagOfEntitiesToEngineSystem.getInstance());
        this.addSystem(InstantDisplayerSystem.getInstance(orthographicCamera));
        this.addSystem(OnActionSystem.getInstance(orthographicCamera));
        this.addSystem(DataTransitToChildSystem.getInstance());
        this.addSystem(PhysicsMovementSystem.getInstance());

        this.addEntityListener(Family.all(ParentAndChildComponent.class).get(), 2, new EntityRemoveListener(this));
    }

}
