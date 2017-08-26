package com.ldz.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.ldz.DebugDataBase;
import com.ldz.IDebugDataBase;
import com.ldz.entity.EntityWithId;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import org.neo4j.driver.v1.AuthTokens;

import java.util.AbstractMap;
import java.util.List;

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
        iDebugDataBase = DebugDataBase.getInstance();
        iDebugDataBase.connectTodatabase("bolt://localhost:7687", AuthTokens.basic("neo4j", "Abc01234"));
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

        if (System.getenv("DEBUG_ENABLED").equals("true")) {
            this.iDebugDataBase.addSystem(system.getClass().getSimpleName(), new AbstractMap.SimpleEntry[]{});
        }

    }

    @Override
    public void addEntity(Entity entity) {
        super.addEntity(entity);
        if (System.getenv("DEBUG_ENABLED").equals("true")) {
            if (entity instanceof EntityWithId) {
                EntityWithId entityWithId = (EntityWithId) entity;
                this.iDebugDataBase.addEntity(entityWithId.getId().name(), new AbstractMap.SimpleEntry[]{});
            }
        }

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (System.getenv("DEBUG_ENABLED").equals("true")) {
            this.timeAccumulator += deltaTime;
            if (this.timeAccumulator > this.DEBUG_INTERVAL) {
                updateDebugEngine();
                this.timeAccumulator = 0f;
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
                    entityIter.forEach(entity -> {
                        if (entity instanceof EntityWithId) {
                            EntityWithId entityWithId = (EntityWithId) entity;
                            this.iDebugDataBase.createLinkSystemToEntity(entitySystem.getClass().getSimpleName(), entityWithId.getId().name());
                        }
                    });
                }

            }
        }
    }

}
