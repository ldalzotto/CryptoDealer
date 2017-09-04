package com.ldz.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.ldz.DebugDataBase;
import com.ldz.IDebugDataBase;
import com.ldz.entity.EntityWithId;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import org.neo4j.driver.v1.AuthTokens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Loic on 20/08/2017.
 */
public class MyEngine extends Engine {

    private static final float DEBUG_INTERVAL = 1.0f;
    private static MyEngine instance = null;
    private IDebugDataBase iDebugDataBase;
    private float timeAccumulator;

    public MyEngine() {
        super();
        if (this.isDebugGraphEnabled()) {
            iDebugDataBase = DebugDataBase.getInstance();
            iDebugDataBase.connectTodatabase("bolt://localhost:7687", AuthTokens.basic("neo4j", "Abc01234"));
        }

    }

    public static MyEngine getInstance() {
        if (instance == null) {
            instance = new MyEngine();
        }
        return instance;
    }

    @Override
    public void addSystem(EntitySystem system) {
        super.addSystem(system);

        if (this.isDebugGraphEnabled()) {
            this.iDebugDataBase.addSystem(system.getClass().getSimpleName());
        }


    }

    @Override
    public void addEntity(Entity entity) {
        super.addEntity(entity);

        if (this.isDebugGraphEnabled()) {
            if (entity instanceof EntityWithId) {
                EntityWithId entityWithId = (EntityWithId) entity;
                Map<String, String> entityParameters = new HashMap<>();
                entityParameters.put("DESCRIPTION", entityWithId.getId().getDescription());
                this.iDebugDataBase.addEntity(entityWithId.getId().name(), entityParameters);
            }
        }


    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        if (this.isDebugGraphEnabled()) {
            this.timeAccumulator += deltaTime;
            if (this.timeAccumulator > this.DEBUG_INTERVAL) {
                updateDebugEngine();
                this.timeAccumulator = 0f;
            }
        }


    }

    @Override
    public void removeEntity(Entity entity) {
        super.removeEntity(entity);

        if (this.isDebugGraphEnabled()) {
            if (entity instanceof EntityWithId) {
                EntityWithId entityWithId = (EntityWithId) entity;
                this.iDebugDataBase.deleteNode(entityWithId.getId().name());
            }
        }

    }

    private void updateDebugEngine() {
        for (EntitySystem entitySystem :
                this.getSystems()) {
            if (entitySystem instanceof IRetrieveAllEntitiesFromSystem) {

                List<Iterable<Entity>> entities = ((IRetrieveAllEntitiesFromSystem) entitySystem).getAllEntities();
                for (Iterable<Entity> entityIter :
                        entities) {
                    for (Entity entity :
                            entityIter) {
                        if (entity instanceof EntityWithId) {
                            EntityWithId entityWithId = (EntityWithId) entity;
                            this.iDebugDataBase.createLinkSystemToEntity(entitySystem.getClass().getSimpleName(), entityWithId.getId().name());
                        }
                    }
                }

            }
        }
    }

    private boolean isDebugGraphEnabled() {
        return (System.getenv("DEBUG_ENABLED") != null && System.getenv("DEBUG_ENABLED").equals("true"));
    }

}
