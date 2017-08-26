package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.OnActionComponent;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import com.ldz.util.CollisionChecker;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Loic on 20/08/2017.
 */
public class OnActionSystem extends IteratingSystem implements IRetrieveAllEntitiesFromSystem {


    private static OnActionSystem instance = null;
    private OrthographicCamera orthographicCamera;

    public OnActionSystem(OrthographicCamera orthographicCamera) {
        super(Family.all(OnActionComponent.class).get());
        this.orthographicCamera = orthographicCamera;
    }

    public static OnActionSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new OnActionSystem(orthographicCamera);
        }
        return instance;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        if (ParentAndChildSystem.getInstance().checkProcessing()) {
            return;
        }

        OnActionComponent onActionComponent = entity.getComponent(OnActionComponent.class);

        if (onActionComponent != null) {


            switch (onActionComponent.action_type) {
                case ON_CLICK_INSIDE:
                    if (Gdx.input.isTouched()) {
                        if (CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)) {
                            onActionComponent.function.apply(entity);
                        }
                    }
                    break;
            }

        }
    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.getEntities());
        return entities;
    }
}
