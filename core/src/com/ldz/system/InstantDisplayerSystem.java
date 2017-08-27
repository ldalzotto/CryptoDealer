package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.component.DisplayStateComponent;
import com.ldz.system.custom.MyIteratingSystem;
import com.ldz.util.CollisionChecker;

/**
 * Created by Loic on 20/08/2017.
 */
public class InstantDisplayerSystem extends MyIteratingSystem {


    private static InstantDisplayerSystem instance = null;
    private OrthographicCamera orthographicCamera;

    public InstantDisplayerSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(DisplayStateComponent.class).get());
        this.orthographicCamera = orthographicCamera;
    }

    public static InstantDisplayerSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new InstantDisplayerSystem(orthographicCamera);
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        DisplayStateComponent displayStateComponent = entity.getComponent(DisplayStateComponent.class);
        BagOfEntitiesComponent bagOfEntitiesComponent = entity.getComponent(BagOfEntitiesComponent.class);

        if (displayStateComponent != null && displayStateComponent.state.equals(DisplayStateComponent.STATE.INSTANT) &&
                bagOfEntitiesComponent != null && !displayStateComponent.isDisplayed) {

            if (Gdx.input.isTouched()) {
                if (CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)) {
                    bagOfEntitiesComponent.addEntityToEngine = true;
                    displayStateComponent.isDisplayed = true;
                    ParentAndChildSystem.getInstance().setProcessing(true);
                }
            }

        }

    }

}
