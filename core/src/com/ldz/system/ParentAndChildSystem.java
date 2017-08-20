package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.ParentAndChildComponent;
import com.ldz.config.childhierarchy.ChildEntitiesConfig;
import com.ldz.config.childhierarchy.domain.ChildEntities;
import com.ldz.config.childhierarchy.domain.ChildEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by Loic on 20/08/2017.
 */
public class ParentAndChildSystem extends EntitySystem {

    private ImmutableArray<Entity> entityList;

    private ChildEntitiesConfig childEntitiesConfig = ChildEntitiesConfig.getInstance();
    private Map<String, List<Entity>> entityById = new HashMap<>();

    private static ParentAndChildSystem instance = null;

    public static ParentAndChildSystem getInstance() {
        if (instance == null) {
            instance = new ParentAndChildSystem();
        }
        return instance;
    }

    private ParentAndChildSystem() {
        addingListOfChilds = new BiFunction<String, List<String>, Void>() {
            @Override
            public Void apply(String s, List<String> strings) {
                Map<String, List<Entity>> entityById = ParentAndChildSystem.getInstance().entityById;
                if(entityById.containsKey(s)){
                    List<Entity> parentEntitys = entityById.get(s);
                    for (Entity parentEntity:
                            parentEntitys) {

                        //get component
                        ParentAndChildComponent parentAndChildComponent = parentEntity.getComponent(ParentAndChildComponent.class);
                        if(parentAndChildComponent != null){

                            //setting childs
                            for (String childClassNames :
                                    strings) {
                                if(entityById.containsKey(childClassNames)){
                                    parentAndChildComponent.childs.addAll(entityById.get(childClassNames));
                                    //setting parend
                                    for (Entity childEntity :
                                            entityById.get(childClassNames)) {
                                        ParentAndChildComponent childParentAndChildComponent = childEntity.getComponent(ParentAndChildComponent.class);
                                        if(childParentAndChildComponent != null){
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

    private BiFunction<String, List<String>, Void> addingListOfChilds;

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
        if(BagOfEntitiesToEngineSystem.getInstance().allBagsDisplayed()){
            super.update(deltaTime);

            this.entityById = initializeEntityById(new HashMap<>());

            ChildEntities childEntities = this.childEntitiesConfig.getChildEntities();

            this.childEntitiesConfig.iterateThroughChildRecursively(this.addingListOfChilds, childEntities.getEntities());

            this.setProcessing(false);
        }

    }

    private Map<String, List<Entity>> initializeEntityById(Map<String, List<Entity>> entitysById) {
        for (Entity entity :
                entityList) {
            String className = entity.getClass().getName();
            if(entitysById.containsKey(className)){
                entitysById.get(className).add(entity);
            } else {
                List<Entity> entities = new ArrayList<>();
                entities.add(entity);
                entitysById.put(className, entities);
            }
        }
        return entitysById;
    }
}