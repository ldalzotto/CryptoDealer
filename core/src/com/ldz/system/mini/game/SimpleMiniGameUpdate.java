package com.ldz.system.mini.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ldz.component.CollisionCalculationComponent;
import com.ldz.component.action.IMiniGameUpdate;
import com.ldz.config.game.entities.EntityId;
import com.ldz.entity.EntityFactory;
import com.ldz.entity.EntityWithId;
import com.ldz.util.LoggingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Loic on 17/09/2017.
 */
public class SimpleMiniGameUpdate implements IMiniGameUpdate {

    private static final String TAG = SimpleMiniGameUpdate.class.getSimpleName();
    private Rectangle gameArea;
    private List<Entity> entityToAddToEngine = new ArrayList<>();
    private List<Entity> entityToRemoveToEngine = new ArrayList<>();
    private EntityWithId player;
    private List<EntityWithId> targets = new ArrayList<>();

    @Override
    public void init(Rectangle gameArea) {
        this.gameArea = gameArea;

        //init player
        this.player = EntityFactory.createInstanceFromEntityId(EntityId.simple_mini_game_player, new Vector2(this.gameArea.x, this.gameArea.y),
                new Texture(Gdx.files.internal("assets/player.bmp")), 7, new Vector2(0, 0));

        int randTargetPos = (int) MathUtils.random(this.gameArea.getWidth());

        EntityWithId entityWithId = EntityFactory.createInstanceFromEntityId(EntityId.simple_mini_game_target, new Vector2(this.gameArea.x + randTargetPos, this.gameArea.y + this.gameArea.height / 2),
                new Texture(Gdx.files.internal("assets/target.bmp")), 7, new Vector2(0, -60));

        randTargetPos = (int) MathUtils.random(this.gameArea.getWidth());
        EntityWithId entityWithId2 = EntityFactory.createInstanceFromEntityId(EntityId.simple_mini_game_target, new Vector2(this.gameArea.x + randTargetPos, this.gameArea.y + this.gameArea.height / 1.5f),
                new Texture(Gdx.files.internal("assets/target.bmp")), 7, new Vector2(0, -60));

        targets.add(entityWithId);
        targets.add(entityWithId2);
        this.entityToAddToEngine.add(entityWithId);
        this.entityToAddToEngine.add(entityWithId2);


        this.entityToAddToEngine.add(player);


    }

    @Override
    public int update(float delta) {

        //refreshing collisions entity
        //between player -> target
        CollisionCalculationComponent collisionCalculationComponent = this.player.getComponent(CollisionCalculationComponent.class);
        if (collisionCalculationComponent != null) {
            collisionCalculationComponent.state = CollisionCalculationComponent.STATE.ACTIVE;
            collisionCalculationComponent.entityToCompare.clear();
            collisionCalculationComponent.entityToCompare.addAll(this.targets);
        }

        //updating
        if (collisionCalculationComponent != null && collisionCalculationComponent.isInCollision) {
            LoggingUtil.DEBUG(TAG, "BOOOOM");
            for (Entity collidedEntity :
                    collisionCalculationComponent.entityinCollision) {
                if (collidedEntity instanceof EntityWithId) {
                    if (((EntityWithId) collidedEntity).getId().equals(EntityId.simple_mini_game_target)) {
                        LoggingUtil.DEBUG(TAG, "MINI_GAME_OVER");
                        entityToRemoveToEngine.add(collidedEntity);
                    }
                }
            }
        }


        return 0;
    }

    @Override
    public List<Entity> addNewEntityToEngine(Engine engine) {
        List<Entity> returnList = new ArrayList<>();
        for (Entity entity :
                this.entityToAddToEngine) {
            engine.addEntity(entity);
            returnList.add(entity);
        }
        this.entityToAddToEngine.clear();
        return returnList;
    }

    @Override
    public void removeEntityToRemoveToEngine(Engine engine) {
        for (Entity entity :
                this.entityToRemoveToEngine) {
            engine.removeEntity(entity);
            if (entity instanceof EntityWithId) {
                switch (((EntityWithId) entity).getId()) {
                    case simple_mini_game_target:
                        this.targets.remove(entity);
                }
            }
        }
        this.entityToRemoveToEngine.clear();
    }
}
