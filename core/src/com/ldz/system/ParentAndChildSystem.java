package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.config.childhierarchy.ChildEntitiesConfig;
import com.ldz.config.childhierarchy.domain.ChildEntities;
import com.ldz.config.game.entities.EntityId;
import com.ldz.entity.EntityWithId;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.*;

/**
 * Created by Loic on 20/08/2017.
 * <p>
 * This {@link System} allow linking parent and childs of all entities. Only entities possessing {@link ParentAndChildComponent} are tagged for child and parent linking.
 * The processing of entites is executed only after the completion of adding all entities contained in bag of entites in engine {@link BagOfEntitiesToEngineSystem#allBagsDisplayed()}.
 * </p>
 * <p>
 * Parent and child link of all entites are defined in {@link ChildEntitiesConfig}.
 * <p/>
 */
public class ParentAndChildSystem extends EntitySystem implements IRetrieveAllEntitiesFromSystem {

    private static final String TAG = ParentAndChildSystem.class.getSimpleName();

    private static ParentAndChildSystem instance = null;
    private ImmutableArray<Entity> entityList;
    private ChildEntitiesConfig childEntitiesConfig = ChildEntitiesConfig.getInstance();
    private Map<EntityId, List<Entity>> entityById = new HashMap<>();

    public static ParentAndChildSystem getInstance() {
        if (instance == null) {
            instance = new ParentAndChildSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.entityList = engine.getEntitiesFor(Family.all(ParentAndChildComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
    }

    /**
     * <p>
     * The update method is :
     * <ul><li>
     * Initializing the list of all parent and child entity {@link ParentAndChildSystem#initializeEntityById(Map)}
     * </li><li>
     * Get the parent and child hierarchy config {@link ChildEntitiesConfig} -> {@link ChildEntities} {@code childEntities}
     * </li><li>
     * Apply the parent and child linking based on the tree {@code childEntities} on entites contained in {@link ParentAndChildSystem#entityById}
     * </li></ul>
     * </p>
     *
     * @param deltaTime the delta time of Engine update
     */
    @Override
    public void update(float deltaTime) {
        if (BagOfEntitiesToEngineSystem.getInstance().allBagsDisplayed()) {

            super.update(deltaTime);

            Gdx.app.debug(TAG, "Starting setting parent and child of : " + ReflectionToStringBuilder.toString(this.entityList));
            this.entityById = initializeEntityById(new HashMap<>());
            Gdx.app.debug(TAG, "Setting parent and child ending successfully.");

            ChildEntities childEntities = this.childEntitiesConfig.getChildEntities();

            this.childEntitiesConfig.applyLinkingThroughChildRecursively(childEntities.getEntities());

            this.setProcessing(false);
        }

    }

    /**
     * <p>
     * Tranform every entites possessing a {@link ParentAndChildComponent} defined in {@link ParentAndChildSystem#entityList} to a {@link Map<EntityId, List<Entity> entitybyid>}. This allow to retreive every entity by their {@link com.ldz.config.game.entities.EntityId}
     * </p>
     *
     * @param entitysById an accumulator Map
     * @return all entities associated to their {@link com.ldz.config.game.entities.EntityId}
     */
    private Map<EntityId, List<Entity>> initializeEntityById(Map<EntityId, List<Entity>> entitysById) {
        for (Entity entity :
                entityList) {
            if (entity instanceof EntityWithId) {
                EntityWithId entityWithId = (EntityWithId) entity;
                EntityId id = entityWithId.getId();
                if (entitysById.containsKey(id)) {
                    entitysById.get(id).add(entity);
                } else {
                    List<Entity> entities = new ArrayList<>();
                    entities.add(entity);
                    entitysById.put(id, entities);
                }
            }

        }
        return entitysById;
    }

    public Map<EntityId, List<Entity>> getEntityById() {
        return entityById;
    }

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.entityList);
        return entities;
    }
}
