package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PopUpComponent;
import com.ldz.component.TimeAccumlatorComponent;
import com.ldz.util.CollisionChecker;

/**
 * Created by Loic on 19/08/2017.
 */
public class PopupSystem extends IteratingSystem {

    public PopupSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(PopUpComponent.class, ParentAndChildComponent.class).get());
        this.orthographicCamera = orthographicCamera;
    }

    private static PopupSystem instance = null;

    public static PopupSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new PopupSystem(orthographicCamera);
        }
        return instance;
    }

    private OrthographicCamera orthographicCamera;


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(Gdx.input.isTouched()){
            if(!CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)){
                this.getEngine().removeEntity(entity);

                PopUpComponent popUpComponent = entity.getComponent(PopUpComponent.class);
                ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);

                if(popUpComponent != null && parentAndChildComponent != null){
                    //adding popupaccumulator
                    Entity sourceEntity = parentAndChildComponent.parent;
                    if(sourceEntity != null){
                        TimeAccumlatorComponent timeAccumlatorComponent = sourceEntity.getComponent(TimeAccumlatorComponent.class);
                        if(timeAccumlatorComponent != null){
                            timeAccumlatorComponent.isProcessing = true;
                        }
                    }
                }
            }
        }
    }
}
