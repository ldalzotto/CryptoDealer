package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.ldz.component.BoudingRectangleComponent;
import com.ldz.component.PhysicsMovementComponent;
import com.ldz.component.TranformComponent;

/**
 * Created by Loic on 26/09/2017.
 */
public class PhysicsMovementSystem extends EntitySystem {

    private static PhysicsMovementSystem instance = null;
    private ImmutableArray<Entity> physicsMovementEntity;

    public static PhysicsMovementSystem getInstance() {
        if (instance == null) {
            instance = new PhysicsMovementSystem();
        }
        return instance;
    }


    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.physicsMovementEntity = engine.getEntitiesFor(Family.all(PhysicsMovementComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity physicsMovementEntity :
                this.physicsMovementEntity) {

            PhysicsMovementComponent physicsMovementComponent = physicsMovementEntity.getComponent(PhysicsMovementComponent.class);
            TranformComponent tranformComponent = physicsMovementEntity.getComponent(TranformComponent.class);
            BoudingRectangleComponent boudingRectangleComponent = physicsMovementEntity.getComponent(BoudingRectangleComponent.class);

            if (physicsMovementComponent != null && tranformComponent != null && boudingRectangleComponent != null) {
                if (physicsMovementComponent.constantSpeed != null) {
                    tranformComponent.position.x += (physicsMovementComponent.constantSpeed.x * deltaTime);
                    tranformComponent.position.y += (physicsMovementComponent.constantSpeed.y * deltaTime);
                    boudingRectangleComponent.rectangle.x += (physicsMovementComponent.constantSpeed.x * deltaTime);
                    boudingRectangleComponent.rectangle.y += (physicsMovementComponent.constantSpeed.y * deltaTime);
                }

            }

        }

    }
}
