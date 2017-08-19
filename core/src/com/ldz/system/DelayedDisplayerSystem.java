package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.TimeAccumlatorComponent;

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

    private boolean restoreTimeAccumulatorEnabled = false;

    private ImmutableArray<Entity> computerMenuEntities;

    public DelayedDisplayerSystem(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        computerMenuEntities = engine.getEntitiesFor(Family.all(TimeAccumlatorComponent.class, BoudingRectangleComponent.class).get());
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

            if(restoreTimeAccumulatorEnabled){
                entity.add(new TimeAccumlatorComponent());
            }

            TimeAccumlatorComponent timeAccumlatorComponent = entity.getComponent(TimeAccumlatorComponent.class);
            if(timeAccumlatorComponent != null){
                if(Gdx.input.isTouched()){
                    if(computerTapPressedInside(Gdx.input.getX(), Gdx.input.getY(), entity)){
                        timeAccumlatorComponent.accumulatedTime += deltaTime;

                        if(timeAccumlatorComponent.accumulatedTime >= timeAccumlatorComponent.timeLimit){
                            timeAccumlatorComponent.accumulatedTime = 0;
                            displayComputerMenu(entity, timeAccumlatorComponent);
                        }
                    }

                } else {
                    timeAccumlatorComponent.accumulatedTime = 0f;
                }
                Gdx.app.debug(TAG, String.valueOf(timeAccumlatorComponent.accumulatedTime));
            }
        }

        if(restoreTimeAccumulatorEnabled){
            restoreTimeAccumulatorEnabled = false;
        }
    }

    public boolean computerTapPressedInside(int mx, int my, Entity entity){
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

    private void displayComputerMenu(Entity entity, TimeAccumlatorComponent timeAccumlatorComponent){
        entity.remove(TimeAccumlatorComponent.class);
        this.getEngine().addEntity(timeAccumlatorComponent.targetEntity);
    }

    private void restoreTimeAccumulators(){
        restoreTimeAccumulatorEnabled = true;
    }
}
