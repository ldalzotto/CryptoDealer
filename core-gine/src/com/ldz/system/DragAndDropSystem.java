package com.ldz.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.DragAndDropComponent;

/**
 * Created by Loic on 28/09/2017.
 */
public class DragAndDropSystem extends EntitySystem {


    private static DragAndDropSystem instance = null;
    private ImmutableArray<Entity> entities;

    public static DragAndDropSystem getInstance() {
        if (instance == null) {
            instance = new DragAndDropSystem();
        }
        return instance;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.entities = engine.getEntitiesFor(Family.all(DragAndDropComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity :
                this.entities) {

            DragAndDropComponent dragAndDropComponent = entity.getComponent(DragAndDropComponent.class);
            if (dragAndDropComponent != null && dragAndDropComponent.processing) {


                if (Gdx.input.justTouched()) {
                    dragAndDropComponent.state = DragAndDropComponent.STATE.START_CLICKING;
                }

                if (!Gdx.input.isTouched()) {
                    if (!dragAndDropComponent.state.equals(DragAndDropComponent.STATE.PENDING)) {
                        dragAndDropComponent.state = DragAndDropComponent.STATE.END_CLICKING;
                    }
                }

                switch (dragAndDropComponent.state) {
                    case PENDING:
                        break;
                    case START_CLICKING:
                        dragAndDropComponent.directionVector = new Vector2();
                        dragAndDropComponent.startPoint = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                        dragAndDropComponent.endPoint = new Vector2();
                        break;
                    case WHILE_CLICKING:
                        break;
                    case END_CLICKING:
                        dragAndDropComponent.endPoint = new Vector2(Gdx.input.getX(), Gdx.input.getY());
                        dragAndDropComponent.directionVector = dragAndDropComponent.endPoint.sub(dragAndDropComponent.startPoint).nor();
                        dragAndDropComponent.state = DragAndDropComponent.STATE.PROCESS_FINISHED;
                        break;
                    case PROCESS_FINISHED:
                        break;
                }

            }

        }

    }
}
