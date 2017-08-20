package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.ExitBoxComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.util.CollisionChecker;

/**
 * Created by Loic on 19/08/2017.
 */
public class ExitBoxSystem extends IteratingSystem {

    public ExitBoxSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(ExitBoxComponent.class).get());
        this.orthographicCamera = orthographicCamera;
    }


    private static ExitBoxSystem instance = null;

    public static ExitBoxSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new ExitBoxSystem(orthographicCamera);
        }
        return instance;
    }

    private OrthographicCamera orthographicCamera;

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(Gdx.input.isTouched()){
            if(CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)){
                this.getEngine().removeEntity(entity);

                ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);

                if(parentAndChildComponent != null){
                    //adding popupaccumulator
                    Entity sourceEntity = parentAndChildComponent.parent;
                    if(sourceEntity != null){
                        this.getEngine().removeEntity(sourceEntity);
                    }
                }
            }
        }
    }
}