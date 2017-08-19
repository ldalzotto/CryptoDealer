package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.component.TimeAccumlatorComponent;
import com.ldz.util.CollisionChecker;
import com.ldz.util.ParentAndChildUtil;

/**
 * Created by Loic on 19/08/2017.
 */
public class DelayedDisplayerSystem extends EntitySystem {

    private static final String TAG = DelayedDisplayerSystem.class.getSimpleName();

    private static DelayedDisplayerSystem instance = null;

    public static DelayedDisplayerSystem getInstance(OrthographicCamera orthographicCamera) {
        if (instance == null) {
            instance = new DelayedDisplayerSystem(orthographicCamera);
        }
        return instance;
    }

    private OrthographicCamera orthographicCamera;

    private ImmutableArray<Entity> computerMenuEntities;

    public DelayedDisplayerSystem(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        computerMenuEntities = engine.getEntitiesFor(Family.all(TimeAccumlatorComponent.class, BoudingRectangleComponent.class, ParentAndChildComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity :
                computerMenuEntities) {

            TimeAccumlatorComponent timeAccumlatorComponent = entity.getComponent(TimeAccumlatorComponent.class);
            ParentAndChildComponent parentAndChildComponent = entity.getComponent(ParentAndChildComponent.class);


            if(timeAccumlatorComponent != null && parentAndChildComponent != null && timeAccumlatorComponent.isProcessing){
                if(Gdx.input.isTouched()){
                    if(CollisionChecker.tapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity, orthographicCamera)){
                        timeAccumlatorComponent.accumulatedTime += deltaTime;

                        if(timeAccumlatorComponent.accumulatedTime >= timeAccumlatorComponent.timeLimit){
                            timeAccumlatorComponent.accumulatedTime = 0;
                            timeAccumlatorComponent.isProcessing = false;
                            ParentAndChildUtil.displayChildsRecurcsively(parentAndChildComponent, this.getEngine());
                        }
                    }

                } else {
                    timeAccumlatorComponent.accumulatedTime = 0f;
                }
                Gdx.app.debug(TAG, String.valueOf(timeAccumlatorComponent.accumulatedTime));
            }
        }

    }

}
