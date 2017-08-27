package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ExitBoxComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.system.custom.MyIteratingSystem;
import com.ldz.util.CollisionChecker;

/**
 * Created by Loic on 19/08/2017.
 */
public class ExitBoxSystem extends MyIteratingSystem {

    private static ExitBoxSystem instance = null;
    private OrthographicCamera orthographicCamera;

    public ExitBoxSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(ExitBoxComponent.class).get());
        this.orthographicCamera = orthographicCamera;
    }

    public static ExitBoxSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new ExitBoxSystem(orthographicCamera);
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (Gdx.input.isTouched()) {
            if (CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)) {

                ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);

                if (parentAndChildComponent != null) {
                    //adding popupaccumulator
                    Entity sourceEntity = parentAndChildComponent.parent;
                    if (sourceEntity != null) {
                        this.getEngine().removeEntity(sourceEntity);
                    }

                    //get popup
                    Entity popupEntity = parentAndChildComponent.parent;

                    if (popupEntity != null) {
                        //popup source
                        ParentAndChildComponent parentAndChildComponent1 = popupEntity.getComponent(ParentAndChildComponent.class);
                        if (parentAndChildComponent1 != null) {
                            Entity popupSourceEntity = parentAndChildComponent1.parent;
                            if (popupSourceEntity != null) {
                                DisplayStateComponent displayStateComponent = popupSourceEntity.getComponent(DisplayStateComponent.class);
                                if (displayStateComponent != null) {
                                    displayStateComponent.isDisplayed = false;
                                }
                            }
                        }

                    }

                }
            }
        }
    }

}
