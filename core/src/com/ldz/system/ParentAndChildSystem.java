package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.config.childhierarchy.ChildEntitiesConfig;
import com.ldz.config.childhierarchy.domain.ChildEntities;
import com.ldz.config.game.entities.EntityId;
import com.ldz.entity.EntityWithId;
import com.ldz.system.inter.IRetrieveAllEntitiesFromSystem;

import java.util.*;
import java.util.function.BiFunction;

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

    private static ParentAndChildSystem instance = null;
    private ImmutableArray<Entity> entityList;
    private ChildEntitiesConfig childEntitiesConfig = ChildEntitiesConfig.getInstance();
    private Map<EntityId, List<Entity>> entityById = new HashMap<>();
    private BiFunction<EntityId, List<EntityId>, Void> addingListOfChilds;

    private ParentAndChildSystem() {
        addingListOfChilds = new BiFunction<EntityId, List<EntityId>, Void>() {
            @Override
            public Void apply(EntityId s, List<EntityId> strings) {
                Map<EntityId, List<Entity>> entityById = ParentAndChildSystem.getInstance().entityById;
                if (entityById.containsKey(s)) {
                    List<Entity> parentEntitys = entityById.get(s);
                    for (Entity parentEntity :
                            parentEntitys) {

                        //get component
                        ParentAndChildComponent parentAndChildComponent = parentEntity.getComponent(ParentAndChildComponent.class);
                        if (parentAndChildComponent != null) {

                            //setting childs
                            for (EntityId childClassNames :
                                    strings) {
                                if (entityById.containsKey(childClassNames)) {
                                    parentAndChildComponent.childs.addAll(entityById.get(childClassNames));
                                    //setting parend
                                    for (Entity childEntity :
                                            entityById.get(childClassNames)) {
                                        ParentAndChildComponent childParentAndChildComponent = childEntity.getComponent(ParentAndChildComponent.class);
                                        if (childParentAndChildComponent != null) {
                                            childParentAndChildComponent.parent = parentEntity;
                                        }
                                    }
                                }
                            }

                        }
                    }

                }
                return null;
            }
        };
    }

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

    @Override
    public void update(float deltaTime) {
        if (BagOfEntitiesToEngineSystem.getInstance().allBagsDisplayed()) {
            super.update(deltaTime);

            this.entityById = initializeEntityById(new HashMap<>());

            ChildEntities childEntities = this.childEntitiesConfig.getChildEntities();
            this.childEntitiesConfig.iterateThroughChildRecursively(this.addingListOfChilds, childEntities.getEntities());

            this.setProcessing(false);
        }

    }

    /**
     * <p>
     * Tranform every entites possessing a {@link ParentAndChildComponent} defined in {@link ParentAndChildSystem#entityList} to a {@link Map<String id, List<Entity> entitybyid>}. This allow to retreive every entity by their {@link com.ldz.config.game.entities.EntityId}
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

    @Override
    public List<Iterable<Entity>> getAllEntities() {
        List entities = Arrays.asList(this.entityList);
        return entities;
    }
}
