package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.CollisionCalculationComponent;
import com.ldz.util.CollisionChecker;

/**
 * Created by Loic on 26/09/2017.
 */
public class CollisionSystem extends EntitySystem {

    private static CollisionSystem instance = null;
    private ImmutableArray<Entity> entities;

    public static CollisionSystem getInstance() {
        if (instance == null) {
            instance = new CollisionSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.entities = engine.getEntitiesFor(Family.all(CollisionCalculationComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity :
                this.entities) {

            CollisionCalculationComponent collisionCalculationComponent = entity.getComponent(CollisionCalculationComponent.class);
            BoudingRectangleComponent boudingRectangleComponent = entity.getComponent(BoudingRectangleComponent.class);

            if (collisionCalculationComponent != null && boudingRectangleComponent != null) {

                //reset component
                collisionCalculationComponent.entityinCollision.clear();
                collisionCalculationComponent.isInCollision = false;

                switch (collisionCalculationComponent.state) {
                    case ACTIVE:
                        for (Entity entityToCompare :
                                collisionCalculationComponent.entityToCompare) {
                            BoudingRectangleComponent boudingRectangleComponentToCompare = entityToCompare.getComponent(BoudingRectangleComponent.class);
                            if (boudingRectangleComponentToCompare != null) {
                                if (CollisionChecker.isRectangleOverlapping(boudingRectangleComponent.rectangle, boudingRectangleComponentToCompare.rectangle)) {
                                    collisionCalculationComponent.entityinCollision.add(entityToCompare);
                                }
                            }
                        }

                        if (collisionCalculationComponent.entityinCollision.size() > 0) {
                            collisionCalculationComponent.isInCollision = true;
                        }

                        break;
                    case INACTIVE:
                        break;
                }
            }

        }

    }
}
