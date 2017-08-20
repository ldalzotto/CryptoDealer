package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.DisplayStateComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.PopUpComponent;
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
    private boolean screenTouching = false;

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        //only compute when parent is not computing
        ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);
        if(parentAndChildComponent != null){
            if(ParentAndChildSystem.getInstance().checkProcessing()){
                return;
            }
        }

        if(Gdx.input.isTouched()){
            if(!this.screenTouching){
                this.screenTouching = true;
                if(!CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)){
                    this.getEngine().removeEntity(entity);

                    PopUpComponent popUpComponent = entity.getComponent(PopUpComponent.class);

                    if(popUpComponent != null && parentAndChildComponent != null){
                        //adding popupaccumulator
                        Entity sourceEntity = parentAndChildComponent.parent;
                        if(sourceEntity != null){
                            //if displaystate component
                            DisplayStateComponent displayStateComponent = sourceEntity.getComponent(DisplayStateComponent.class);
                            if(displayStateComponent != null){
                                displayStateComponent.isDisplayed = false;
                            }

                        }
                    }
                }
            }
        } else {
            this.screenTouching = false;
        }
    }

    public boolean popupActives(){
        if(this.getEntities().size() > 0){
            return true;
        } else {
            return false;
        }
    }
}
