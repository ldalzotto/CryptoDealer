package com.ldz.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.*;
import com.ldz.util.CollisionChecker;
import com.ldz.util.ComponentUtil;

import java.util.Map;

/**
 * Created by Loic on 19/08/2017.
 */
public class PopupSystem extends IteratingSystem {

    private static final String TAG = PopupSystem.class.getSimpleName();

    private static PopupSystem instance = null;
    private OrthographicCamera orthographicCamera;
    private boolean screenTouching = false;

    public PopupSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(PopUpComponent.class, ParentAndChildComponent.class).get());
        this.orthographicCamera = orthographicCamera;
    }

    public static PopupSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new PopupSystem(orthographicCamera);
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        Map<String, Component> componentContainer;
        try {
            componentContainer = ComponentUtil.getAllComponentsFromEntity(entity, PopUpComponent.class, ParentAndChildComponent.class);
        } catch (Exception e) {
            Gdx.app.error(TAG, e.getMessage());
            Gdx.app.error(TAG, e.getCause().toString());
            return;
        }

        //only compute when parent is not computing
        ParentAndChildComponent parentAndChildComponent = (ParentAndChildComponent) componentContainer.get(ParentAndChildComponent.class.getSimpleName());
        PopUpComponent popUpComponent = (PopUpComponent) componentContainer.get(PopUpComponent.class.getSimpleName());


        if (ParentAndChildSystem.getInstance().checkProcessing()) {
            return;
        }


        if (Gdx.input.isTouched()) {
            if (!popUpComponent.screenBeingTouched) {
                popUpComponent.screenBeingTouched = true;
                if (!CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)) {
                    this.getEngine().removeEntity(entity);


                    //adding popupaccumulator
                    Entity sourceEntity = parentAndChildComponent.parent;
                    if (sourceEntity != null) {
                        //if displaystate component
                        DisplayStateComponent displayStateComponent = sourceEntity.getComponent(DisplayStateComponent.class);
                        if (displayStateComponent != null) {
                            displayStateComponent.isDisplayed = false;
                        }

                    }

                }
            }
        } else {
            popUpComponent.screenBeingTouched = false;
        }


    }

    public boolean popupActives() {
        if (this.getEntities().size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
