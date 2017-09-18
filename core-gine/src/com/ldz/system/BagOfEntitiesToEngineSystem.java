package com.ldz.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.ldz.component.BagOfEntitiesComponent;
import com.ldz.system.custom.MyIteratingSystem;

/**
 * Created by Loic on 20/08/2017.
 */
public class BagOfEntitiesToEngineSystem extends MyIteratingSystem {

    private static final String TAG = BagOfEntitiesToEngineSystem.class.getSimpleName();
    private static BagOfEntitiesToEngineSystem instance = null;

    public BagOfEntitiesToEngineSystem() {
        super(Family.all(BagOfEntitiesComponent.class).get());
    }

    public static BagOfEntitiesToEngineSystem getInstance() {
        if (instance == null) {
            instance = new BagOfEntitiesToEngineSystem();
        }
        return instance;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        BagOfEntitiesComponent bagOfEntitiesComponent = entity.getComponent(BagOfEntitiesComponent.class);

        if (bagOfEntitiesComponent != null && bagOfEntitiesComponent.addEntityToEngine) {
            for (Entity bagEntity :
                    bagOfEntitiesComponent.entities) {
                try {
                    this.getEngine().addEntity(bagEntity);
                } catch (Exception e) {
                    Gdx.app.error(TAG, e.getMessage(), e);
                }
            }
            bagOfEntitiesComponent.addEntityToEngine = false;
        }

    }

    public boolean allBagsDisplayed() {
        boolean returnBool = true;

        for (Entity entity :
                this.getEntities()) {
            BagOfEntitiesComponent bagOfEntitiesComponent = entity.getComponent(BagOfEntitiesComponent.class);
            if (bagOfEntitiesComponent != null) {
                if (bagOfEntitiesComponent.addEntityToEngine) {
                    returnBool = false;
                }
            }
        }

        return returnBool;
    }

}
