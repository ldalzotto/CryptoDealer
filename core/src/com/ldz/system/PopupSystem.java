package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.PopUpComponent;

/**
 * Created by Loic on 19/08/2017.
 */
public class PopupSystem extends IteratingSystem {

    public PopupSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(PopUpComponent.class).get());
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
            if(!tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity)){
                this.getEngine().removeEntity(entity);

                PopUpComponent popUpComponent = entity.getComponent(PopUpComponent.class);
                if(popUpComponent != null){
                    popUpComponent.sourceEntity.add(popUpComponent.sourceOfPopupTimeAccumulatorComponent);
                }
            }
        }
    }

    public boolean tapPressedInside(int mx, int my, Entity entity){
        boolean isPressedInside = false;
        if(entity != null){
            BoudingRectangleComponent boudingRectangleComponent = entity.getComponent(BoudingRectangleComponent.class);
            if(boudingRectangleComponent != null){
                Vector3 unprojected = orthographicCamera.unproject(new Vector3(mx, my, 0));
                if(boudingRectangleComponent.rectangle.contains(unprojected.x, unprojected.y)){
                    isPressedInside = true;
                }
            }
        }
        return isPressedInside;
    }
}
